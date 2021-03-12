package com.github.ticoyk.mvchallenge.service;

import java.util.ArrayList;
import java.util.List;

import com.github.ticoyk.mvchallenge.model.PessoaJuridica;
import com.github.ticoyk.mvchallenge.repository.PessoaJuridicaRepository;

import org.springframework.stereotype.Service;

@Service
public class PessoaJuridicaService implements ClienteService<PessoaJuridica>{

    private PessoaJuridicaRepository pessoaJuridicaRepository;

    PessoaJuridicaService( PessoaJuridicaRepository pessoaJuridicaRepository ){
        this.pessoaJuridicaRepository = pessoaJuridicaRepository;
    }

    @Override
    public PessoaJuridica registrarNovoCliente(PessoaJuridica pessoaJuridica){
        return this.pessoaJuridicaRepository.save(pessoaJuridica);
    }

    @Override
    public PessoaJuridica atualizarCliente(PessoaJuridica pessoaJuridica){
        return this.pessoaJuridicaRepository.save(pessoaJuridica);
    }

    @Override
    public List<PessoaJuridica> trazerTodosClientes() {
        List<PessoaJuridica> listaClientes = new ArrayList<PessoaJuridica>();
        this.pessoaJuridicaRepository.findAll().iterator().forEachRemaining( cliente -> {
            listaClientes.add(cliente);
        });
        return listaClientes;
    }

    @Override
    public PessoaJuridica encontrarClientePorId(Long id) {
        return this.pessoaJuridicaRepository.findById(id).get();
    }

}