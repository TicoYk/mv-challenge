package com.github.ticoyk.mvchallenge.service;

import com.github.ticoyk.mvchallenge.model.Cliente;
import com.github.ticoyk.mvchallenge.model.Endereco;
import com.github.ticoyk.mvchallenge.repository.ClienteRepository;
import com.github.ticoyk.mvchallenge.repository.EnderecoRepository;

import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

    private EnderecoRepository enderecoRepository;
    private ClienteRepository clienteRepository;

    public EnderecoService(
        EnderecoRepository enderecoRepository,
        ClienteRepository clienteRepository
        ){
        this.enderecoRepository = enderecoRepository;
        this.clienteRepository = clienteRepository;
    } 
    
    public Endereco encontrarEnderecoPorId(Long id){
        return this.enderecoRepository.findById(id).get();
    }

    public Cliente cadastrarEndereco(Long clienteId, Endereco endereco){
        Cliente cliente = this.clienteRepository.findById(clienteId).get();
        endereco.setCliente(cliente);
        this.enderecoRepository.save(endereco);
        return cliente;
    }

    public Endereco atualizarEndereco(Long enderecoId, Endereco novoEndereco){
        Endereco endereco = this.encontrarEnderecoPorId(enderecoId);
        endereco.setEnderecoCompleto(novoEndereco.getEnderecoCompleto());
        endereco.setCep(novoEndereco.getCep());
        return this.enderecoRepository.save(endereco);
    }

    public void deletarEnderecoPorId(Long enderecoId){
        this.enderecoRepository.deleteById(enderecoId);
    }

    public Cliente deletarEnderecoPorIdTrazerCliente(Long enderecoId){
        Cliente cliente = encontrarEnderecoPorId(enderecoId).getCliente();
        this.enderecoRepository.deleteById(enderecoId);
        return cliente;
    }
}