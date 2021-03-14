package com.github.ticoyk.mvchallenge.controller.api;

import com.github.ticoyk.mvchallenge.model.ApiErro;
import com.github.ticoyk.mvchallenge.model.report.MinhaData;
import com.github.ticoyk.mvchallenge.model.report.XPTOReport;
import com.github.ticoyk.mvchallenge.service.RelatorioService;
import com.github.ticoyk.mvchallenge.service.XPTORelatorioService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/relatorios")
public class RelatorioController {
    
    private RelatorioService relatorioService;
    private XPTORelatorioService xptoRelatorioService;

    public RelatorioController(
            RelatorioService relatorioService,
            XPTORelatorioService xptoRelatorioService
        ){
        this.relatorioService = relatorioService;
        this.xptoRelatorioService = xptoRelatorioService;
    }

    @PostMapping("/xpto")
    public ResponseEntity buscarRelatorioXpto(@RequestBody MinhaData data){
        try {
            XPTOReport report = xptoRelatorioService.gerarRelatorioXPTOEntreDatas(data.getLocalDateFrom(), data.getLocalDateTo());
            return ResponseEntity.status(HttpStatus.CREATED).body(report);
        } catch ( Exception e ) {
            ApiErro apiErro = new ApiErro("Parâmetros incorretos cheque \"from\" e \" to\" formato \"yyyy-MM-dd\"", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiErro);
        }
    }

    @PostMapping("/cliente")
    public ResponseEntity buscarRelatorioTodosClientes(){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(
                relatorioService.gerarRelatorioTodosClientes()
                );
        } catch ( Exception e ){
            ApiErro apiErro = new ApiErro("Cliente não encontrado.", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiErro);
        }
    }

    @PostMapping("/cliente/{clienteId}")
    public ResponseEntity buscarRelatorioCliente(@PathVariable("clienteId") Long clienteId){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(
                relatorioService.gerarRelatorioCliente(clienteId)
                );
        } catch ( Exception e ){
            ApiErro apiErro = new ApiErro("Cliente não encontrado.", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiErro);
        }
    }

    @PostMapping("/cliente/{clienteId}/entreDatas")
    public ResponseEntity buscarRelatorioClienteEntreDatas(
        @PathVariable("clienteId") Long clienteId,
        @RequestBody MinhaData data
        ){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(
                relatorioService.gerarRelatorioClientePeriodo(
                    data.getLocalDateFrom(), 
                    data.getLocalDateTo(), 
                    clienteId)
                );
        } catch ( Exception e ){
            ApiErro apiErro = new ApiErro("Parâmetros incorretos cheque \"from\" e \" to\" formato \"yyyy-MM-dd\"", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiErro);
        }
    }
}
