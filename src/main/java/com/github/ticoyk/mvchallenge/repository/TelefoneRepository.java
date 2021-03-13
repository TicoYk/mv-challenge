package com.github.ticoyk.mvchallenge.repository;

import com.github.ticoyk.mvchallenge.model.Telefone;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelefoneRepository extends CrudRepository<Telefone, Long>{
    
}
