package day27.workshop.services;

import java.util.Optional;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import day27.workshop.models.Review;
import day27.workshop.repositories.ReviewRepo;
import day27.workshop.utils.Converters;
import jakarta.json.JsonObject;

@Service
public class ReviewService {

    @Autowired
    ReviewRepo rRepo;

    public String insertReview(Review review) {

        Optional<String> game = rRepo.getGameById(review.getGId());

        if(game.isPresent()) {
            String result = rRepo.insertReview(Converters.toDocument(review, game.get()));
            return "Your review (%s) has been added".formatted(result);
        }

        return "Game not found";
        
    }

    public String updateReview(Review newReview, String oId) {

        Optional<Document> result = rRepo.getReviewByoId(oId);

        if(result.isPresent()) {

            String output = rRepo.updateReview(newReview, oId, result.get());

            return output;

        }

        return "Review not found";
    }

    public Review getReviewById(String oId) {

        Optional<Document> result = rRepo.getReviewByoId(oId);
        if(result.isPresent()) {

            return Converters.toReviewObj(result.get());
        }

        return null;

    }

    public Optional<JsonObject> getReviewByIdJson(String oId) {

        Optional<Document> result = rRepo.getReviewByoId(oId);
        if(result.isPresent()) {

            Boolean isEdited = rRepo.checkIfEdited(oId);

            return Optional.of(Converters.toJson(result.get(), isEdited));
        }

        return Optional.empty();

    }

    public Optional<JsonObject> getHistoryByIdJson(String oId) {

        Optional<Document> result = rRepo.getReviewByoId(oId);
        if(result.isPresent()) {

            return Optional.of(Converters.toJson(result.get()));
        }

        return Optional.empty();

    }
    
}
