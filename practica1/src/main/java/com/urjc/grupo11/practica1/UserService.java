package com.urjc.grupo11.practica1;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    private ConcurrentMap<Long, User> users = new ConcurrentHashMap<>();
    private AtomicLong nextId = new AtomicLong(1);

    public UserService(){
        save(new User("Pepito", "PepitoPerez512", "pepitoperez@gmail.com"));
        save(new User("Juanito", "Juanito8080", "juanito.8080@gmail.com"));
    }
    public void save(User user){
        if (user.getId() == null || user.getId()==0){
            long id = nextId.getAndIncrement();
            user.setId(id);
        }
        users.put(user.getId(), user);
    }
    public Collection<User> findAll(){
        return users.values();
    }
    public User findById(Long id){
        return users.get(id);
    }
    public void deleteById(Long id){
        users.remove(id);
    }
    public User findByName(String username){
        for (User user : users.values()) {
            if (user.getName().equals(username)) {
                return user;
            }
        }
        return null;
    }
    public User findByEmail(String email){
        for (User user : users.values()) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }
    
}
