package com.urjc.grupo11.practica1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Controller
public class BeatController {

    @Autowired
    private BeatService beats;
    @Autowired
    LicenseService licenses;
    @Autowired
    UserService users;

    public String normalizeTag(String tag) {
        return tag.toLowerCase().replaceAll("\\s+", "");
    }

    public Set<String> tokenize(String query) {
        Set<String> queryTags = new HashSet<>(Arrays.asList(query.toLowerCase().split("\\s+")));
        return correctTags(queryTags.stream()
            .map(this::normalizeTag)
            .collect(Collectors.toSet()));
    }

    public Set<String> correctTags(Set<String> queryTags) {
        Set<String> correctedTags = new HashSet<>();
        for (String queryTag : queryTags) {
            String closestExistingTag = findClosestTag(queryTag);
            correctedTags.add(closestExistingTag);
        }
        return correctedTags;
    }

    public Set<String> getAllExistingTags() {
        Set<String> allTags = new HashSet<>();
        for (Beat beat : beats.findAll()) {
            allTags.addAll(beat.getTags());
        }
        return allTags;
    }

    public String findClosestTag(String queryTag) {
        int minDistance = Integer.MAX_VALUE;
        String closestTag = null;
        for (String existingTag : getAllExistingTags()) {
            int distance = computeLevenshteinDistance(queryTag, existingTag);
            if (distance < minDistance) {
                minDistance = distance;
                closestTag = existingTag;
            }
        }
        return closestTag;
    }

    public int computeLevenshteinDistance(String a, String b) {
        int[][] dp = new int[a.length() + 1][b.length() + 1];

        for (int i = 0; i <= a.length(); i++) {
            for (int j = 0; j <= b.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else {
                    dp[i][j] = min(dp[i - 1][j - 1] + costOfSubstitution(a.charAt(i - 1), b.charAt(j - 1)), dp[i - 1][j] + 1, dp[i][j - 1] + 1);
                }
            }
        }

        return dp[a.length()][b.length()];
    }

    public int costOfSubstitution(char a, char b) {
        return a == b ? 0 : 1;
    }

    public int min(int... numbers) {
        return Arrays.stream(numbers).min().orElse(Integer.MAX_VALUE);
    }

    public Collection<Beat> findByTagsContaining(String tag) {
        String normalizedTag = normalizeTag(tag);
        return beats.findAll().stream()
            .filter(beat -> beat.getTags().stream()
                .map(this::normalizeTag)
                .collect(Collectors.toSet())
                .contains(normalizedTag))
            .collect(Collectors.toList());
    }
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
    @GetMapping("/beats/{id}")
    public String getLicensesByBeat(Model model, @PathVariable Long id, HttpSession session){
        model.addAttribute("beat", beats.findById(id));
        User currentUser = (User) session.getAttribute("user");
        List<User> u = new ArrayList<>();;
        List<License> l = licenses.findAll().stream()
            .filter(license -> license.getBeatId() == id)
            .collect(Collectors.toList());
        for(License li : l){
            u.add(users.findById(li.getUserId()));
        }
        model.addAttribute("user", (currentUser!= null && currentUser.getId()!=beats.findById(id).getProducerID() && !u.contains(currentUser)));
        model.addAttribute("users", u);
        model.addAttribute("licenses", l);
        model.addAttribute("isCurrentUser", (currentUser!= null && currentUser.getId()==beats.findById(id).getProducerID()));
        return "beat";
    }
    @GetMapping("/buscar")
    public String search(@RequestParam("q") String query, Model model) {
        Set<String> queryTags = tokenize(query);
        List<Beat> beatList = new ArrayList<>();
        for (String tag : queryTags) {
            beatList.addAll(findByTagsContaining(tag));
        }
        Set<Beat> returnSet = new HashSet<>(beatList);
        model.addAttribute("beats", returnSet);
        return "buscar";
    }

    @PostMapping("/beat/{id}/del")
    public String deleteBeat(@PathVariable Long id, HttpSession session){
        User currentUser = (User) session.getAttribute("user");
        if(beats.findById(id).getProducerID().equals(currentUser.getId())){
            beats.deleteById(id);
            return "redirect:/beats";
        }else{
            return "redirect:/";
        }
    }

    @GetMapping("/vender")
    public String showCreateBeatForm(HttpSession session, Model model) {
        model.addAttribute("user", session.getAttribute("user"));
        return "vender";
    }

    @PostMapping("/vender")
    public String createBeat(@RequestParam String beatName, @RequestParam Double price, @RequestParam String description, @RequestParam String url, @RequestParam(required = false) List<String> mood, @RequestParam(required = false) List<String> genre, @RequestParam String tags, HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        if(currentUser != null){
            Set<String> tagSet = new HashSet<>(Arrays.asList(tags.split("\\s+")));
            Beat newBeat = new Beat(beatName, GENERO.valueOf(genre.get(0).toUpperCase()), description, url, price, tagSet, currentUser.getId());
            beats.save(newBeat);
        }
        return "redirect:/beats";
    }

    @GetMapping("/beats/{id}/editar")
    public String changePrice(@RequestParam Long id, Model model, HttpSession session) {
        return "editbeat";
    }

    @PostMapping("/beats/{id}/editar")
    public String postMethodName(@RequestParam Double price, @PathVariable Long id) {
        Beat newBeat = beats.findById(id);
        newBeat.setPrice(price);
        beats.save(newBeat);
        return "redirect:/beats/{id}";
    }
    
    
}