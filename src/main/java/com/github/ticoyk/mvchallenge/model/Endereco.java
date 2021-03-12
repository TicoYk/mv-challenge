package com.github.ticoyk.mvchallenge.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Endereco {

    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private Long id;
    
    @Column(nullable = false)
    private String enderecoCompleto;

    @Column(nullable = false)
    private String cep;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="cliente_id", referencedColumnName="id", nullable = false)
    private Cliente cliente;
    
    public Endereco(){}

    public Endereco(String enderecoCompleto, String cep, Cliente cliente) {
        this.enderecoCompleto = enderecoCompleto;
        this.cep = cep;
        this.cliente = cliente;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}