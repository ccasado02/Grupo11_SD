package com.urjc.grupo11.practica1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class BeatController {

    @Autowired
    private BeatService beats;


    @SuppressWarnings("null")
    @GetMapping("/beats")
    public String getBeats(Model model) {
        for (GENERO genre : GENERO.values()) {
            List<Beat> beatsByGenre = beats.findAll().stream()
                    .filter(beat -> beat.getGenre() == genre)
                    .collect(Collectors.toList());
            model.addAttribute(genre.name().toLowerCase(), beatsByGenre);
        }
        return "comprar";
    }
}