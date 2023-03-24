package day22.workshop.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import day22.workshop.models.Rsvp;
import day22.workshop.repo.RsvpRepo;

@RestController
@RequestMapping("/api")
public class RsvpRestController {

    @Autowired
    RsvpRepo rRepo;

    @GetMapping("/rsvps")
    public ResponseEntity<List<Rsvp>> getAllRsvp() {

        List<Rsvp> result = rRepo.getAllRsvp();

        if(!result.isEmpty()) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        
    }

    @GetMapping("/rsvp")
    public ResponseEntity<List<Rsvp>> getRsvpsByName(@RequestParam(name = "q") String name) {

        List<Rsvp> result = rRepo.getRsvpByName(name);
        if(!result.isEmpty()) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/rsvp")
    public ResponseEntity<String> upsertRsvp(@RequestBody Rsvp rsvp) {

        Optional<Rsvp> query = rRepo.getRsvpById(rsvp.getId());

        if(query.isPresent()) {
            Boolean added = rRepo.updateRsvp(rsvp);
            return new ResponseEntity<>("Rsvp updated", HttpStatus.CREATED);
            
        } else if(query.isEmpty()) {

            rRepo.addRsvp(rsvp);
            return new ResponseEntity<>("Rsvp added", HttpStatus.OK);

        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @GetMapping("/rsvps/count")
    public ResponseEntity<String> getRsvpCount() {
        int count = rRepo.countRsvp();
        return new ResponseEntity<>("There are %d rsvp".formatted(count), HttpStatus.OK);
    }


    
}
