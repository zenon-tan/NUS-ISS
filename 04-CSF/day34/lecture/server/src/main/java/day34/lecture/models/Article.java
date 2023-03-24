package day34.lecture.models;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {

    private String author;
    private String title;
    private String description;

    public static Article toArticle(JsonObject json) {

        Article article = new Article();
        article.setAuthor(json.getString("author"));
        article.setTitle(json.getString("title"));
        article.setDescription(json.getString("description"));

        return article;
    }

    public static JsonObject toJson(Article article) {
        return Json.createObjectBuilder()
        .add("author", article.getAuthor())
        .add("title", article.getTitle())
        .add("description", article.getDescription())
        .build();
    }


    
}
