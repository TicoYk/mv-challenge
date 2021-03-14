package com.github.ticoyk.mvchallenge.controller.api;

import java.util.List;

import com.github.ticoyk.mvchallenge.model.ApiErro;
import com.github.ticoyk.mvchallenge.model.Conta;
import com.github.ticoyk.mvchallenge.model.Transacao;
import com.github.ticoyk.mvchallenge.service.ContaService;
import com.github.ticoyk.mvchallenge.service.TransacaoService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/contas")
@RestController
public class ContaControllerApi {
    
    private ContaService contaService;
    private TransacaoService transacaoService;
    
    public ContaControllerApi(ContaService contaService, TransacaoService transacaoService) {
        this.contaService = contaService;
        this.transacaoService = transacaoService;
    }

    @GetMapping
    public ResponseEntity buscarContas() {
        try {
            List<Conta> contas = this.contaService.encontrarTodasContas();
            return ResponseEntity.status(HttpStatus.OK).body(contas);
        } catch ( Exception e ){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage().toString());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity buscarContasPorId(@PathVariable Long id) {
        try {
            Conta conta = this.contaService.encontrarContaPorId(id);
            return ResponseEntity.status(HttpStatus.OK).body(conta);
        } catch ( Exception e ){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage().toString());
        }
    }

    @PostMapping("/{contaId}/transacao")
    public ResponseEntity realizarTransacao(@PathVariable Long contaId, @RequestBody Transacao transacao){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(
                this.transacaoService.registrarTransacao(contaId, transacao)
            );
        } catch ( Exception e ){
            ApiErro apiErro = new ApiErro("Parâmetros incorretos", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiErro);
        }
    }
}
