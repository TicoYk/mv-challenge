package com.github.ticoyk.mvchallenge.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Endereco {

    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private Long id;
    
    @Column(nullable = false)
    private String enderecoCompleto;

    @Column(nullable = false)
    private String cep;
    
    public Endereco(){}

    public Endereco(String enderecoCompleto, String cep) {
        this.enderecoCompleto = enderecoCompleto;
        this.cep = cep;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEnderecoCompleto() {
        return enderecoCompleto;
    }

    public void setEnderecoCompleto(String enderecoCompleto) {
        this.enderecoCompleto = enderecoCompleto;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}