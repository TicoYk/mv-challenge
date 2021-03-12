package com.github.ticoyk.mvchallenge.service;

import java.util.ArrayList;
import java.util.List;

import com.github.ticoyk.mvchallenge.model.PessoaFisica;
import com.github.ticoyk.mvchallenge.repository.PessoaFisicaRepository;

import org.springframework.stereotype.Service;

@Service
public class PessoaFisicaService implements ClienteService<PessoaFisica> {

    PessoaFisicaRepository pessoaFisicaRepository;

    public PessoaFisicaService( PessoaFisicaRepository pessoaFisicaRepository ){
        this.pessoaFisicaRepository = pessoaFisicaRepository;
    }

    @Override
    public PessoaFisica registrarNovoCliente(PessoaFisica pessoaFisica){
        return this.pessoaFisicaRepository.save(pessoaFisica);
    }

    @Override
    public PessoaFisica atualizarCliente(PessoaFisica pessoaFisica){
        return this.pessoaFisicaRepository.save(pessoaFisica);
    }

    @Override
    public List<PessoaFisica> trazerTodosClientes() {
        List<PessoaFisica> listaClientes = new ArrayList<PessoaFisica>();
        this.pessoaFisicaRepository.findAll().iterator().forEachRemaining( cliente -> {
            listaClientes.add(cliente);
        });
        return listaClientes;
    }

    @Override
    public PessoaFisica encontrarClientePorId(Long id) {
        return this.pessoaFisicaRepository.findById(id).get();
    }
}