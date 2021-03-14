package com.github.ticoyk.mvchallenge.repository;

import com.github.ticoyk.mvchallenge.model.Cliente;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long>{

    Cliente findByNome(String nome);
}