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



@Controller
public class UserController{

    @Autowired
    UserService users;
    @Autowired
    BeatService beats;
    @Autowired
    LicenseService licenses;

    @GetMapping("/")
    public String home(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        return "index";
    }



    @GetMapping("/usuarios")
    public String getAllUsers(Model model){
        model.addAttribute("users", users.findAll().stream().collect(Collectors.toList()));
        return "users";
    }

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
    @PostMapping("/usuarios/{id}/editname")
    public String updateUserName(@PathVariable Long id, @RequestParam String newname, HttpSession session, RedirectAttributes redirectAttributes){
        if (users.findByName(newname)==null){
        User currentUser = (User) session.getAttribute("user");
        User newUser = new User(newname, currentUser.getPassword(), currentUser.getEmail());
        newUser.setId(currentUser.getId());
        users.save(newUser);
        session.setAttribute("user", newUser);
        return "redirect:/usuarios/{id}";
        }else{
            redirectAttributes.addFlashAttribute("error", "Usuario o contraseña incorrectos");
            return "redirect:/usuarios/{id}/editname";
        }
    }
        
    @GetMapping("/usuarios/{id}")
    public String getUser(@PathVariable Long id, HttpSession session, Model model){
        User user = users.findById(id);
        User currentUser = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        model.addAttribute("isCurrentUser", currentUser != null && currentUser.getId().equals(user.getId()));
        return "user";
    }
    
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }


    
    @GetMapping("/usuarios/{id}/del")
    public String deleteUser(@PathVariable Long id, HttpSession session, Model model){
        User currentUser = (User) session.getAttribute("user");
        if(currentUser != null && currentUser.getId().equals(id)){
            users.deleteById(id); 
            model.addAttribute("usersList", users.getUsers()); 
            return "transfer";
        }else{
            return "redirect:/";
        }
    }

    @PostMapping("/usuarios/{id}/transfer")
    public String transferProperty(@PathVariable Long id, @RequestParam Long newOwnerId, HttpSession session){
        User currentUser = (User) session.getAttribute("user");
        if(currentUser != null && currentUser.getId().equals(id)){
            // Transfer all beats from the deleting user to the selected user
            beats.transferBeats(id, newOwnerId);

            // Delete all licenses that the selected user has bought from the deleting user
            List<License> licensesToDelete = licenses.findAll().stream()
                .filter(license -> license.getUserId().equals(newOwnerId) && beats.findById(license.getBeatId()).getProducerID().equals(id))
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

    @PostMapping("/usuarios/{id}/borrar")
    public String deleteProperty(@PathVariable Long id, HttpSession session){
        User currentUser = (User) session.getAttribute("user");
        if(currentUser != null && currentUser.getId().equals(id)){
            // Encuentra y elimina todas las licencias de los beats producidos por el usuario actual
            List<License> licensesToDelete = licenses.findAll().stream()
                .filter(license -> beats.findById(license.getBeatId()).getProducerID().equals(id))
                .collect(Collectors.toList());
            for (License license : licensesToDelete) {
                licenses.deleteById(license.getId());
            }

            // Encuentra y elimina todos los beats del usuario actual
            List<Beat> beatsToDelete = beats.findAll().stream()
                .filter(beat -> beat.getProducerID().equals(id))
                .collect(Collectors.toList());
            for (Beat beat : beatsToDelete) {
                beats.deleteById(beat.getId());
            }

            // Cierra la sesión y elimina al usuario
            logout(session);
            users.deleteById(id);
        }
        return "redirect:/";  // Redirige a la página de inicio
    }

    @GetMapping("/iniciasesion")
    public String signInForm(){
        return "SignIn";
    }

    @GetMapping("/signin")
    public String signIn(@RequestParam(required=false) String username, @RequestParam(required=false) String password, RedirectAttributes redirectAttributes, HttpSession session, Model model) {
        if (username==null || password == null){
            username = (String) session.getAttribute("nombre");
            password = (String) session.getAttribute("contrasena");
        }
        User user = users.findByName(username);
        if (user != null && user.getPassword().equals(password)) {
            session.setAttribute("user", user); // Establecer el usuario en la sesión
            return "redirect:/";
        } else {
            redirectAttributes.addFlashAttribute("error", "Usuario o contraseña incorrectos");
            return "redirect:/iniciasesion";
        }
    }
    @GetMapping("/registrate")
    public String signUpForm() {
        return "SignUp";
    }

    @PostMapping("/signup")
    public String signUp(@RequestParam String username, @RequestParam String password, @RequestParam String email, RedirectAttributes redirectAttributes, HttpSession session) {
        User newUser = new User(username, password, email);
        if ((users.findByName(username))!=null || (users.findByEmail(email))!=null) {
            if(users.correctEmailFormat(email)){
                redirectAttributes.addFlashAttribute("error", "Usuario o email existentes");
                return "redirect:/registrate";
            }
            else{
                redirectAttributes.addFlashAttribute("error", "Email incorrecto");
                return "redirect:/registrate";              
            }
        }
        else{
            if(users.correctEmailFormat(email)){
                users.save(newUser);
                session.setAttribute("user", newUser); // Establecer el usuario en la sesión
                return "redirect:/";
            } else{
                redirectAttributes.addFlashAttribute("error", "Email incorrecto");
                return "redirect:/registrate";          
            }
        }
    }
    
}
