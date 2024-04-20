package com.urjc.grupo11.practica1;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Beat {
    // Unique identifier for the Beat
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private GENERO genre;
    private Double price;
    private String description;
    private String url;
    private Set<String> tags;

    @OneToMany(mappedBy = "beat", cascade=CascadeType.ALL, orphanRemoval = true)
    private Set<License> licenses;

    @ManyToOne
    @JoinColumn(name = "producer_id")
    private User producer;

    // Default constructor
    public Beat(){}

    // Constructor with parameters
    public Beat(String name, GENERO genre, String description, String url, Double price, Set<String> tags, User producer, Set<License> licenses){
        super();
        this.name=name;
        this.genre=genre;
        this.description=description;
        this.url=url;
        this.price=price;
        this.tags = tags;
        this.producer = producer;
        this.licenses=licenses;
        }
    
    // Getter and setter methods for producer
    public User getProducer() {
        return this.producer;
    }

    public void setProducer(User producer) {
        this.producer = producer;
    }

    // Getter and setter methods for id
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter and setter methods for name
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and setter methods for genre
    public GENERO getGenre() {
        return this.genre;
    }

    public void setGenre(GENERO genre) {
        this.genre = genre;
    }

    // Getter and setter methods for description
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Getter and setter methods for url
    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    // Getter and setter methods for price
    public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    // Getter and setter methods for tags
    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }
}
