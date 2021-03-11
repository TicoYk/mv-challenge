package com.github.ticoyk.mvchallenge.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class PessoaFisica {
    
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private Long id;

    private String cpf;

    @OneToOne(cascade = CascadeType.ALL)
    private Cliente cliente;

    PessoaFisica(){}
}