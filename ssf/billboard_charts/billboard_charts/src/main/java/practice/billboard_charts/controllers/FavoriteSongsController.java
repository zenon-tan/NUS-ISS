package practice.billboard_charts.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import practice.billboard_charts.models.Song;
import practice.billboard_charts.models.SongList;
import practice.billboard_charts.service.SongService;

@Controller
@RequestMapping("/favorites")
public class FavoriteSongsController {

    @Autowired
    SongService sSrc;

    @GetMapping
    public String addFavorites(Model model, @RequestParam(required = false) String number) throws IOException {

        if(number == null) {

            number = "100";

        }

        Optional<SongList> songList = sSrc.getSongs(Integer.parseInt(number));
        model.addAttribute("songlist", songList.get().getSongList());
        model.addAttribute("favorite", songList);
        return "favoritecharts";
    }

    @PostMapping
    public String saveFavorites(Model model, @Valid @ModelAttribute("favorite") SongList songList, BindingResult binding) throws IOException {

        // @ModelAttribute("favorite") binds the favorite from ^ (and from the html page) to the object here

        if(binding.hasErrors()) {

            // Regenerate the table of 100 songs
            Optional<SongList> songList2 = sSrc.getSongs(100);
            model.addAttribute("songlist", songList2.get().getSongList());
            return "favoritecharts";

        }

        sSrc.saveFavSongs(songList.getUsername(), songList.getFavSongs());

        List<Song> favSongs = sSrc.getFavSongs(songList.getUsername());
        model.addAttribute("username", songList.getUsername());
        model.addAttribute("favorite", favSongs);

        return "result";
    }

    @GetMapping(path = "{username}")
    public String showFavByUser(Model model, @PathVariable String username, SongList songList) {

        List<Song> favSongs = sSrc.getFavSongs(songList.getUsername());
        model.addAttribute("username", songList.getUsername());
        model.addAttribute("favorite", favSongs);

        return "result";

    }

}
