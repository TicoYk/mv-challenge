package com.github.ticoyk.mvchallenge.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.github.ticoyk.mvchallenge.constants.TipoTransacao;

@Entity
public class Transacao {
    
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private Long id;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoTransacao tipo;

    @Column(nullable = false)
    private Double valor;
    
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="conta_id", referencedColumnName="id", nullable = false)
    private Conta conta;
    
    public Transacao(){}

    public Transacao(TipoTransacao tipo, Double valor, Date createdDate, Conta conta) {
        this.tipo = tipo;
        this.valor = valor;
        this.createdDate = createdDate;
        this.conta = conta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoTransacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoTransacao tipo) {
        this.tipo = tipo;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public java.util.Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(java.util.Date createdDate) {
        this.createdDate = createdDate;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

}