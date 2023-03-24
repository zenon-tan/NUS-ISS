package practice.billboard_charts.service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import practice.billboard_charts.models.Song;
import practice.billboard_charts.models.SongList;

@Service
public class SongService {   

    @Autowired
    RedisTemplate<String, Object> redisTemplate;  

    private static final String RAPID_API_URL = "https://billboard3.p.rapidapi.com/hot-100";

    // Get json from api
    public Optional<SongList> getSongs(int number) throws IOException{

        // String loveUrl = UriComponentsBuilder.fromUriString(RAPID_API_URL)
        // .queryParam("fname", fname)
        // .queryParam("sname", sname)
        // .toUriString();

        //Set the headers you need send
        final HttpHeaders headers = new HttpHeaders();
        headers.add("X-RapidAPI-Key", System.getenv("LOVE_API_KEY"));
        // headers.add("X-RapidAPI-Key", "cc5f4a677dmsh26a78b42c78bcedp131fd5jsn549c873bbbd1");
        headers.add("X-RapidAPI-Host","billboard3.p.rapidapi.com");
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        // System.out.println(headers.toString());

        //Create a new HttpEntity
        final HttpEntity<String> entity = new HttpEntity<String>(headers);

        RestTemplate template = new RestTemplate();
        // System.out.println("OK");

        //Execute the method writing your HttpEntity to the request
        ResponseEntity<String> response = template.exchange(
            RAPID_API_URL, HttpMethod.GET, entity, String.class);  
        

        // check response
        if (response.getStatusCode() == HttpStatus.OK) {
            System.out.println("Request Successful.");
            // System.out.println(response.getBody());
        } else {
            System.out.println("Request Failed");
            System.out.println(response.getStatusCode());
        }

        SongList songList = SongList.createList(response.getBody());
        // System.out.println(songList.getSongList());
       saveToDataBase(songList);

        if(songList != null) {
            return Optional.of(songList);
        }

        return Optional.empty();
    }

    public void saveToDataBase(SongList songList) {

        for(Song song : songList.getSongList()) {

            JsonObject jsonobj = Json.createObjectBuilder()
            .add("artist", song.getArtist())
            .add("title", song.getTitle())
            .add("image", song.getImage())
            .add("rank", song.getRank())
            .build();
            
            // Save as json
            redisTemplate.opsForHash().put("hot100", song.getTitle(), jsonobj.toString());

            // Save as Song.class
            redisTemplate.opsForHash().put("hot100_Obj", song.getTitle(), song);
        }



    }

    public void saveFavSongs(String username, List<String> favSongs) {

        for (String songTitle : favSongs) {
            // Use the song titles to obtain the Song objects
            Song songObj = (Song) redisTemplate.opsForHash().get("hot100_Obj", songTitle);

            // Save the song objects to the db
            redisTemplate.opsForHash().put(username, songTitle, songObj);

        }
        
    }

    public List<Song> getFavSongs(String username) {
        Set<Object> favSongKeys = redisTemplate.opsForHash().keys(username);
        List<Song> favSongObjs = redisTemplate.opsForHash().multiGet(username, favSongKeys).stream()
        .filter(Song.class::isInstance)
        .map(Song.class::cast)
        .toList();

        return favSongObjs;
    }

    public JsonObject getFavSongsJson(String username) {

        // Get keys from hash
        Set<Object> favSongKeys = redisTemplate.opsForHash().keys(username);
        List<Song> favSongObjs = redisTemplate.opsForHash().multiGet(username, favSongKeys).stream()
        .filter(Song.class::isInstance)
        .map(Song.class::cast)
        .toList();

        JsonObjectBuilder resultObj = Json.createObjectBuilder();

        for(Song song : favSongObjs) {

            JsonObjectBuilder result = Json.createObjectBuilder()
            .add("artist", song.getArtist())
            .add("title", song.getTitle())
            .add("weeksAtNo1", song.getWeeksAtNo1())
            .add("lastWeek", song.getLastWeek());

            resultObj.add(song.getTitle(), result);

        }

        JsonObjectBuilder finalresult = Json.createObjectBuilder();

        finalresult.add(username, resultObj);

        return finalresult.build();

    }

    





    
}
