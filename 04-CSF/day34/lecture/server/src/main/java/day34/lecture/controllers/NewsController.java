package day34.lecture.controllers;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import day34.lecture.models.Article;
import day34.lecture.services.NewsService;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(path="/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class NewsController {

    private Logger logger = Logger.getLogger(NewsController.class.getName());

    @Autowired
    private NewsService newsSrv;

    @GetMapping(path="/news/{country}/{category}")
    // ResponseBody tells spring boot to return the body itself, not the view
    // RESTController contains controller and responsebody
    @ResponseBody
    public ResponseEntity<String> getNews(@PathVariable String country, 
    @PathVariable String category, @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {

        logger.log(Level.INFO, "country: %s, category: %s".formatted(country, category));

        List<Article> articles = newsSrv.getNews(country, category, pageSize);

        JsonArrayBuilder jArr = Json.createArrayBuilder();
        articles.stream().forEach(v -> jArr.add(Article.toJson(v)));

        return ResponseEntity.ok(jArr.build().toString());
    }
    
}
