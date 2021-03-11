package com.github.ticoyk.mvchallenge.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class PessoaJuridica {
    
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private Long id;
    private String cnpj;

    @OneToOne(cascade = CascadeType.ALL)
    private Cliente cliente;
    
    PessoaJuridica(){}
}