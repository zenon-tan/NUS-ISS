package day28.workshop.repositories;

import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.LimitOperation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.aggregation.UnwindOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import day28.workshop.utils.CustomAggregation;

@Repository
public class ReviewRepo {

    @Autowired
    MongoTemplate mongoTemplate;

    public Optional<Document> getReviewsByGid(int gid) {

        MatchOperation match = Aggregation.match(Criteria.where("gid").is(gid));

        LookupOperation lookup = Aggregation.lookup("comment", "gid", "gid", "reviews");

        Aggregation pipeline = Aggregation.newAggregation(match, lookup);

        try {

            AggregationResults<Document> results = mongoTemplate.aggregate(pipeline, "game", Document.class);

            return Optional.of(results.getMappedResults().get(0));
            
        } catch (Exception e) {
            
            return Optional.empty();
        }
        
    }

    public Double getReviewAverageByGid(int gid) {

        MatchOperation match = Aggregation.match(Criteria.where("gid").is(gid));

        GroupOperation group = Aggregation.group("gid").avg("rating").as("average");

        Aggregation pipeline = Aggregation.newAggregation(match, group);

        AggregationResults<Document> results = mongoTemplate.aggregate(pipeline, "comment", Document.class);
        Double average = results.getMappedResults().get(0).getDouble("average");

        return average;

    }

    public List<Document> getAllReviewsSorted(String sortBy) throws Exception {

        int order = 0;

        if(sortBy.equalsIgnoreCase("highest")) {

            order = -1;

        } else if(sortBy.equalsIgnoreCase("lowest")) {

            order = 1;
        }

        else {

            throw new Exception("Invalid sorting critera");
        }

        String lookupPipeline = """
            {
                $lookup: {from: "comment", foreignField: "gid", localField: "gid",
                    pipeline:[
                        
                        {$sort: {rating: %d}}, {$limit: 1}
                        
                    ], 
                    as: "reviews"}
            }
                """.formatted(order);

        String lookupPipelineAlt = """
            {
                $lookup: {from:"comment", as: "reviews",let: {gid: "$gid"}, 
                pipeline:[
                    {$match: {$expr: {$eq:["$$gid","$gid"]}}},
                    {$sort:{rating: %d}},{$limit: 1}
                    ]}
            }
                """.formatted(order);

        LimitOperation limit = Aggregation.limit(250);

        UnwindOperation unwind = Aggregation.unwind("reviews");

        ProjectionOperation project = Aggregation.project("gid", "name", "reviews.rating", "reviews.user", "reviews.c_text", "reviews.c_id");

        TypedAggregation<Document> custom = Aggregation.newAggregation(Document.class, limit, new CustomAggregation(lookupPipelineAlt), unwind, project);

        AggregationResults<Document> results = mongoTemplate.aggregate(custom, "game", Document.class);

        return results.getMappedResults();

        
    }
    
}
