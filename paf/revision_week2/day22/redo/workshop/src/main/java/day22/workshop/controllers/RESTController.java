package day22.workshop.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import day22.workshop.models.Rsvp;
import day22.workshop.repositories.RsvpRepo;

@RestController
@RequestMapping("/api")
public class RESTController {

    @Autowired
    RsvpRepo rRepo;

    @GetMapping("/rsvps")
    public ResponseEntity<List<Rsvp>> getAllRsvps() {

        Optional<List<Rsvp>> results = rRepo.getAllRsvp();

        if(results.isEmpty()) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(results.get(), HttpStatus.OK);
        
    }
    
    @GetMapping("/rsvp")
    public ResponseEntity<List<Rsvp>> getRsvpsByName(@RequestParam(name = "q") String name) {

        Optional<List<Rsvp>> results = rRepo.getRsvpsByName(name);

        if(results.isEmpty()) {

            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);

        }

        return new ResponseEntity<>(results.get(), HttpStatus.OK);
    }

    @PostMapping("/rsvp")
    public ResponseEntity<String> upsertRsvp(@RequestBody Rsvp rsvp) {

        String result = rRepo.upsertRsvp(rsvp);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/rsvp/{phone}")
    public ResponseEntity<String> updateRsvp(@PathVariable(name = "phone") String phone, @RequestBody Rsvp rsvp) {
        rsvp.setPhone(phone);

        Boolean result = rRepo.updateRsvp(rsvp);
        if(result) {
            return new ResponseEntity<>("Rsvp Updated", HttpStatus.OK);
        }

        return new ResponseEntity<>("Rsvp update error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/rsvps/count")
    public ResponseEntity<String> countRsvps() {

        int count = rRepo.countRsvps();

        return new ResponseEntity<>("There are %d Rsvps".formatted(count), HttpStatus.OK);
    }
}
