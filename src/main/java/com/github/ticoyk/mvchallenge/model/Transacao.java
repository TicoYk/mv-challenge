package com.github.ticoyk.mvchallenge.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    
    Transacao(){}
}