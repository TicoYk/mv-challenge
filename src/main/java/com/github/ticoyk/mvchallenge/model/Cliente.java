package com.github.ticoyk.mvchallenge.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Cliente {
    
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private Long id;

    private String nome;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Conta> contas;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Endereco> enderecos;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Telefone> telefones;

    Cliente(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}