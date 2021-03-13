package com.github.ticoyk.mvchallenge.service;

import com.github.ticoyk.mvchallenge.model.Conta;
import com.github.ticoyk.mvchallenge.model.Transacao;
import com.github.ticoyk.mvchallenge.repository.ContaRepository;
import com.github.ticoyk.mvchallenge.repository.TransacaoRepository;

import org.springframework.stereotype.Service;

@Service
public class TransacaoService {
    
    private ContaRepository contaRepository;
    private TransacaoRepository transacaoRepository;

    public TransacaoService( ContaRepository contaRepository, TransacaoRepository transacaoRepository){
        this.contaRepository = contaRepository;
        this.transacaoRepository = transacaoRepository;
    }

    public Transacao registrarTransacao(Long contaId, Transacao transacao){
        Conta conta = this.contaRepository.findById(contaId).get();
        transacao.setConta(conta);
        return this.transacaoRepository.save(transacao);
    }
}
