package day26.workshop.repositories;

import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class GameRepo {

    @Autowired
    MongoTemplate mongoTemplate;

    public List<Document> getAllGames(int limit, int offset) {

        return mongoTemplate.find(new Query().limit(limit).skip(offset), Document.class, "game");
        
    }

    public List<Document> getAllGamesSorted(int limit, int offset) {

        return mongoTemplate.find(new Query().limit(limit).skip(offset).with(Sort.by(Sort.Direction.ASC, "ranking")), Document.class, "game");
        
    }

    public Optional<Document> getGameById(int gid) {

        try {

            Document result = mongoTemplate.find(new Query(Criteria.where("gid").is(gid)), 
            Document.class, "game").get(0);

            return Optional.of(result);

            
        } catch (Exception e) {
            return Optional.empty();
        }
    }
    
}
