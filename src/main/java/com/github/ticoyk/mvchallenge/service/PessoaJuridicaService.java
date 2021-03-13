package com.github.ticoyk.mvchallenge.service;

import java.util.ArrayList;
import java.util.List;

import com.github.ticoyk.mvchallenge.model.Cliente;
import com.github.ticoyk.mvchallenge.model.PessoaJuridica;
import com.github.ticoyk.mvchallenge.repository.PessoaJuridicaRepository;

import org.springframework.stereotype.Service;

@Service
public class PessoaJuridicaService implements ClienteCrudService<PessoaJuridica>{

    private PessoaJuridicaRepository pessoaJuridicaRepository;

    PessoaJuridicaService( PessoaJuridicaRepository pessoaJuridicaRepository ){
        this.pessoaJuridicaRepository = pessoaJuridicaRepository;
    }

    @Override
    public PessoaJuridica registrarOuAtualizarCliente(PessoaJuridica pessoaJuridica){
        return this.pessoaJuridicaRepository.save(pessoaJuridica);
    }

    // Irá Atualizar apenas o Nome e CNPJ
    @Override
    public PessoaJuridica atualizarCliente(Long id, PessoaJuridica novoPJ){
        PessoaJuridica pj = this.encontrarClientePorId(id); 
        Cliente cliente = pj.getCliente();
        cliente.setNome(novoPJ.getCliente().getNome());
        pj.setCnpj(novoPJ.getCnpj());
        return this.pessoaJuridicaRepository.save(pj);
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

    @Override
    public void deletarClientePorId(Long id) {
        this.pessoaJuridicaRepository.delete(this.encontrarClientePorId(id));
    }
}