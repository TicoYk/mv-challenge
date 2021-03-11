package com.github.ticoyk.mvchallenge.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Endereco {

    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private Long id;
    
    Endereco(){}
}