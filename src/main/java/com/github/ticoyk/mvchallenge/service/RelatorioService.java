package com.github.ticoyk.mvchallenge.service;

import java.time.LocalDate;
import java.util.List;

import com.github.ticoyk.mvchallenge.constants.TipoTransacao;
import com.github.ticoyk.mvchallenge.model.Cliente;
import com.github.ticoyk.mvchallenge.model.Transacao;
import com.github.ticoyk.mvchallenge.model.report.ClienteReport;
import com.github.ticoyk.mvchallenge.repository.ClienteRepository;
import com.github.ticoyk.mvchallenge.repository.TransacaoRepository;

import org.springframework.stereotype.Service;

@Service
public class RelatorioService {
    
    private TransacaoRepository transacaoRepository;
    private ClienteRepository clienteRepository;

    public RelatorioService(
        TransacaoRepository transacaoRepository,
        ClienteRepository clienteRepository
    ){
        this.transacaoRepository = transacaoRepository;
        this.clienteRepository = clienteRepository;
    }

    public ClienteReport gerarRelatorioCliente(Long clienteId){
        Cliente cliente = this.clienteRepository.findById(clienteId).get();
        
        List<Transacao> transacoes = this.buscarTransacao(cliente);

        ClienteReport clienteReport = new ClienteReport();
        clienteReport.setEnderecos(cliente.getEnderecos());
        
        Double receita = this.gerarValorTotal(TipoTransacao.RECEITA, transacoes);
        Double despesa = this.gerarValorTotal(TipoTransacao.DESPESA, transacoes);
        clienteReport.setMovimentacoesReceita(receita);
        clienteReport.setMovimentacoesDespesa(despesa);
        clienteReport.setTotalMovimentacoes(receita+despesa);
        clienteReport.setSaldoAtual(receita - despesa);
        clienteReport.setValorPago(this.buscarValorPago(cliente));
        
        return clienteReport;
    }

    public ClienteReport gerarRelatorioClientePeriodo(LocalDate from, LocalDate to, Long clienteId){
        Cliente cliente = this.clienteRepository.findById(clienteId).get();
        
        List<Transacao> transacoes = this.buscarTransacaoPorData(cliente, from, to);
        List<Transacao> transacoesAntigas = this.buscarTransacaoAntesData(cliente, from);

        ClienteReport clienteReport = new ClienteReport();
        clienteReport.setDataInicio(from);
        clienteReport.setDataFim(to);
        clienteReport.setEnderecos(cliente.getEnderecos());
        
        Double receita = this.gerarValorTotal(TipoTransacao.RECEITA, transacoes);
        Double despesa = this.gerarValorTotal(TipoTransacao.DESPESA, transacoes);
        clienteReport.setMovimentacoesReceita(receita);
        clienteReport.setMovimentacoesDespesa(despesa);
        clienteReport.setSaldoAtual(receita - despesa);
        clienteReport.setSaldoInicial(this.gerarSaldo(transacoesAntigas));
        clienteReport.setValorPago(this.buscarValorPago(cliente));

        return clienteReport;
    }

    private Double gerarValorTotal(TipoTransacao tipoTransacao, List<Transacao> transacoes){
        return transacoes.stream()
            .filter(transacao -> transacao.getTipo().equals(tipoTransacao))
            .mapToDouble(Transacao::getValor).sum();
    }

    private Double buscarValorPago(Cliente cliente){
        return this.transacaoRepository.findByClienteValoPagoXPTO(cliente.getId());
    }

    private Double gerarSaldo(List<Transacao> transacoes){
        return transacoes.stream().mapToDouble(Transacao::getValor).sum();
    }

    private List<Transacao> buscarTransacao(Cliente cliente){
        return this.transacaoRepository.findTransacaoByClientId(cliente.getId());
    }

    private List<Transacao> buscarTransacaoPorData(Cliente cliente, LocalDate from, LocalDate to){
        return this.transacaoRepository.findTransacaoByClientIdBetweenDates(cliente.getId(), from, to);
    }

    private List<Transacao> buscarTransacaoAntesData(Cliente cliente, LocalDate data){
        return this.transacaoRepository.findTransacaoByClientIdFromStartToDate(cliente.getId(), data);
    }

}
