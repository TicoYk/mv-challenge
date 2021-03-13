package com.github.ticoyk.mvchallenge.repository;

import com.github.ticoyk.mvchallenge.model.Transacao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransacaoRepository extends CrudRepository<Transacao, Long>{
    
}
