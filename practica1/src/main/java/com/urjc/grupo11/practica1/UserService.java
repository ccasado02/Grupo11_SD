package com.urjc.grupo11.practica1;

import java.util.Collection;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository users;

    public UserService(){}

    public void save(User user){
        users.save(user);
    }

    public Collection<User> findAll(){
        return users.findAll();
    }

    public User findById(Long id){
        return users.findById(id).orElse(null);
    }

    public void deleteById(Long id){
        users.deleteById(id);
    }

    public User findByName(String username){
        for (User user : users.findAll()) {
            if (user.getName().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public User findByEmail(String email){
        for (User user : users.findAll()) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }

    public Boolean correctEmailFormat(String email){
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

}
