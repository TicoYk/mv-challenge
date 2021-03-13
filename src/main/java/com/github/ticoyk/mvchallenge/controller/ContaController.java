package com.github.ticoyk.mvchallenge.controller;

import com.github.ticoyk.mvchallenge.constants.TipoCliente;
import com.github.ticoyk.mvchallenge.model.Cliente;
import com.github.ticoyk.mvchallenge.model.Conta;
import com.github.ticoyk.mvchallenge.service.ClienteService;
import com.github.ticoyk.mvchallenge.service.ContaService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ContaController {
        
    private ContaService contaService;

    public ContaController(ContaService contaService, ClienteService clienteService){
        this.contaService = contaService;
    }
    // especID = Id da especialização PF ou PJ
    @GetMapping(value = "/clientes/{clienteId}/{especId}/contas/cadastrar")
    public String formularioClienteConta(
        @PathVariable Long clienteId, 
        @PathVariable Long especId,
        Model model
        ){
        model.addAttribute("clienteId", clienteId);
        model.addAttribute("especId", especId);
        model.addAttribute("conta", new Conta());

        return "clientes/contas/cadastrar";
    }

    @PostMapping(value = "/clientes/{clienteId}/{especId}/contas/cadastrar")
    public String registrarClienteConta(
        @PathVariable Long clienteId, 
        @PathVariable Long especId,
        @ModelAttribute Conta conta,
        Model model
        ){
        Cliente cliente = this.contaService.cadastrarConta(clienteId, conta);
        
        if(cliente.getTipoCliente().equals(TipoCliente.PF)){
            return "redirect:/clientes/pessoa-fisica/" + especId;
        }
        if(cliente.getTipoCliente().equals(TipoCliente.PJ)){
            return "redirect:/clientes/pessoa-juridica/" + especId;
        }
        return "redirect:/clientes";
    }

    @GetMapping(value = "/clientes/{clienteId}/{especId}/contas/{contaId}/atualizar")
    public String formularioClienteConta(
        @PathVariable Long clienteId, 
        @PathVariable Long especId,
        @PathVariable Long contaId,
        Model model
        ){
        
        model.addAttribute("clienteId", clienteId);
        model.addAttribute("especId", especId);
        model.addAttribute("conta", this.contaService.encontrarContaPorId(contaId));

        return "clientes/contas/atualizar";
    }

    @PostMapping(value = "/clientes/{clienteId}/{especId}/contas/{contaId}/atualizar")
    public String atualizarClienteConta(
        @PathVariable Long clienteId, 
        @PathVariable Long especId,
        @PathVariable Long contaId,
        @ModelAttribute Conta novaConta,
        Model model
        ){
        Conta conta = this.contaService.atualizarConta(contaId, novaConta);
        Cliente cliente = conta.getCliente();

        if(cliente.getTipoCliente().equals(TipoCliente.PF)){
            return "redirect:/clientes/pessoa-fisica/" + especId;
        }
        if(cliente.getTipoCliente().equals(TipoCliente.PJ)){
            return "redirect:/clientes/pessoa-juridica/" + especId;
        }
        return "redirect:/clientes";
    }

    @GetMapping(value = "/clientes/{clienteId}/{especId}/contas/{contaId}/deletar")
    public String deletarClienteConta(
        @PathVariable Long clienteId, 
        @PathVariable Long especId,
        @PathVariable Long contaId,
        @ModelAttribute Conta conta,
        Model model
        ){
        Cliente cliente = this.contaService.deletarContaPorIdTrazerCliente(contaId);
        
        if(cliente.getTipoCliente().equals(TipoCliente.PF)){
            return "redirect:/clientes/pessoa-fisica/" + especId;
        }
        if(cliente.getTipoCliente().equals(TipoCliente.PJ)){
            return "redirect:/clientes/pessoa-juridica/" + especId;
        }
        return "redirect:/clientes";
    }

}
