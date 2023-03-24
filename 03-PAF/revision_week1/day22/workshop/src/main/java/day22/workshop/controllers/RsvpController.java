package day22.workshop.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import day22.workshop.models.Rsvp;
import day22.workshop.repositories.RsvpRepo;

@RestController
@RequestMapping("/api/rsvps")
public class RsvpController {

    @Autowired
    RsvpRepo rRepo;

    @GetMapping("/")
    public ResponseEntity<List<Rsvp>> getAllRsvp(@RequestParam(name = "q", required = false) String name) {

        if(name == null) {

            List<Rsvp> results = rRepo.getAllRsvp();
            if(results.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    
            }
    
            return new ResponseEntity<>(results, HttpStatus.OK);

        } else {
            List<Rsvp> results = rRepo.getRsvpByName(name);
            if(results.isEmpty()) {

                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }

            return new ResponseEntity<>(results, HttpStatus.OK);

        }
    }

    @PostMapping("/")
    public ResponseEntity<String> addRsvp(@RequestBody Rsvp rsvp) {

        Boolean inserted = rRepo.addRsvp(rsvp);
        if(inserted) {
            return new ResponseEntity<>("Rsvp added", HttpStatus.CREATED);
        }

        return new ResponseEntity<>("Rsvp failed to add", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/")
    public ResponseEntity<String> updateRsvp(@RequestBody Rsvp rsvp) {

        Boolean updated = rRepo.updateRsvp(rsvp);
        if(updated) {
            return new ResponseEntity<>("Rsvp updated", HttpStatus.CREATED);
        }

        return new ResponseEntity<>("Rsvp failed to update", HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @PostMapping("/batch")
    public ResponseEntity<String> batchAddRsvp(@RequestBody List<Rsvp> rsvps) {

        int listSize = rsvps.size();
        int resultSize = rRepo.batchAddRsvp(rsvps).length;

        if(listSize == resultSize) {

            return new ResponseEntity<>("All entries have been inserted", HttpStatus.CREATED);

        }

        return new ResponseEntity<>("Error inserting entries", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    
}
