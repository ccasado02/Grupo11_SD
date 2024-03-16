package com.urjc.grupo11.practica1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/users")
public class UserController{

    @Autowired
    UserService users;

    @PostMapping("/create")
    public String createUser(@ModelAttribute User user, Model model) {
        users.save(user);
        model.addAttribute("user", user);
        return "redirect:/";
    }
}
