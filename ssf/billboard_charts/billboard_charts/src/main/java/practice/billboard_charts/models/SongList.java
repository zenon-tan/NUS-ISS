package practice.billboard_charts.models;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.InputStream;

import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonNumber;

public class SongList {

    @Autowired
    static
    RedisTemplate<String, Object> redisTemplate;
    
    @NotEmpty(message = "username must not be empty")
    @Size(min = 6, max = 18, message = "Username length must be between 6 to 18 characters")
    private String username;
    private List<Song> songList = new LinkedList<>();

    @Size(min = 3, message = "You must select at least 3 songs")
    @NotEmpty(message = "You must choose 3 favorite songs")
    private List<String> favSongs = new LinkedList<>();
    private int number;

    public List<Song> getSongList() {
        return songList;
    }

    public void setSongList(List<Song> songList) {
        this.songList = songList;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }    

    public List<String> getFavSongs() {
        return favSongs;
    }

    public void setFavSongs(List<String> favSongs) {
        this.favSongs = favSongs;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static SongList createList(String json) throws IOException {
        
        SongList sl = new SongList();

        // System.out.println(json);

        try(InputStream is = new ByteArrayInputStream(json.getBytes())) {
            
            JsonReader jr = Json.createReader(is);
            // JsonObject o = jr.readObject();
            JsonArray jsonarray = jr.readArray();

            for(int i = 0; i < jsonarray.size(); i++) {

                JsonObject obj = jsonarray.getJsonObject(i);
                Song song = new Song();

                // System.out.println(obj.getString("artist"));

                song.setArtist(obj.getString("artist"));
                song.setTitle(obj.getString("title"));
                song.setImage(obj.getString("image"));
                song.setRank(obj.getJsonNumber("rank").toString());

                JsonNumber weeks =obj.getJsonNumber("weeksAtNo1");
                if(weeks != null) {

                    song.setWeeksAtNo1(obj.getJsonNumber("weeksAtNo1").toString());

                } else {
                    song.setWeeksAtNo1("0");

                }
                
                song.setLastWeek(obj.getJsonNumber("lastWeek").toString());
                song.setChange(obj.getString("change"));
                song.setPeakPosition(obj.getJsonNumber("peakPosition").toString());

                // System.out.println(song.getArtist());
                
                sl.songList.add(song);

            }


            return sl;
        }

    }
    
}
