package com.urjc.grupo11.practica1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SignUpController {

    @Autowired
    private UserService userService;

    @GetMapping("/registrate")
    public String signUpForm() {
        return "SignUp";
    }

    @PostMapping("/signup")
    public String signUp(@RequestParam String username, @RequestParam String password, @RequestParam String email, RedirectAttributes redirectAttributes) {
        User newUser = new User(username, password, email);
        if ((userService.findByName(username))!=null || (userService.findByEmail(email))!=null) {
            redirectAttributes.addFlashAttribute("error", "Usuario o email existentes");
            return "redirect:/registrate";
        }
        else{
            userService.save(newUser);
            redirectAttributes.addFlashAttribute("nombre", username);
            redirectAttributes.addFlashAttribute("contrasena", password);
            return "redirect:/signin";
        }
    }
}
