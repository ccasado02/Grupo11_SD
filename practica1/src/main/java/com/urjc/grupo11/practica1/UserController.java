package com.urjc.grupo11.practica1;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;

// Controller for managing user-related operations
@Controller
public class UserController {

    @Autowired 
    UserService users;
    @Autowired 
    BeatService beats;
    @Autowired 
    LicenseService licenses;

    // Method to display the home page
    @GetMapping("/")
    public String home(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        return "index";
    }

    // Method to get all users
    @GetMapping("/usuarios")
    public String getAllUsers(Model model){
        model.addAttribute("users", users.findAll().stream().collect(Collectors.toList()));
        return "users";
    }

    // Method to display the form for editing user's name
    @GetMapping("/usuarios/{id}/editname")
    public String editUserName(@PathVariable Long id, HttpSession session, Model model){
        User user = users.findById(id);
        User currentUser = (User) session.getAttribute("user");
        if (currentUser != null && currentUser.getId().equals(user.getId())) {
            model.addAttribute("user", user);
            return "editname";
        } else {
            return "redirect:/";
        }
    }

    // Method to update user's name
    @PostMapping("/usuarios/{id}/editname")
    public String updateUserName(@PathVariable Long id, @RequestParam String newname, HttpSession session, RedirectAttributes redirectAttributes){
        if (users.findByName(newname)==null){
            User currentUser = (User) session.getAttribute("user");
            User newUser = new User(newname, currentUser.getPassword(), currentUser.getEmail());
            newUser.setId(currentUser.getId());
            users.save(newUser);
            session.setAttribute("user", newUser);
            return "redirect:/usuarios/{id}";
        } else {
            redirectAttributes.addFlashAttribute("error", "Usuario o contraseña incorrectos");
            return "redirect:/usuarios/{id}/editname";
        }
    }
        
    // Method to display user's details
    @GetMapping("/usuarios/{id}")
    public String getUser(@PathVariable Long id, HttpSession session, Model model){
        User user = users.findById(id);
        User currentUser = (User) session.getAttribute("user");
        model.addAttribute("user", currentUser);
        model.addAttribute("usuario", user);
        model.addAttribute("isCurrentUser", currentUser != null && currentUser.getId().equals(user.getId()));
        return "user";
    }
    
    // Method to handle user logout
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    // Method to display user transfer page
    @GetMapping("/usuarios/{id}/del")
    public String deleteUser(@PathVariable Long id, HttpSession session, Model model){
        User currentUser = (User) session.getAttribute("user");
        if(currentUser != null && currentUser.getId().equals(id)){
            model.addAttribute("user", currentUser);
            model.addAttribute("isCurrentUser", (currentUser!= null && currentUser.getId()==beats.findById(id).getProducerID()));
            model.addAttribute("usersList", users.getUsers()); 
            return "transfer";
        } else {
            return "redirect:/";
        }
    }

    // Method to transfer user property to another user
    @PostMapping("/usuarios/{id}/transfer")
    public String transferProperty(@PathVariable Long id, @RequestParam Long newOwnerId, HttpSession session){
        User currentUser = (User) session.getAttribute("user");
        if(currentUser != null && currentUser.getId().equals(id)){
            // Transfer all beats from the deleting user to the selected user
            beats.transferBeats(id, newOwnerId);

            // Delete all licenses that the selected user has bought from the deleting user
            List<License> licensesToDelete = licenses.findAll().stream()
                .filter(license -> license.getUserId()==newOwnerId && beats.findById(license.getBeatId()).getProducerID() == id)
                .collect(Collectors.toList());
            for (License license : licensesToDelete) {
                licenses.deleteById(license.getId());
            }

            // Delete all licenses of the beats that were owned by the deleting user
            List<License> licensesOfDeletedUser = licenses.findAll().stream()
                .filter(license -> beats.findById(license.getBeatId()).getProducerID().equals(id))
                .collect(Collectors.toList());
            for (License license : licensesOfDeletedUser) {
                licenses.deleteById(license.getId());
            }
            logout(session);
            users.deleteById(id);
        }
        return "redirect:/usuarios";
    }

    // Method to delete user
    @PostMapping("/usuarios/{id}/borrar")
    public String deleteProperty(@PathVariable Long id, HttpSession session){
        User currentUser = (User) session.getAttribute("user");
        if(currentUser != null && currentUser.getId().equals(id)){
            // Find and delete all licenses of beats produced by the current user
            List<License> licensesToDelete = licenses.findAll().stream()
                .filter(license -> beats.findById(license.getBeatId()).getProducerID().equals(id))
                .collect(Collectors.toList());
            for (License license : licensesToDelete) {
                licenses.deleteById(license.getId());
            }

            // Find and delete all beats of the current user
            List<Beat> beatsToDelete = beats.findAll().stream()
                .filter(beat -> beat.getProducerID().equals(id))
                .collect(Collectors.toList());
            for (Beat beat : beatsToDelete) {
                beats.deleteById(beat.getId());
            }

            // Invalidate session and delete user
            logout(session);
            users.deleteById(id);
        }
        return "redirect:/";  // Redirect to home page
    }

    // Method to display sign in form
    @GetMapping("/iniciasesion")
    public String signInForm(){
        return "SignIn";
    }

    // Method to handle user sign in
    @GetMapping("/signin")
    public String signIn(@RequestParam(required=false) String username, @RequestParam(required=false) String password, RedirectAttributes redirectAttributes, HttpSession session, Model model) {
        if (username==null || password == null){
            username = (String) session.getAttribute("nombre");
            password = (String) session.getAttribute("contrasena");
            }
            User user = users.findByName(username);
            if (user != null && user.getPassword().equals(password)) {
                session.setAttribute("user", user); // Set the user in session
                return "redirect:/";
            } else {
                redirectAttributes.addFlashAttribute("error", "Usuario o contraseña incorrectos");
                return "redirect:/iniciasesion";
            }
        }

    // Method to display sign up form
    @GetMapping("/registrate")
    public String signUpForm() {
        return "SignUp";
    }

    // Method to handle user sign up
    @PostMapping("/signup")
    public String signUp(@RequestParam String username, @RequestParam String password, @RequestParam String email, RedirectAttributes redirectAttributes, HttpSession session) {
        User newUser = new User(username, password, email);
        if ((users.findByName(username))!=null || (users.findByEmail(email))!=null) {
            if(users.correctEmailFormat(email)){
                redirectAttributes.addFlashAttribute("error", "Usuario o email existentes");
                return "redirect:/registrate";
            } else {
                redirectAttributes.addFlashAttribute("error", "Email incorrecto");
                return "redirect:/registrate";              
            }
        } else {
            if(users.correctEmailFormat(email)){
                users.save(newUser);
                session.setAttribute("user", newUser); // Set the user in session
                return "redirect:/";
            } else {
                redirectAttributes.addFlashAttribute("error", "Email incorrecto");
                return "redirect:/registrate";          
            }
        }
    }   
}