package com.github.ticoyk.mvchallenge.controller;

import com.github.ticoyk.mvchallenge.constants.TipoCliente;
import com.github.ticoyk.mvchallenge.model.Cliente;
import com.github.ticoyk.mvchallenge.model.Telefone;
import com.github.ticoyk.mvchallenge.service.ClienteService;
import com.github.ticoyk.mvchallenge.service.TelefoneService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TelefoneController {
    
    private TelefoneService telefoneService;
    private ClienteService clienteService;

    public TelefoneController(TelefoneService telefoneService, ClienteService clienteService){
        this.telefoneService = telefoneService;
        this.clienteService = clienteService;
    }
    // especID = Id da especialização PF ou PJ
    @GetMapping(value = "/clientes/{clienteId}/{especId}/telefones/cadastrar")
    public String formularioClienteTelefone(
        @PathVariable Long clienteId, 
        @PathVariable Long especId,
        Model model
        ){
        model.addAttribute("clienteId", clienteId);
        model.addAttribute("especId", especId);
        model.addAttribute("telefone", new Telefone());

        return "clientes/telefones/cadastrar";
    }

    @PostMapping(value = "/clientes/{clienteId}/{especId}/telefones/cadastrar")
    public String registrarClienteTelefone(
        @PathVariable Long clienteId, 
        @PathVariable Long especId,
        @ModelAttribute Telefone telefone,
        Model model
        ){
        Cliente cliente = this.clienteService.adicionarTelefone(clienteId, telefone);
        if(cliente.getTipoCliente().equals(TipoCliente.PF)){
            return "redirect:/clientes/pessoa-fisica/" + especId;
        }
        if(cliente.getTipoCliente().equals(TipoCliente.PJ)){
            return "redirect:/clientes/pessoa-juridica/" + especId;
        }
        return "redirect:/clientes";
    }
}
