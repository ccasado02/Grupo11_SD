package com.urjc.grupo11.practica1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class BeatController {

    @Autowired
    private BeatService beats;
    @Autowired
    LicenseService licenses;
    @Autowired
    UserService users;

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
        @GetMapping("/beats/{id}")
    public String getLicensesByBeat(Model model, @PathVariable Long id){
        model.addAttribute("beat", beats.findById(id));
        List<User> u = new ArrayList<>();;
        List<License> l = licenses.findAll().stream()
            .filter(license -> license.getBeatId() == id)
            .collect(Collectors.toList());
        for(License li : l){
            u.add(users.findById(li.getUserId()));
        }
        model.addAttribute("users", u);
        model.addAttribute("licenses", l);
        return "beat";
    }
}