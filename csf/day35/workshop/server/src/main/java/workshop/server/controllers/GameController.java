package workshop.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.json.JsonObject;
import workshop.server.services.GameService;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api")
public class GameController {

    @Autowired
    GameService gSrc;

    @ResponseBody
    @GetMapping(path = "/games")
    public ResponseEntity<String> getGames(@RequestParam(defaultValue = "10") int limit, @RequestParam(defaultValue = "0") int skip) {

        JsonObject result = gSrc.getGames(limit, skip);
        return new ResponseEntity<String>(result.toString(), HttpStatus.OK);

    }
    
}
