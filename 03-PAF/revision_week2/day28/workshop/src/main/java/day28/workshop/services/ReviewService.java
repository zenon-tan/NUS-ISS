package day28.workshop.services;

import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import day28.workshop.repositories.ReviewRepo;
import day28.workshop.utils.Converters;
import jakarta.json.Json;
import jakarta.json.JsonObject;

@Service
public class ReviewService {

    @Autowired
    ReviewRepo rRepo;

    public Optional<JsonObject> getReviewsByGid(int gid) {

        Optional<Document> result = rRepo.getReviewsByGid(gid);

        if(result.isPresent()) {

            Double avg = rRepo.getReviewAverageByGid(gid);
            JsonObject json = Converters.toJson(result.get(), avg);

            return Optional.of(json);

        }

        return Optional.empty();
    }

    public JsonObject getSortedReviews(String sortedBy) {

        List<Document> results;
        try {
            results = rRepo.getAllReviewsSorted(sortedBy);
            JsonObject json = Converters.toJson(results, sortedBy);

            return json;

        } catch (Exception e) {
            
            e.printStackTrace();
            return null;
        }

    }
    
}
