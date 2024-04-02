package com.urjc.grupo11.practica1;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Beat {
    // Unique identifier for the Beat
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    // Identifier for the producer of the Beat
    private Long producerID;
    // Name of the Beat
    private String name;
    // Genre of the Beat
    private GENERO genre;
    // Price of the Beat
    private Double price;
    // Description of the Beat
    private String description;
    // URL to access the Beat
    private String url;
    // Set of tags associated with the Beat
    private Set<String> tags;

    // Default constructor
    public Beat(){}

    // Constructor with parameters
    public Beat(String name, GENERO genre, String description, String url, Double price, Set<String> tags, long producerID){
        super();
        this.name=name;
        this.genre=genre;
        this.description=description;
        this.url=url;
        this.price=price;
        this.tags = tags;
        this.producerID = producerID;
    }
    
    // Getter and setter methods for producerID
    public Long getProducerID() {
        return this.producerID;
    }

    public void setProducerID(Long producerID) {
        this.producerID = producerID;
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
