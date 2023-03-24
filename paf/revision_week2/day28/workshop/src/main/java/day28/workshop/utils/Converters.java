package day28.workshop.utils;

import java.time.LocalDateTime;
import java.util.List;

import org.bson.Document;
import org.springframework.cglib.core.Local;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

public class Converters {

    public static JsonObject toJson(Document doc, Double average) {

        JsonObjectBuilder json = Json.createObjectBuilder();
        JsonArrayBuilder arr = Json.createArrayBuilder();

        for (Document d : doc.getList("reviews", Document.class)) {

            String format = "/review/%s".formatted(d.getString("c_id"));
            arr.add(format);
        }

        json.add("game_id", doc.getInteger("gid"))
        .add("name", doc.getString("name"))
        .add("year", doc.getInteger("year"))
        .add("rank", doc.getInteger("ranking"))
        .add("average", average)
        .add("users_rated", doc.getInteger("users_rated"))
        .add("url", doc.getString("url"))
        .add("thumbnail", doc.getString("image"))
        .add("reviews", arr)
        .add("timestamp", LocalDateTime.now().toString());

        return json.build();
        
    }

    public static JsonObject toJson(List<Document> docs, String sortBy) {

        JsonObjectBuilder json = Json.createObjectBuilder();
        JsonArrayBuilder arr = Json.createArrayBuilder();

        for (Document d : docs) {

            JsonObjectBuilder jj = Json.createObjectBuilder();

            jj
            .add("_id", d.getInteger("gid"))
            .add("name", d.getString("name"))
            .add("rating", d.getInteger("rating"))
            .add("user", d.getString("user"))
            .add("comment", d.getString("c_text"))
            .add("review_id", d.getString("c_id"));

            arr.add(jj);
        }

        json.add("rating", sortBy).add("games", arr)
        .add("timestamp", LocalDateTime.now().toString());

        return json.build();
        
    }
    
}
