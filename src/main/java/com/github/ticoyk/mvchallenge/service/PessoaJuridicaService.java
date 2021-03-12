package com.github.ticoyk.mvchallenge.service;

import com.github.ticoyk.mvchallenge.model.PessoaJuridica;
import com.github.ticoyk.mvchallenge.repository.PessoaJuridicaRepository;

import org.springframework.stereotype.Service;

@Service
public class PessoaJuridicaService implements ClienteService<PessoaJuridica>{

    private PessoaJuridicaRepository pessoaJuridicaRepository;

    PessoaJuridicaService( PessoaJuridicaRepository pessoaJuridicaRepository ){
        this.pessoaJuridicaRepository = pessoaJuridicaRepository;
    }

    public PessoaJuridica registrarNovoCliente(PessoaJuridica pessoaJuridica){
        return this.pessoaJuridicaRepository.save(pessoaJuridica);
    }

    public PessoaJuridica atualizarCliente(PessoaJuridica pessoaJuridica){
        return this.pessoaJuridicaRepository.save(pessoaJuridica);
    }
}