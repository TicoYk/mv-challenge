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
import org.springframework.web.bind.annotation.PutMapping;

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
        Cliente cliente = this.enderecoService.cadastrarEndereco(clienteId, endereco);
        
        if(cliente.getTipoCliente().equals(TipoCliente.PF)){
            return "redirect:/clientes/pessoa-fisica/" + especId;
        }
        if(cliente.getTipoCliente().equals(TipoCliente.PJ)){
            return "redirect:/clientes/pessoa-juridica/" + especId;
        }
        return "redirect:/clientes";
    }

    @GetMapping(value = "/clientes/{clienteId}/{especId}/enderecos/{enderecoId}/atualizar")
    public String formularioAtualizarClienteEndereco(
        @PathVariable Long clienteId, 
        @PathVariable Long especId,
        @PathVariable Long enderecoId,
        Model model
        ){
        
        model.addAttribute("clienteId", clienteId);
        model.addAttribute("especId", especId);
        model.addAttribute("endereco", this.enderecoService.encontrarEnderecoPorId(enderecoId));

        return "clientes/enderecos/atualizar";
    }

    @PostMapping(value = "/clientes/{clienteId}/{especId}/enderecos/{enderecoId}/atualizar")
    public String atualizarClienteEndereco(
        @PathVariable Long clienteId, 
        @PathVariable Long especId,
        @PathVariable Long enderecoId,
        @ModelAttribute Endereco novaEndereco,
        Model model
        ){
        Endereco endereco = this.enderecoService.atualizarEndereco(enderecoId, novaEndereco);
        Cliente cliente = endereco.getCliente();

        if(cliente.getTipoCliente().equals(TipoCliente.PF)){
            return "redirect:/clientes/pessoa-fisica/" + especId;
        }
        if(cliente.getTipoCliente().equals(TipoCliente.PJ)){
            return "redirect:/clientes/pessoa-juridica/" + especId;
        }
        return "redirect:/clientes";
    }

    @GetMapping(value = "/clientes/{clienteId}/{especId}/enderecos/{enderecoId}/deletar")
    public String deletarClienteEndereco(
        @PathVariable Long clienteId, 
        @PathVariable Long especId,
        @PathVariable Long enderecoId,
        @ModelAttribute Endereco endereco,
        Model model
        ){
        Cliente cliente = this.enderecoService.deletarEnderecoPorIdTrazerCliente(enderecoId);
        
        if(cliente.getTipoCliente().equals(TipoCliente.PF)){
            return "redirect:/clientes/pessoa-fisica/" + especId;
        }
        if(cliente.getTipoCliente().equals(TipoCliente.PJ)){
            return "redirect:/clientes/pessoa-juridica/" + especId;
        }
        return "redirect:/clientes";
    }
}
