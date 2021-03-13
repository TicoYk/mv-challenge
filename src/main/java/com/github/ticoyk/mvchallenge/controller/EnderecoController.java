package com.github.ticoyk.mvchallenge.controller;

import com.github.ticoyk.mvchallenge.constants.TipoCliente;
import com.github.ticoyk.mvchallenge.model.Cliente;
import com.github.ticoyk.mvchallenge.model.Endereco;
import com.github.ticoyk.mvchallenge.service.ClienteService;
import com.github.ticoyk.mvchallenge.service.EnderecoService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EnderecoController {
    private EnderecoService enderecoService;
    private ClienteService clienteService;

    public EnderecoController(EnderecoService enderecoService, ClienteService clienteService){
        this.enderecoService = enderecoService;
        this.clienteService = clienteService;
    }
    // especID = Id da especialização PF ou PJ
    @GetMapping(value = "/clientes/{clienteId}/{especId}/enderecos/cadastrar")
    public String formularioClienteEndereco(
        @PathVariable Long clienteId, 
        @PathVariable Long especId,
        Model model
        ){
        model.addAttribute("clienteId", clienteId);
        model.addAttribute("especId", especId);
        model.addAttribute("endereco", new Endereco());

        return "clientes/enderecos/cadastrar";
    }

    @PostMapping(value = "/clientes/{clienteId}/{especId}/enderecos/cadastrar")
    public String registrarClienteEndereco(
        @PathVariable Long clienteId, 
        @PathVariable Long especId,
        @ModelAttribute Endereco endereco,
        Model model
        ){
        Cliente cliente = this.clienteService.adicionarEndereco(clienteId, endereco);
        
        if(cliente.getTipoCliente().equals(TipoCliente.PF)){
            return "redirect:/clientes/pessoa-fisica/" + especId;
        }
        if(cliente.getTipoCliente().equals(TipoCliente.PJ)){
            return "redirect:/clientes/pessoa-juridica/" + especId;
        }
        return "redirect:/clientes";
    }
}
