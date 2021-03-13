package com.github.ticoyk.mvchallenge.repository;

import com.github.ticoyk.mvchallenge.model.PessoaFisica;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaFisicaRepository extends CrudRepository<PessoaFisica, Long> {

    
}