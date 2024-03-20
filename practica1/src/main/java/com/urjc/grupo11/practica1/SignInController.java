package com.urjc.grupo11.practica1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SignInController {

    @Autowired
    private UserService userService;

    @GetMapping("/iniciasesion")
    public String aaaa(){
        return "SignIn";
    }

    @GetMapping("/signin")
    public String signIn(@RequestParam(required=false) String username, @RequestParam(required=false) String password, RedirectAttributes redirectAttributes, Model model) {
        if (username==null || password == null){
            username = (String) redirectAttributes.getFlashAttributes().get("nombre");
            password = (String) redirectAttributes.getFlashAttributes().get("contrasena");
        }
        User user = userService.findByName(username);
        if (user != null && user.getPassword().equals(password)) {
            return "redirect:/";
        } else {
            redirectAttributes.addFlashAttribute("error", "Usuario o contraseña incorrectos");
            return "redirect:/iniciasesion";
        }
    }
}
