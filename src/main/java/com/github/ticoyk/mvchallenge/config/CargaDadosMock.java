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
import com.github.ticoyk.mvchallenge.service.ClienteService;
import com.github.ticoyk.mvchallenge.service.PessoaFisicaService;
import com.github.ticoyk.mvchallenge.service.PessoaJuridicaService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CargaDadosMock {
    
    private PessoaFisicaService pessoaFisicaService;
    private PessoaJuridicaService pessoaJuridicaService;
    private ClienteService clienteService;
    
    public CargaDadosMock(
        PessoaFisicaService pessoaFisicaService,
        PessoaJuridicaService pessoaJuridicaService,
        ClienteService clienteService
        ){
        this.pessoaFisicaService = pessoaFisicaService;
        this.pessoaJuridicaService = pessoaJuridicaService;
        this.clienteService = clienteService;
    }

    @Value("${spring.profiles.active}")
    private String activeProfile;

    @PostConstruct
    public void init() {
        Cliente xpto = this.clienteService.encontrarClientePorNome("XPTO");
        if(xpto == null){
            criarXPTO();
        } 
        if(activeProfile.equals("dev") || activeProfile.equals("test")){
            criarClientes();
        }
    }

    private void criarXPTO(){
        PessoaJuridica pj = this.pessoaJuridicaService.registrarOuAtualizarCliente(
            new PessoaJuridica("12345678901234", new Cliente("XPTO", TipoCliente.PJ))
            );
        Cliente cliente = pj.getCliente();
        Conta conta = new Conta("XPTO Bank", cliente);
        cliente.setContas(Arrays.asList(conta));
        pj.setCliente(cliente);
        this.pessoaJuridicaService.registrarOuAtualizarCliente(pj);
    }

    private void criarClientes() {
        // PF
        PessoaFisica pf = this.pessoaFisicaService.registrarOuAtualizarCliente(
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
        this.pessoaFisicaService.registrarOuAtualizarCliente(pf);
        
        // PJ 

        PessoaJuridica pj = this.pessoaJuridicaService.registrarOuAtualizarCliente(
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
        this.pessoaJuridicaService.registrarOuAtualizarCliente(pj);

    }

}