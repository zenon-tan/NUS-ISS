package practice.billboard_charts.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import practice.billboard_charts.service.SongService;

@RestController
@RequestMapping("/api/favorites")
public class FavoriteSongsRestController {

    @Autowired
    SongService sSrc;

    @GetMapping(path = "{username}")
    public ResponseEntity<String> getFavApi(@PathVariable String username) {

        return ResponseEntity
        .status(HttpStatus.OK)
        .contentType(MediaType.APPLICATION_JSON)
        .body(sSrc.getFavSongsJson(username).toString());

    }
    
}
