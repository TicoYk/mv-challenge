package com.github.ticoyk.mvchallenge.service;

import com.github.ticoyk.mvchallenge.model.PessoaFisica;
import com.github.ticoyk.mvchallenge.repository.PessoaFisicaRepository;

import org.springframework.stereotype.Service;

@Service
public class PessoaFisicaService implements ClienteService<PessoaFisica> {

    PessoaFisicaRepository pessoaFisicaRepository;

    public PessoaFisicaService( PessoaFisicaRepository pessoaFisicaRepository ){
        this.pessoaFisicaRepository = pessoaFisicaRepository;
    }

    public PessoaFisica registrarNovoCliente(PessoaFisica pessoaFisica){
        return this.pessoaFisicaRepository.save(pessoaFisica);
    }

    public PessoaFisica atualizarCliente(PessoaFisica pessoaFisica){
        return this.pessoaFisicaRepository.save(pessoaFisica);
    }
}