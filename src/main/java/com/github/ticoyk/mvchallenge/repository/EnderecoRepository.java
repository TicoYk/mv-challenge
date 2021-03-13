package com.github.ticoyk.mvchallenge.repository;

import com.github.ticoyk.mvchallenge.model.Endereco;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends CrudRepository<Endereco, Long>{

    
}