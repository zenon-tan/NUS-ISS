package day28.workshop.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import day28.workshop.services.ReviewService;
import jakarta.json.JsonObject;

@RestController
@RequestMapping
public class ReviewController {

    @Autowired
    ReviewService rSrc;

    @GetMapping("/game/{id}/reviews")
    public ResponseEntity<String> getReviewsByGid(@PathVariable(name = "id") int id) {
        
        Optional<JsonObject> json = rSrc.getReviewsByGid(id);

        if(json.isPresent()) {

            return new ResponseEntity<>(json.toString(), HttpStatus.OK);
        }

        return new ResponseEntity<>("Game not found", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/games/{sort}")
    public ResponseEntity<String> getSortedReviews(@PathVariable(name = "sort") String sort) {
        
        JsonObject result = rSrc.getSortedReviews(sort);
        if(result == null) {
            return new ResponseEntity<>("Invalid sorting critera", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(result.toString(), HttpStatus.OK);
    }
    
}
