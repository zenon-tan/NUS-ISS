package workshop.server.repositories;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.LimitOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.SkipOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import com.mongodb.client.DistinctIterable;
import com.mongodb.client.MongoCursor;

@Repository
public class GameRepo {

    @Autowired
    MongoTemplate mongoTemplate;

    public List<Document> findAllDistinctGames(int limit, int skip) {

       ProjectionOperation project = Aggregation.project("name");
       LimitOperation limitOp = Aggregation.limit(limit);
       SkipOperation skipOp = Aggregation.skip(skip);

       Aggregation pipeline = Aggregation.newAggregation(project, skipOp, limitOp);

       AggregationResults<Document> results = mongoTemplate.aggregate(pipeline, "game", Document.class);

       return results.getMappedResults();


    }
    
}
