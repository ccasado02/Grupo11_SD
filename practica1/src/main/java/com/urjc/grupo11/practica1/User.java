package com.urjc.grupo11.practica1;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="Usuario")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String password;
    private String email;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade=CascadeType.ALL, orphanRemoval = true)
    private Set<License> licenses;

    @JsonIgnore
    @OneToMany(mappedBy = "producer", cascade=CascadeType.ALL, orphanRemoval = true)
    private Set<Beat> beats;

    public User(){}

    public User(String name, String password, String email, Set<License> licenses, Set<Beat> beats){
        super();
        this.name=name;
        this.password=password;
        this.email=email;
        this.licenses=licenses;
        this.beats = beats;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<License> getLicenses() {
        return licenses;
    }

    public void setLicenses(Set<License> licenses) {
        this.licenses = licenses;
    }
    
    public Set<Beat> getBeats() {
        return beats;
    }

    public void setBeats(Set<Beat> beats) {
        this.beats = beats;
    }
}

