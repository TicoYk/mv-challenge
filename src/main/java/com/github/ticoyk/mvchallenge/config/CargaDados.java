package com.github.ticoyk.mvchallenge.config;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import com.github.ticoyk.mvchallenge.constants.TipoCliente;
import com.github.ticoyk.mvchallenge.model.Cliente;
import com.github.ticoyk.mvchallenge.model.Conta;
import com.github.ticoyk.mvchallenge.model.Endereco;
import com.github.ticoyk.mvchallenge.model.PessoaFisica;
import com.github.ticoyk.mvchallenge.model.PessoaJuridica;
import com.github.ticoyk.mvchallenge.model.Telefone;
import com.github.ticoyk.mvchallenge.service.PessoaFisicaService;
import com.github.ticoyk.mvchallenge.service.PessoaJuridicaService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CargaDados {
    
    private PessoaFisicaService pessoaFisicaService;
    private PessoaJuridicaService pessoaJuridicaService;

    public CargaDados(
        PessoaFisicaService pessoaFisicaService,
        PessoaJuridicaService pessoaJuridicaService
        ){
        this.pessoaFisicaService = pessoaFisicaService;
        this.pessoaJuridicaService = pessoaJuridicaService;
    }

    @Value("${spring.profiles.active}")
    private String activeProfile;

    @PostConstruct
    public void init() {
        if(activeProfile.equals("dev")){
            criarClientes();
        }
    }

    private void criarClientes() {
        // PF
        PessoaFisica pf = this.pessoaFisicaService.registrarNovoCliente(
            new PessoaFisica("12345678901", new Cliente("Novo Cliente PF",TipoCliente.PF))
            );
        Cliente cliente = pf.getCliente();

        Telefone telefone = new Telefone("444444444", cliente);
        Endereco endereco = new Endereco("Um endereço completo! PF", "66666666", cliente);
        Conta conta = new Conta("Nome do Banco PF", cliente);
        
        cliente.setContas(Arrays.asList(conta));
        cliente.setEnderecos(Arrays.asList(endereco));
        cliente.setTelefones(Arrays.asList(telefone));

        pf.setCliente(cliente);
        this.pessoaFisicaService.atualizarCliente(pf);
        
        // PJ 

        PessoaJuridica pj = this.pessoaJuridicaService.registrarNovoCliente(
            new PessoaJuridica("12345678901234", new Cliente("Novo Cliente PJ", TipoCliente.PJ))
            );
        cliente = pj.getCliente();

        endereco = new Endereco("Um endereço incompleto! PJ", "77777777", cliente);
        telefone = new Telefone("333333333", cliente);
        conta = new Conta("Nome do Banco PJ", cliente);

        cliente.setContas(Arrays.asList(conta));
        cliente.setEnderecos(Arrays.asList(endereco));
        cliente.setTelefones(Arrays.asList(telefone));

        pj.setCliente(cliente);
        this.pessoaJuridicaService.registrarNovoCliente(pj);

    }

}