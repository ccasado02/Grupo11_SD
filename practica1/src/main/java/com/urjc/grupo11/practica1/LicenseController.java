package com.urjc.grupo11.practica1;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class LicenseController{

    @Autowired LicenseService licenses;
    @Autowired BeatService beats;
    @Autowired UserService users;

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
}
