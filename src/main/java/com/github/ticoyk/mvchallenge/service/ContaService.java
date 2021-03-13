package com.github.ticoyk.mvchallenge.service;

import com.github.ticoyk.mvchallenge.model.Cliente;
import com.github.ticoyk.mvchallenge.model.Conta;
import com.github.ticoyk.mvchallenge.repository.ContaRepository;

import org.springframework.stereotype.Service;

@Service
public class ContaService {

    private ContaRepository contaRepository;
    public ContaService(ContaRepository contaRepository){
        this.contaRepository = contaRepository;
    }

    public Conta cadastrarNovaConta(Cliente cliente, Conta conta){
        conta.setCliente(cliente);
        return this.contaRepository.save(conta);
    }

}