package practice.billboard_charts.controllers;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import practice.billboard_charts.models.SongList;
import practice.billboard_charts.service.SongService;

@Controller
@RequestMapping("/songs")
public class SongController {

    @Autowired
    SongService sSrc;

    @GetMapping
    public String showChartsByNumber(Model model, @RequestParam(required = false) String number) throws IOException {

        if(number == null) {

            number = "100";

        }

        model.addAttribute("top", number);

        Optional<SongList> songList = sSrc.getSongs(Integer.parseInt(number));
        model.addAttribute("songlist", songList.get().getSongList());
        return "charts";
    }

}
