package com.github.ticoyk.mvchallenge.service;

import com.github.ticoyk.mvchallenge.model.Cliente;
import com.github.ticoyk.mvchallenge.repository.ClienteRepository;

import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    
    private ClienteRepository clienteRepository;

    public ClienteService(
        ClienteRepository clienteRepository
        ){
        this.clienteRepository = clienteRepository;
    }

    public Cliente encontrarClientePorId(Long id){
        return this.clienteRepository.findById(id).get();
    }

    public Cliente encontrarClientePorNome(String nome) {
        return this.clienteRepository.findByNome(nome);
    }
    
}
