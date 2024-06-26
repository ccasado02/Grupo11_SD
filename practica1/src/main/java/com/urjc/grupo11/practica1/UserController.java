package com.urjc.grupo11.practica1;

import java.util.HashSet;
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
import java.util.logging.Logger;

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
    public String getAllUsers(Model model, HttpSession session){
        User currentUser = (User) session.getAttribute("user");
        model.addAttribute("user", currentUser);
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
            User newUser = new User(newname, currentUser.getPassword(), currentUser.getEmail(), currentUser.getLicenses(), currentUser.getBeats());
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
        List<User> userList = users.findAll().stream().filter(user-> user.getId() != id).collect(Collectors.toList());
        if(currentUser != null && currentUser.getId().equals(id)){
            model.addAttribute("user", currentUser);
            model.addAttribute("isCurrentUser", (currentUser!= null && currentUser.getId()==beats.findById(id).getProducer().getId()));
            model.addAttribute("usersList", userList); 
            return "transfer";
        } else {
            return "redirect:/";
        }
    }

    // Method to transfer user property to another user

    @PostMapping("/usuarios/{id}/transfer")
    public String transferProperty(@PathVariable Long id, @RequestParam Long newOwnerId, HttpSession session){
        Logger logger = Logger.getLogger(getClass().getName());
    
        User currentUser = (User) session.getAttribute("user");
        if(currentUser != null && currentUser.getId().equals(id)){
    
            // Delete all licenses that the selected user has bought from the deleting user
            List<License> licensesToDelete = licenses.findAll().stream()
                .filter(license -> license.getUser().getId()==newOwnerId && beats.findById(license.getBeat().getId()).getProducer().getId()==id)
                .collect(Collectors.toList());
            logger.info("Licencias a eliminar: "+ licensesToDelete.size());
            for (License license : licensesToDelete) {
                licenses.deleteById(license.getId());
            }
    
            beats.transferBeats(id, newOwnerId);

            // Transfer all beats from the deleting user to the selected user
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
            // Invalidate session and delete user
            logout(session);
            users.deleteById(id);
        }
        return "redirect:/";  // Redirect to home page
    }

    @GetMapping("/usuarios/{id}/editprofile")
    public String editProfile(@PathVariable Long id, HttpSession session, Model model){
        User user = users.findById(id);
        User currentUser = (User) session.getAttribute("user");
        if (currentUser != null && currentUser.getId().equals(user.getId())) {
            model.addAttribute("user", user);
            return "editprofile";
        } else {
            return "redirect:/";
        }
    }

    // Method to update user's name
    @PostMapping("/usuarios/{id}/editprofile")
    public String updateProfile(@PathVariable Long id, @RequestParam String newname, @RequestParam String newmail,  @RequestParam String newpass, @RequestParam String confirmpass, HttpSession session, RedirectAttributes redirectAttributes){
        User currentUser = (User) session.getAttribute("user");
        if (!newpass.isEmpty() && !newpass.equals(confirmpass)) {
            redirectAttributes.addFlashAttribute("error", "Las contraseñas no coinciden");
            return "redirect:/usuarios/{id}/editname";
        }
        if (!newname.isEmpty() && users.findByName(newname)==null){
            currentUser.setName(newname);
        }
        if (!newmail.isEmpty() && users.findByEmail(newmail)==null){
            currentUser.setEmail(newmail);
        }
        if (!newpass.isEmpty()){
            currentUser.setPassword(newpass);
        }
        users.save(currentUser);
        session.setAttribute("user", currentUser);
        return "redirect:/usuarios/{id}";
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
        User newUser = new User(username, password, email, new HashSet<>(), new HashSet<>());
        if ((users.findByName(username))!=null || (users.findByEmail(email))!=null) {
            if(users.correctEmailFormat(email)){
                redirectAttributes.addFlashAttribute("error", "Usuario o e mail existentes");
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