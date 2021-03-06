package com.github.ticoyk.mvchallenge.repository;

import com.github.ticoyk.mvchallenge.model.PessoaJuridica;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaJuridicaRepository extends CrudRepository<PessoaJuridica, Long> {

    
}