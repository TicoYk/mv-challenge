package com.github.ticoyk.mvchallenge.service;

import com.github.ticoyk.mvchallenge.model.Cliente;
import com.github.ticoyk.mvchallenge.model.Endereco;
import com.github.ticoyk.mvchallenge.repository.EnderecoRepository;

import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

    private EnderecoRepository enderecoRepository;
    public EnderecoService(EnderecoRepository enderecoRepository){
        this.enderecoRepository = enderecoRepository;
    }

    public Endereco cadastrarNovoEndereco(Cliente cliente, Endereco endereco){
        endereco.setCliente(cliente);
        return this.enderecoRepository.save(endereco);
    }
}