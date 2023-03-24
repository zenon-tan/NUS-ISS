package day26.workshop.utils;

import java.time.LocalDateTime;
import java.util.List;

import org.bson.Document;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

public class Converters {

    public static JsonObject toJson(List<Document> docs, int offset, int limit) {

        JsonArrayBuilder arr = Json.createArrayBuilder();

        int count = 0;

        for(Document doc : docs) {
            JsonObjectBuilder json = Json.createObjectBuilder()
            .add("gid", doc.getInteger("gid"))
            .add("name", doc.getString("name"))
            .add("year", doc.getInteger("year"))
            .add("ranking", doc.getInteger("ranking"))
            .add("users_rated", doc.getInteger("users_rated"))
            .add("url", doc.getString("url"))
            .add("image", doc.getString("image"));

            count += 1;

            arr.add(json);
        }

        JsonObjectBuilder json = Json.createObjectBuilder()
        .add("games", arr)
        .add("offset", offset)
        .add("limit", limit)
        .add("total", count)
        .add("timestamp", LocalDateTime.now().toString());

        return json.build();
    }

    public static JsonObject toJson(Document doc) {

        JsonObjectBuilder json = Json.createObjectBuilder()
            .add("gid", doc.getInteger("gid"))
            .add("name", doc.getString("name"))
            .add("year", doc.getInteger("year"))
            .add("ranking", doc.getInteger("ranking"))
            .add("users_rated", doc.getInteger("users_rated"))
            .add("url", doc.getString("url"))
            .add("image", doc.getString("image"))
            .add("timestamp", LocalDateTime.now().toString());

        return json.build();
    }
    
}
