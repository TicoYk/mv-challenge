package com.github.ticoyk.mvchallenge.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Telefone {
    
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String numero;
    
    public Telefone(){}

    public Telefone(String numero) {
        this.numero = numero;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}