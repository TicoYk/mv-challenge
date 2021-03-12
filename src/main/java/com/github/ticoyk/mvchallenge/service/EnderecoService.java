package com.github.ticoyk.mvchallenge.service;

import com.github.ticoyk.mvchallenge.repository.EnderecoRepository;

import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

    EnderecoRepository enderecoRepository;

    public EnderecoService( EnderecoRepository enderecoRepository ){
        this.enderecoRepository = enderecoRepository;
    }
}