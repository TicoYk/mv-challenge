package com.github.ticoyk.mvchallenge.model;

import java.time.LocalDate;

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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.ticoyk.mvchallenge.constants.TipoTransacao;

import org.hibernate.annotations.CreationTimestamp;

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
    
    @CreationTimestamp
    private LocalDate dataCriacao;

    // Atributo para Armazenar Conta que beneficiou XPTO
    @Column(nullable=true)
    private String identificador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="conta_id", referencedColumnName="id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "cliente", "transacoes"})
    private Conta conta;
    
    public Transacao(){}

    public Transacao(TipoTransacao tipo, Double valor, Conta conta) {
        this.tipo = tipo;
        this.valor = valor;
        this.conta = conta;
    }
    
    public Transacao(TipoTransacao tipo, Double valor, LocalDate dataCriacao, Conta conta) {
        this.tipo = tipo;
        this.valor = valor;
        this.dataCriacao = dataCriacao;
        this.conta = conta;
    }

    public Transacao(TipoTransacao tipo, Double valor, Conta conta, String identificador) {
        this.tipo = tipo;
        this.valor = valor;
        this.identificador = identificador;
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

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setCreatedDate(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

}