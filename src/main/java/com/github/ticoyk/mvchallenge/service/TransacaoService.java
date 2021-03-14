package com.github.ticoyk.mvchallenge.service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import com.github.ticoyk.mvchallenge.constants.TipoTransacao;
import com.github.ticoyk.mvchallenge.model.Cliente;
import com.github.ticoyk.mvchallenge.model.Conta;
import com.github.ticoyk.mvchallenge.model.Transacao;
import com.github.ticoyk.mvchallenge.repository.ClienteRepository;
import com.github.ticoyk.mvchallenge.repository.ContaRepository;
import com.github.ticoyk.mvchallenge.repository.TransacaoRepository;

import org.springframework.stereotype.Service;

@Service
public class TransacaoService {
    
    private ClienteRepository clienteRepository;
    private ContaRepository contaRepository;
    private TransacaoRepository transacaoRepository;

    public TransacaoService( 
        ClienteRepository clienteRepository, 
        ContaRepository contaRepository, 
        TransacaoRepository transacaoRepository
        ){
        this.clienteRepository = clienteRepository;
        this.contaRepository = contaRepository;
        this.transacaoRepository = transacaoRepository;
    }

    public Transacao registrarTransacao(Long contaId, Transacao transacao){
        Conta contaAtual = this.contaRepository.findById(contaId).get();
        Double valor = this.creditarXPTO(contaAtual);
        // Adicionando Taxa a o valor da transação
        if( transacao.getTipo().equals(TipoTransacao.DESPESA)){
            transacao.setValor(transacao.getValor() + valor);
        } else {
            transacao.setValor(transacao.getValor() - valor);
        }
        
        transacao.setIdentificador("");
        transacao.setConta(contaAtual);
        return this.transacaoRepository.save(transacao);
    }

    public List<Transacao> encontrarTransacaoPorTempo(){
        return this.transacaoRepository.findTransacaoWithDayDifferenceByDay(LocalDate.now(), 30);
    }

    //Até 10 movimentações o cliente irá pagar R$ 1,00 por movimentação; 
    //De 10 a 20 movimentações o cliente irá pagar R$ 0,75 por movimentação; 
    //Acima de 20 movimentações o cliente irá pagar R$ 0,50 por movimentação;
    private Double creditarXPTO(Conta conta){
        Integer numeroTransacoes = 
            this.transacaoRepository.findTransacaoCountByClienteAndDate(
                conta.getCliente().getId(), 
                LocalDate.now(), 
                30
                );
        Cliente xpto = this.clienteRepository.findByNome("XPTO");
        Double valor;
        Conta contaXPTO = xpto.getContas().get(0);

        if(numeroTransacoes <= 10) {
            valor = Double.valueOf(1);
        } else if(numeroTransacoes <= 20) {
            valor = Double.valueOf(0.75);
        } else {
            valor = Double.valueOf(0.50);
        }
        
        this.transacaoRepository.save(
            new Transacao(TipoTransacao.RECEITA, valor, contaXPTO, conta.getCliente().getId().toString())
            );
        return valor;
    }
    
}
