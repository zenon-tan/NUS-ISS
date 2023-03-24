package day27.workshop.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import day27.workshop.services.ReviewService;
import jakarta.json.JsonObject;

@RestController
@RequestMapping("/api")
public class ReviewRestController {

    @Autowired
    ReviewService rSrc;

    @GetMapping("/review/{id}")
    public ResponseEntity<String> getReviewById(@PathVariable(name = "id") String id) {

        Optional<JsonObject> result = rSrc.getReviewByIdJson(id);
        if(result.isPresent()) {

            return new ResponseEntity<>(result.get().toString(), HttpStatus.OK);
        }

        return new ResponseEntity<>("Review not found", HttpStatus.NOT_FOUND);
        

    }

    @GetMapping("/review/{id}/history")
    public ResponseEntity<String> getHistoryById(@PathVariable(name = "id") String id) {

        Optional<JsonObject> result = rSrc.getHistoryByIdJson(id);
        if(result.isPresent()) {

            return new ResponseEntity<>(result.get().toString(), HttpStatus.OK);
        }

        return new ResponseEntity<>("Review not found", HttpStatus.NOT_FOUND);
        

    }
    
}
