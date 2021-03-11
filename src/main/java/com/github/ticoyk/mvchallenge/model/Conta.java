package com.github.ticoyk.mvchallenge.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Conta {
    
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private Long id;

    Conta(){}

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
}