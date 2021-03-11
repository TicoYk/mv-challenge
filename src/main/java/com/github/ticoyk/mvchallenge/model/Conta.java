package com.github.ticoyk.mvchallenge.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Conta {
    
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable=false)
    private String nomeBanco;
    
    @OneToMany(cascade = CascadeType.ALL)
    private List<Transacao> transacao;

    public Conta(){}
    
}