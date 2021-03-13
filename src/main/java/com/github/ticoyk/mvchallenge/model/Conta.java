package com.github.ticoyk.mvchallenge.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Conta {
    
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable=false)
    private String nomeBanco;
    
    @OneToMany(mappedBy="conta", cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties({"conta"})
    private List<Transacao> transacoes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cliente_id", referencedColumnName="id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "contas", "enderecos", "telefones"})
    private Cliente cliente;
    
    public Conta(){}

    public Conta(String nomeBanco) {
        this.nomeBanco = nomeBanco;
    }
    
    public Conta(String nomeBanco, Cliente cliente) {
        this.nomeBanco = nomeBanco;
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeBanco() {
        return nomeBanco;
    }

    public void setNomeBanco(String nomeBanco) {
        this.nomeBanco = nomeBanco;
    }

    public List<Transacao> getTransacoes() {
        return transacoes;
    }

    public void setTransacoes(List<Transacao> transacoes) {
        this.transacoes = transacoes;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
}