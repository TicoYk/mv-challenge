package com.github.ticoyk.mvchallenge.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.github.ticoyk.mvchallenge.constants.TipoCliente;


@Entity
public class Cliente {
    
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private Long id;
    
    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoCliente tipoCliente;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Conta> contas;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Endereco> enderecos;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Telefone> telefones;

    public Cliente(){}

}