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

@RestController
@RequestMapping("/api/licenses")
public class LicenseAPIController {
    @Autowired
    private LicenseService licenses;
    @GetMapping("/")
    public Collection<License> getLicenses(){
        return licenses.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<License> getLicense(@PathVariable Long id){
        License license = licenses.findById(id);
        if(license != null){
            return ResponseEntity.ok(license);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/") 
    public ResponseEntity<License> createLicense(@RequestBody License license){
        licenses.save(license);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(license.getId()).toUri();
        return ResponseEntity.created(location).body(license);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<License> deleteLicense(@PathVariable Long id){
        License license = licenses.findById(id);
        if(license!=null){
            licenses.deleteById(id);
            return ResponseEntity.ok(license);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<License> replaceLicense(@PathVariable Long id, @RequestBody License newLicense){
        License license = licenses.findById(id);
        if(license!=null){
            newLicense.setId(id);
            licenses.save(newLicense);
            return ResponseEntity.ok(license);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    @PatchMapping("/{id}")
    public ResponseEntity<License> updateLicense(@PathVariable Long id, @RequestBody License updateLicense){
        License license = licenses.findById(id);
        if(license!=null){
            if (!(updateLicense.getUserId()==null || updateLicense.getUserId()==0)){
                license.setUserId(updateLicense.getUserId());
            }
            if (!(updateLicense.getBeatId()==null || updateLicense.getBeatId()==0)){
                license.setBeatId(updateLicense.getBeatId());
            }
            if(updateLicense.getBuyDate() != null){
                license.setBuyDate(updateLicense.getBuyDate());
            }
            return ResponseEntity.ok(license);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
