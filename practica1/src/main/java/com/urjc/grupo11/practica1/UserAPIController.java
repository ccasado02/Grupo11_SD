package com.urjc.grupo11.practica1;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

// REST controller for managing users
@RestController
@RequestMapping("/api/users")
public class UserAPIController {
    @Autowired
    private UserService users;

    // Method to get all users
    @GetMapping("/")
    public Collection<User> getUsers(){
        return users.findAll();
    }

    // Method to get a user by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id){
        User user = users.findById(id);
        if(user != null){
            return ResponseEntity.ok(user);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    // Method to create a user
    @PostMapping("/") 
    public ResponseEntity<User> createUser(@RequestBody User user){
        users.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(location).body(user);
    }

    // Method to delete a user by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id){
        User user = users.findById(id);
        if(user!=null){
            users.deleteById(id);
            return ResponseEntity.ok(user);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    // Method to replace a user by ID
    @PutMapping("/{id}")
    public ResponseEntity<User> replaceUser(@PathVariable Long id, @RequestBody User newuser){
        User user = users.findById(id);
        if(user!=null){
            newuser.setId(id);
            users.save(newuser);
            return ResponseEntity.ok(user);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    // Method to update a user by ID
    @PatchMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updateUser){
        User user = users.findById(id);
        if(user!=null){
            if (updateUser.getEmail() != null){
                user.setEmail(updateUser.getEmail());
            }
            if(updateUser.getName() != null){
                user.setName(updateUser.getName());
            }
            if(updateUser.getPassword() != null){
                user.setPassword(updateUser.getPassword());
            }
            return ResponseEntity.ok(user);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}