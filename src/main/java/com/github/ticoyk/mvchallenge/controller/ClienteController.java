package com.github.ticoyk.mvchallenge.controller;

import com.github.ticoyk.mvchallenge.constants.TipoCliente;
import com.github.ticoyk.mvchallenge.model.Cliente;
import com.github.ticoyk.mvchallenge.model.PessoaFisica;
import com.github.ticoyk.mvchallenge.model.PessoaJuridica;
import com.github.ticoyk.mvchallenge.service.PessoaFisicaService;
import com.github.ticoyk.mvchallenge.service.PessoaJuridicaService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ClienteController {

    private PessoaFisicaService pessoaFisicaService;
    private PessoaJuridicaService pessoaJuridicaService;

    public ClienteController(
        PessoaFisicaService pessoaFisicaService, 
        PessoaJuridicaService pessoaJuridicaService
        ){
        this.pessoaFisicaService = pessoaFisicaService;
        this.pessoaJuridicaService = pessoaJuridicaService;
    }

    @GetMapping(value = "/clientes")
    public String clientes(Model model){
        model.addAttribute("listaPf", this.pessoaFisicaService.trazerTodosClientes());
        model.addAttribute("listaPj", this.pessoaJuridicaService.trazerTodosClientes());
        return "clientes/index";
    }

    @GetMapping(value = "/clientes/pessoa-fisica/{pfId}")
    public String buscarPorClientePF(@PathVariable Long pfId, Model model){
        PessoaFisica pf = this.pessoaFisicaService.encontrarClientePorId(pfId);
        Cliente cliente = pf.getCliente();
        model.addAttribute("cliente", cliente);
        model.addAttribute("telefones", cliente.getTelefones());
        model.addAttribute("enderecos", cliente.getEnderecos());
        model.addAttribute("contas", cliente.getContas());

        model.addAttribute("especId", pfId);
        model.addAttribute("cpf", pf.getCpf());
        return "clientes/detalhes-pf";
    }

    @GetMapping(value = "/clientes/pessoa-juridica/{pjId}")
    public String buscarPorClientePJ(@PathVariable Long pjId, Model model){
        PessoaJuridica pj = this.pessoaJuridicaService.encontrarClientePorId(pjId);
        Cliente cliente = pj.getCliente();
        model.addAttribute("cliente", cliente);
        model.addAttribute("telefones", cliente.getTelefones());
        model.addAttribute("enderecos", cliente.getEnderecos());
        model.addAttribute("contas", cliente.getContas());
        
        model.addAttribute("especId", pjId);
        model.addAttribute("cnpj", pj.getCnpj()); 
        return "clientes/detalhes-pj";
    }

    @GetMapping(value = "/clientes/cadastrar")
    public String registrarclientes(Model model){
        model.addAttribute("pessoaFisica", new PessoaFisica());
        model.addAttribute("pessoaJuridica", new PessoaJuridica());
        return "clientes/cadastrar";
    }

    @PostMapping(value = "/clientes/cadastrar/pessoa-fisica")
    public String registrarPessoaFisica(@ModelAttribute PessoaFisica pessoaFisica, Model model){
        Cliente cliente = pessoaFisica.getCliente();
        cliente.setTipoCliente(TipoCliente.PF);
        pessoaFisica.setCliente(cliente);
        this.pessoaFisicaService.registrarOuAtualizarCliente(pessoaFisica);
        return "redirect:/clientes/cadastrar";
    }

    @PostMapping(value = "/clientes/cadastrar/pessoa-juridica")
    public String registrarPessoaJuridica(@ModelAttribute PessoaJuridica pessoaJuridica, Model model){
        Cliente cliente = pessoaJuridica.getCliente();
        cliente.setTipoCliente(TipoCliente.PF);
        pessoaJuridica.setCliente(cliente);
        this.pessoaJuridicaService.registrarOuAtualizarCliente(pessoaJuridica);
        return "redirect:/clientes/cadastrar";
    }

    @GetMapping(value = "/clientes/atualizar/pessoa-fisica/{pfId}")
    public String alterarPessoaFisica(@PathVariable Long pfId, Model model){
        model.addAttribute("pessoaFisica", this.pessoaFisicaService.encontrarClientePorId(pfId));
        return "/clientes/atualizar-pf";
    }

    @PostMapping(value = "/clientes/atualizar/pessoa-fisica/{pfId}")
    public String atualizarPessoaFisica(
        @PathVariable Long pfId,
        @ModelAttribute PessoaFisica pessoaFisica,
        Model model
        ){
        this.pessoaFisicaService.atualizarCliente(pfId, pessoaFisica);
        return "redirect:/clientes";
    }

    @GetMapping(value = "/clientes/atualizar/pessoa-juridica/{pfId}")
    public String alterarPessoaJuridica(@PathVariable Long pfId, Model model){
        model.addAttribute("pessoaJuridica", this.pessoaJuridicaService.encontrarClientePorId(pfId));
        return "/clientes/atualizar-pj";
    }

    @PostMapping(value = "/clientes/atualizar/pessoa-juridica/{pjId}")
    public String atualizarPessoaJuridica(
        @PathVariable Long pjId,
        @ModelAttribute PessoaJuridica pessoaJuridica,
        Model model
        ){
        this.pessoaJuridicaService.atualizarCliente(pjId, pessoaJuridica);
        return "redirect:/clientes";
    }

    @GetMapping(value = "/clientes/deletar/pessoa-fisica/{pfId}")
    public String deletarPessoaFisica(
        @PathVariable Long pfId
        ){
        this.pessoaFisicaService.deletarClientePorId(pfId);
        return "redirect:/clientes";
    }

    @GetMapping(value = "/clientes/deletar/pessoa-juridica/{pjId}")
    public String deletarPessoaJuridica(
        @PathVariable Long pjId
        ){
        this.pessoaJuridicaService.deletarClientePorId(pjId);
        return "redirect:/clientes";
    }
}
