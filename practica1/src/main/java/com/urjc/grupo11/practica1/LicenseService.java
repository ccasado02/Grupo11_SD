package com.urjc.grupo11.practica1;

import java.time.LocalDate;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

// Service class for managing licenses
@Service
public class LicenseService {
    private ConcurrentMap<Long, License> licenses = new ConcurrentHashMap<>();
    private AtomicLong nextId = new AtomicLong(1);

    @Autowired
    private UserService userService;

    @Autowired
    private BeatService beatService;

    // Method to initialize licenses
    @PostConstruct
    public void init(){
        User user1 = userService.findById(1L);
        User user2 = userService.findById(2L);
        Beat beat1 = beatService.findById(1L);
        Beat beat2 = beatService.findById(2L);

        if(user1 != null && beat1 != null) {
            save(new License(user1.getId(), beat1.getId(), LocalDate.of(2023, 12, 12), LICENSETYPE.COMMERCIAL));
        }
        if(user2 != null && beat1 != null) {
            save(new License(user2.getId(), beat1.getId(), LocalDate.of(2024, 2, 20), LICENSETYPE.PERSONAL));
        }
        if(user1 != null && beat2 != null) {
            save(new License(user1.getId(), beat2.getId(), LocalDate.of(2024, 1, 8), LICENSETYPE.COMMERCIAL));
        }
    }

    // Method to save a license
    public void save(License license){
        if (license.getId() == null || license.getId()==0){
            long id = nextId.getAndIncrement();
            license.setId(id);
        }
        licenses.put(license.getId(), license);
    }

    // Method to retrieve all licenses
    public Collection<License> findAll(){
        return licenses.values();
    }

    // Method to find a license by ID
    public License findById(Long id){
        return licenses.get(id);
    }

    // Method to delete a license by ID
    public void deleteById(Long id){
        licenses.remove(id);
    }
}