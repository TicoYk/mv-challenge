package com.github.ticoyk.mvchallenge.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.github.ticoyk.mvchallenge.model.Cliente;
import com.github.ticoyk.mvchallenge.model.report.Movimentacao;
import com.github.ticoyk.mvchallenge.model.report.XPTOReport;
import com.github.ticoyk.mvchallenge.repository.ClienteRepository;

import org.springframework.stereotype.Service;

@Service
public class XPTORelatorioService {

    private ClienteRepository clienteRepository;

    public XPTORelatorioService(
        ClienteRepository clienteRepository
    ){
        this.clienteRepository = clienteRepository;
    }

    public XPTOReport gerarRelatorioXPTOEntreDatas(LocalDate from, LocalDate to){
        List<Movimentacao> movimentacoes = new ArrayList<Movimentacao>();
        List<Cliente> clientes = new ArrayList<Cliente>();

        this.clienteRepository.findAll().iterator().forEachRemaining( cliente -> {
            if( !cliente.getNome().equals("XPTO")){
                clientes.add(cliente);
            }
        });
        // Aberração mas funciona.
        clientes.forEach( cliente -> {
            Movimentacao movimentacao = new Movimentacao(cliente.getNome());
            cliente.getContas().iterator().forEachRemaining( conta -> {
                conta.getTransacoes().iterator().forEachRemaining( transacao -> {
                    if(transacao.getDataCriacao().isAfter(from) && 
                        transacao.getDataCriacao().isBefore(to)
                    )
                    movimentacao.addTransacaoValor(transacao.getValor());
                });
            });
            movimentacoes.add(movimentacao);
        });
        return new XPTOReport(from, to, movimentacoes);
    }
}
