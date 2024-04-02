package com.urjc.grupo11.practica1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

// Class for combining License and Beat
class LicenciaYBeat {
    private License licencia;
    private Beat beat;

    public License getLicencia() {
        return licencia;
    }

    public Beat getBeat() {
        return beat;
    }

    public LicenciaYBeat(License license, Beat beat){
        this.licencia= license;
        this.beat= beat;
    }
}

// Controller class for License-related operations
@Controller
public class LicenseController{

    // Autowired services
    @Autowired LicenseService licenses;
    @Autowired BeatService beats;
    @Autowired UserService users;

    // GetMapping for retrieving all licenses
    @GetMapping("/licencias")
    public String getAllLicenses(Model model){
        List<License> l = licenses.findAll().stream().collect(Collectors.toList());
        List<String> licenseDetails = new ArrayList<>();
        for(License li : l){
            User user = users.findById(li.getUserId());
            Beat beat = beats.findById(li.getBeatId());
            if (user != null && beat != null) {
                String detail = user.getName() + " compra " + beat.getName() + " el día " + li.getBuyDate() + " con una licencia de uso " + li.getLicenseType();
                licenseDetails.add(detail);
            }
        }
        model.addAttribute("licenseDetails", licenseDetails);
        return "licenses";
    }

    // GetMapping for buying a beat
    @GetMapping("/beats/{id}/comprar")
    public String comprarBeat(Model model, @PathVariable Long id) {
        return "comprarbeat";
    }

    // PostMapping for buying a license for a beat
    @PostMapping("/beats/{id}/comprar")
    public String comprarLicencia(@PathVariable Long id, @RequestParam LICENSETYPE licenseType, HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return "redirect:/login";
        }
        License newLicense = new License(currentUser.getId(), id, LocalDate.now(), licenseType);
        licenses.save(newLicense);
        return "redirect:/beats/" + id;
    }

    @GetMapping("/usuarios/{id}/licencias")
    public String getLicenciasByUser(Model model, @PathVariable Long id, HttpSession session){
        User currentUser = (User) session.getAttribute("user");
        if(currentUser.getId() == id){
            List<License> licencias = licenses.findAll().stream()
                .filter(license -> license.getUserId().equals(id))
                .collect(Collectors.toList());
        
            // Crear una lista de objetos LicenciaYBeat
            List<LicenciaYBeat> licenciasYBeats = new ArrayList<>();
            for (License licencia : licencias) {
                Beat beat = beats.findById(licencia.getBeatId());
                licenciasYBeats.add(new LicenciaYBeat(licencia, beat));
            }
            model.addAttribute("user", currentUser);
            model.addAttribute("licenciasYBeats", licenciasYBeats);
            return "licenciasPorUsuario";
        }else{
            return "error";
        }
    }

    // GetMapping for retrieving a specific license
    @GetMapping("/licencias/{id}")
    public String getLicencia(Model model, @PathVariable Long id, HttpSession session){
        License licencia = licenses.findById(id);
        Beat beat = beats.findById(licencia.getBeatId());
        User currentUser = (User) session.getAttribute("user");
        model.addAttribute("user", currentUser);
        model.addAttribute("licencia", licencia);
        model.addAttribute("beat", beat);
        model.addAttribute("isCurrentUser", currentUser != null && currentUser.getId().equals(licencia.getUserId()));
        return "licencia";
    }

    // PostMapping for editing a license
    @PostMapping("/licencias/{id}/edit")
    public String editLicencia(Model model, @PathVariable Long id, @RequestParam LICENSETYPE licenseType, HttpSession session){
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null || !currentUser.getId().equals(licenses.findById(id).getUserId())) {
            return "redirect:/login";
        }
        
        License licencia = licenses.findById(id);
        licencia.setLicenseType(licenseType);
        licenses.save(licencia);
        return "redirect:/usuarios/" + currentUser.getId() + "/licencias";
    }

    // PostMapping for deleting a license
    @PostMapping("/licencias/{id}/del")
    public String delLicencia(@PathVariable Long id, HttpSession session){
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null || !currentUser.getId().equals(licenses.findById(id).getUserId())) {
            return "redirect:/login";
        }
        licenses.deleteById(id);
        return "redirect:/usuarios/" + currentUser.getId() + "/licencias";
    }
    
}