package day27.workshop.repositories;

import java.time.LocalDateTime;
import java.util.Optional;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongodb.client.result.UpdateResult;

import day27.workshop.models.Review;
import day27.workshop.utils.Converters;

@Repository
public class ReviewRepo {

    @Autowired
    MongoTemplate mongoTemplate;

    public String insertReview(Document doc) {

        Document newDoc = mongoTemplate.insert(doc, "reviews");
        // System.out.println(newDoc.toString());
        ObjectId id = newDoc.getObjectId("_id");

        return id.toString();
        
    }

    public Optional<String> getGameById(int gId) {

        try {

            Document result = mongoTemplate.find(new Query(Criteria.where("gid").is(gId)), Document.class, "game").get(0);
            String game = result.getString("name");

            return Optional.of(game);
            
        } catch (Exception e) {

            return Optional.empty();
        }

    }

    public Optional<Document> getReviewByoId(String oId) {

        try {
            Document result = mongoTemplate.find(new Query(Criteria.where("_id").is(oId)), Document.class, "reviews").get(0);
            return Optional.of(result);

        } catch (Exception e) {

            return Optional.empty();
        }
    }
    

    public String updateReview(Review newReview, String oId, Document oldReview) {

        Query query = Query.query(Criteria.where("_id").is(oId));

        // Update the old review
        Update updateOps = new Update()
        .set("comment", newReview.getComment())
        .set("rating", newReview.getRating())
        .set("posted", LocalDateTime.now().toString())
        .push("edited", Converters.toDocument(oldReview));

        UpdateResult updateResult = mongoTemplate.updateMulti(query, updateOps, Document.class, "reviews");

        return "Review %s updated: %d".formatted(oId, updateResult.getModifiedCount());
    }

    public Boolean checkIfEdited(String oId) {

        Document result = getReviewByoId(oId).get();
        try {

            if(result.getList("edited", Document.class).isEmpty()) {
                return false;
            }

            return true;
            
        } catch (Exception e) {

            return false;
        }

    }
    
}
