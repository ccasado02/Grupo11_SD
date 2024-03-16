package com.urjc.grupo11.practica1;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class License {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long userId;
    private Long beatId;
    private LocalDate buyDate;

    public License(){}

    public License(Long userId, Long beatId, LocalDate buyDate){
        super();
        this.userId=userId;
        this.beatId=beatId;
        this.buyDate=buyDate;
    }
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBeatId() {
        return this.beatId;
    }

    public void setBeatId(Long beatId) {
        this.beatId = beatId;
    }

    public LocalDate getBuyDate() {
        return this.buyDate;
    }

    public void setBuyDate(LocalDate buyDate) {
        this.buyDate = buyDate;
    }
}
