package com.github.ticoyk.mvchallenge.service;

import java.util.ArrayList;
import java.util.List;

import com.github.ticoyk.mvchallenge.model.Cliente;
import com.github.ticoyk.mvchallenge.model.PessoaFisica;
import com.github.ticoyk.mvchallenge.repository.PessoaFisicaRepository;

import org.springframework.stereotype.Service;

@Service
public class PessoaFisicaService implements ClienteCrudService<PessoaFisica> {

    PessoaFisicaRepository pessoaFisicaRepository;

    public PessoaFisicaService( PessoaFisicaRepository pessoaFisicaRepository ){
        this.pessoaFisicaRepository = pessoaFisicaRepository;
    }

    @Override
    public PessoaFisica registrarOuAtualizarCliente(PessoaFisica pessoaFisica){
        return this.pessoaFisicaRepository.save(pessoaFisica);
    }

    // Irá Atualizar apenas o Nome e CPF
    @Override
    public PessoaFisica atualizarCliente(Long id, PessoaFisica novaPF){
        PessoaFisica pf = this.encontrarClientePorId(id); 
        Cliente cliente = pf.getCliente();
        cliente.setNome(novaPF.getCliente().getNome());
        pf.setCpf(novaPF.getCpf());
        return this.pessoaFisicaRepository.save(pf);
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

    @Override
    public void deletarClientePorId(Long id) {
        this.pessoaFisicaRepository.delete(this.encontrarClientePorId(id));
    }
}