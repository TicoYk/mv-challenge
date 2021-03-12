package com.github.ticoyk.mvchallenge.service;

import com.github.ticoyk.mvchallenge.repository.ContaRepository;

import org.springframework.stereotype.Service;

@Service
public class ContaService {

    private ContaRepository contaRepository;

    public ContaService(ContaRepository contaRepository){
        this.contaRepository = contaRepository;
    }

}