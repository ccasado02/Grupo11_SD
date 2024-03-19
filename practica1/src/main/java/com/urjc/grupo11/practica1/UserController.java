package com.urjc.grupo11.practica1;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class UserController{

    @Autowired
    UserService users;


    @GetMapping("/usuarios")
    public String getAllUsers(Model model){
        model.addAttribute("users", users.findAll().stream().collect(Collectors.toList()));
        return "users";
    }
    
    @GetMapping("/usuarios/{id}")
    public String getUser(@PathVariable Long id, Model model){
        User user = users.findById(id);
        model.addAttribute("user", user);
        return "user";
    }

    @PostMapping("/usuarios/{id}/del")
    public String deleteUser(@PathVariable Long id){
        users.deleteById(id);
        return "redirect:/usuarios";
    }

    
}
