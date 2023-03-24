package workshop.server.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import workshop.server.repositories.GameRepo;

@Service
public class GameService {

    @Autowired
    GameRepo gRepo;

    public JsonObject getGames(int limit, int skip) {

        List<Document> allGames = gRepo.findAllDistinctGames(limit, skip);

        JsonObjectBuilder json = Json.createObjectBuilder();
        JsonArrayBuilder arr = Json.createArrayBuilder();

        for(Document i : allGames) {
            arr.add(i.getString("name"));
        }

        return json.add("games", arr).build();

        
    }
    
}
