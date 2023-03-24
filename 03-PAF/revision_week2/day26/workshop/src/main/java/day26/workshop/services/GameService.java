package day26.workshop.services;

import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import day26.workshop.repositories.GameRepo;
import day26.workshop.utils.Converters;
import jakarta.json.JsonObject;

@Service
public class GameService {

    @Autowired
    GameRepo gRepo;

    public JsonObject getAllGames(int offset, int limit) {

        List<Document> result = gRepo.getAllGames(limit, offset);

        JsonObject json = Converters.toJson(result, offset, limit);

        return json;

    }

    public JsonObject getAllGamesSorted(int offset, int limit) {

        List<Document> result = gRepo.getAllGamesSorted(limit, offset);

        JsonObject json = Converters.toJson(result, offset, limit);

        return json;

    }

    public String getGameByid(int gid) {
        
        Optional<Document> result = gRepo.getGameById(gid);

        if(result.isPresent()) {

            JsonObject json = Converters.toJson(result.get());

            return json.toString();

        }

        return "Game not found";
    }
    
}
