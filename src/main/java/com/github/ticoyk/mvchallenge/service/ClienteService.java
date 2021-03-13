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
        ClienteRepository clienteRepository,
        TelefoneRepository telefoneRepository
        ){
        this.clienteRepository = clienteRepository;
    }

    public Cliente encontrarClientePorId(Long id){
        return this.clienteRepository.findById(id).get();
    }

    public Cliente adicionarTelefone(Long clienteId, Telefone telefone){
        Cliente cliente = this.clienteRepository.findById(clienteId).get();
        List<Telefone> telefones = cliente.getTelefones();
        telefone.setCliente(cliente);
        telefones.add(telefone);
        cliente.setTelefones(telefones);
        return this.clienteRepository.save(cliente);
    }

    public Cliente adicionarEndereco(Long clienteId, Endereco endereco){
        Cliente cliente = this.clienteRepository.findById(clienteId).get();
        List<Endereco> enderecos = cliente.getEnderecos();
        endereco.setCliente(cliente);
        enderecos.add(endereco);
        cliente.setEnderecos(enderecos);
        return this.clienteRepository.save(cliente);
    }

    public Cliente adicionarConta(Long clienteId, Conta conta){
        Cliente cliente = this.clienteRepository.findById(clienteId).get();
        List<Conta> contas = cliente.getContas();
        conta.setCliente(cliente);
        contas.add(conta);
        cliente.setContas(contas);
        return this.clienteRepository.save(cliente);
    }
}
