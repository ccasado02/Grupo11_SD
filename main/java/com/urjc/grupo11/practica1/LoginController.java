package com.urjc.grupo11.practica1;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class LoginController {
    @GetMapping("/login")
    public String pruebaMustache(Model model){
        model.addAttribute("prueba", "Hola");
        return "LogIn";
    }
}
