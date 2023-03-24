package day34.lecture.services;

import java.io.StringReader;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import day34.lecture.models.Article;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@Service
public class NewsService {

    public static final String NEWS_API = "https://newsapi.org/v2/top-headlines";

    @Value("${news.key}")
    private String newsKey;

    public List<Article> getNews(String country, String category, int pageSize) {

        String url = UriComponentsBuilder.fromUriString(NEWS_API)
        .queryParam("country", country)
        .queryParam("category", category)
        .queryParam("apiKey", "SET_THIS")
        .toUriString();

        RequestEntity<Void> req = RequestEntity.get(url)
        .accept(MediaType.APPLICATION_JSON).build();

        RestTemplate template = new RestTemplate();

        ResponseEntity<String> resp = null;

        try {
            resp = template.exchange(req, String.class);

        } catch (RestClientException e) {
            e.printStackTrace();
            return Collections.EMPTY_LIST;
        }

        JsonReader reader = Json.createReader(new StringReader(resp.getBody()));
        JsonObject newsResp = reader.readObject();
        JsonArray jsonArr = newsResp.getJsonArray("articles");

        return jsonArr.stream()
        .map(v -> v.asJsonObject())
        .map(Article::toArticle)
        .toList();


        
    }
    
}
