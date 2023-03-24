package day27.workshop.utils;

import java.time.LocalDateTime;

import org.bson.Document;

import day27.workshop.models.Review;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

public class Converters {

    public static Document toDocument(Review review, String game) {

        Document doc = new Document();
        doc.append("user", review.getName())
        .append("rating", review.getRating())
        .append("comment", review.getComment())
        .append("ID", review.getGId())
        .append("posted", LocalDateTime.now().toString())
        .append("name", game);

        return doc;
        
    }

    public static Document toDocument(Document oldReview) {

        Document doc = new Document();

        doc
        .append("comment", oldReview.getString("comment"))
        .append("rating", oldReview.getInteger("rating"))
        .append("posted", oldReview.getString("posted"));

        return doc;
        
    }

    public static Review toReviewObj(Document doc) {

        Review review = new Review();
        review.setName(doc.getString("user"));
        review.setRating(doc.getInteger("rating"));
        review.setComment(doc.getString("comment"));
        review.setGId(doc.getInteger("ID"));

        return review;
    }

    public static JsonObject toJson(Document doc, Boolean isEdited) {

        JsonObjectBuilder json = Json.createObjectBuilder()
        .add("user", doc.getString("user"))
        .add("rating", doc.getInteger("rating"))
        .add("comment", doc.getString("comment"))
        .add("ID", doc.getInteger("ID"))
        .add("posted", doc.getString("posted"))
        .add("name", doc.getString("name"))
        .add("edited", isEdited)
        .add("timestamp", LocalDateTime.now().toString());

        return json.build();
    }

    public static JsonObject toJson(Document doc) {

        JsonArrayBuilder arr = Json.createArrayBuilder();

        for(Document d : doc.getList("edited", Document.class)) {

            JsonObjectBuilder jj = Json.createObjectBuilder()
            .add("comment", d.getString("comment"))
            .add("rating", d.getInteger("rating"))
            .add("posted", d.getString("posted"));

            arr.add(jj);
        }

        JsonObjectBuilder json = Json.createObjectBuilder()
        .add("user", doc.getString("user"))
        .add("rating", doc.getInteger("rating"))
        .add("comment", doc.getString("comment"))
        .add("ID", doc.getInteger("ID"))
        .add("posted", doc.getString("posted"))
        .add("name", doc.getString("name"))
        .add("edited", arr)
        .add("timestamp", LocalDateTime.now().toString());

        return json.build();


    }

    
}
