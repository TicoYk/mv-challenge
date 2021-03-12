package com.github.ticoyk.mvchallenge.controller;

import com.github.ticoyk.mvchallenge.model.PessoaFisica;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClienteController {
    
    @GetMapping(value = "/clientes")
    public String clientes(Model model){
        model.addAttribute("pessoaFisica", new PessoaFisica());
        return "clientes";
    }

}
