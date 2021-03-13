package com.github.ticoyk.mvchallenge.service;

import java.util.List;

import com.github.ticoyk.mvchallenge.model.Cliente;
import com.github.ticoyk.mvchallenge.model.Conta;
import com.github.ticoyk.mvchallenge.repository.ClienteRepository;
import com.github.ticoyk.mvchallenge.repository.ContaRepository;

import org.springframework.stereotype.Service;

@Service
public class ContaService {

    private ContaRepository contaRepository;
    private ClienteRepository clienteRepository;

    public ContaService(
        ContaRepository contaRepository,
        ClienteRepository clienteRepository
        ){
        this.contaRepository = contaRepository;
        this.clienteRepository = clienteRepository;
    }

    public Conta encontrarContaPorId(Long id){
        return this.contaRepository.findById(id).get();
    }

    public Cliente cadastrarConta(Long clienteId, Conta conta){
        Cliente cliente = this.clienteRepository.findById(clienteId).get();
        conta.setCliente(cliente);
        this.contaRepository.save(conta);
        return cliente;
    }

    public Conta atualizarConta(Long contaId, Conta novaConta){
        Conta conta = this.encontrarContaPorId(contaId);
        conta.setNomeBanco(novaConta.getNomeBanco());
        return this.contaRepository.save(conta);
    }

    public void deletarContaPorId(Long contaId){
        this.contaRepository.deleteById(contaId);
    }

    public Cliente deletarContaPorIdTrazerCliente(Long contaId){
        Cliente cliente = encontrarContaPorId(contaId).getCliente();
        this.contaRepository.deleteById(contaId);
        return cliente;
    }
}