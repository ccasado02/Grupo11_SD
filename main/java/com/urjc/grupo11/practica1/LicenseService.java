package com.urjc.grupo11.practica1;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


// Service class for managing licenses
@Service
public class LicenseService {
    @Autowired
    private LicenseRepository licenses;

    // Method to save a license
    public void save(License license){
        licenses.save(license);
    }

    // Method to retrieve all licenses
    public Collection<License> findAll(){
        return licenses.findAll();
    }

    // Method to find a license by ID
    public License findById(Long id){
        return licenses.findById(id).orElse(null);
    }

    // Method to delete a license by ID
    public void deleteById(Long id){
        licenses.deleteById(id);
    }
}