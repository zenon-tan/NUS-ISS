package day26.workshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import day26.workshop.services.GameService;
import jakarta.json.JsonObject;

@RestController
@RequestMapping
public class GameController {

    @Autowired
    GameService gSrc;

    @GetMapping("/games")
    public ResponseEntity<String> getAllGames(@RequestParam(name = "limit", defaultValue = "25") int limit, 
    @RequestParam(name = "offset", defaultValue = "0") int offset) {

        return new ResponseEntity<>(gSrc.getAllGames(offset, limit).toString(), HttpStatus.OK);

    }

    @GetMapping("/games/rank")
    public ResponseEntity<String> getAllGamesSorted(@RequestParam(name = "limit", defaultValue = "25") int limit, 
    @RequestParam(name = "offset", defaultValue = "0") int offset) {

        return new ResponseEntity<>(gSrc.getAllGamesSorted(offset, limit).toString(), HttpStatus.OK);

    }
    
    @GetMapping("/game/{id}")
    public ResponseEntity<String> getGameById(@PathVariable(name = "id") int gid) {

        return new ResponseEntity<>(gSrc.getGameByid(gid), HttpStatus.OK);

        
    }
}
