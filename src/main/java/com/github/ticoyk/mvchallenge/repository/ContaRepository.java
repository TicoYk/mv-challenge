package com.github.ticoyk.mvchallenge.repository;

import com.github.ticoyk.mvchallenge.model.Conta;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaRepository extends CrudRepository<Conta, Long>{

    
}