package com.urjc.grupo11.practica1;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class License {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate buyDate;
    private LICENSETYPE licenseType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "beat_id")
    private Beat beat;

    public License(){}

    public License(User userId, Beat beatId, LocalDate buyDate, LICENSETYPE licenseType){
        super();
        this.user=userId;
        this.beat=beatId;
        this.buyDate=buyDate;
        this.licenseType=licenseType;
    }
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Beat getBeat() {
        return this.beat;
    }

    public void setBeat(Beat beat) {
        this.beat = beat;
    }

    public LocalDate getBuyDate() {
        return this.buyDate;
    }

    public void setBuyDate(LocalDate buyDate) {
        this.buyDate = buyDate;
    }
    public LICENSETYPE getLicenseType(){
        return this.licenseType;
    }
    public void setLicenseType(LICENSETYPE licenseType){
        this.licenseType = licenseType;
    }
}
