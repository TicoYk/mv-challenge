package com.github.ticoyk.mvchallenge.service;

import java.util.List;

import com.github.ticoyk.mvchallenge.model.Cliente;
import com.github.ticoyk.mvchallenge.model.Telefone;
import com.github.ticoyk.mvchallenge.repository.TelefoneRepository;

import org.springframework.stereotype.Service;

@Service
public class TelefoneService {
    
    private TelefoneRepository telefoneRepository;
    public TelefoneService(TelefoneRepository telefoneRepository){
        this.telefoneRepository = telefoneRepository;
    }

    public Telefone cadastrarNovoTelefone(Cliente cliente, Telefone telefone){
        List<Telefone> telefones = cliente.getTelefones();
        cliente.setTelefones(telefones);
        return this.telefoneRepository.save(telefone);
    }
}
