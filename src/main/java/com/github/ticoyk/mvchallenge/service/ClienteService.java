package com.github.ticoyk.mvchallenge.service;

import java.util.List;

import com.github.ticoyk.mvchallenge.model.Cliente;
import com.github.ticoyk.mvchallenge.model.Conta;
import com.github.ticoyk.mvchallenge.model.Endereco;
import com.github.ticoyk.mvchallenge.model.Telefone;
import com.github.ticoyk.mvchallenge.repository.ClienteRepository;
import com.github.ticoyk.mvchallenge.repository.TelefoneRepository;

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
    
}
