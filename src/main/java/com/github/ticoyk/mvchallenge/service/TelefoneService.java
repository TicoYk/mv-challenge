package com.github.ticoyk.mvchallenge.service;

import com.github.ticoyk.mvchallenge.model.Cliente;
import com.github.ticoyk.mvchallenge.model.Telefone;
import com.github.ticoyk.mvchallenge.repository.ClienteRepository;
import com.github.ticoyk.mvchallenge.repository.TelefoneRepository;

import org.springframework.stereotype.Service;

@Service
public class TelefoneService {
    
    private TelefoneRepository telefoneRepository;
    private ClienteRepository clienteRepository;

    public TelefoneService(
        TelefoneRepository telefoneRepository,
        ClienteRepository clienteRepository
        ){
        this.telefoneRepository = telefoneRepository;
        this.clienteRepository = clienteRepository;
    } 
    
    public Telefone encontrarTelefonePorId(Long id){
        return this.telefoneRepository.findById(id).get();
    }

    public Cliente cadastrarTelefone(Long clienteId, Telefone telefone){
        Cliente cliente = this.clienteRepository.findById(clienteId).get();
        telefone.setCliente(cliente);
        this.telefoneRepository.save(telefone);
        return cliente;
    }

    public Telefone atualizarTelefone(Long telefoneId, Telefone novoTelefone){
        Telefone telefone = this.encontrarTelefonePorId(telefoneId);
        telefone.setNumero(novoTelefone.getNumero());
        return this.telefoneRepository.save(telefone);
    }

    public void deletarTelefonePorId(Long telefoneId){
        this.telefoneRepository.deleteById(telefoneId);
    }

    public Cliente deletarTelefonePorIdTrazerCliente(Long telefoneId){
        Cliente cliente = encontrarTelefonePorId(telefoneId).getCliente();
        this.telefoneRepository.deleteById(telefoneId);
        return cliente;
    }
}
