package com.github.ticoyk.mvchallenge.model.report;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.ticoyk.mvchallenge.model.Endereco;

@JsonSerialize

public class ClienteReport {

    private LocalDate dataInicio;
    private LocalDate dataFim;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "cliente"})
    private List<Endereco> enderecos;
    private Double movimentacoesReceita;
    private Double movimentacoesDespesa;
    private Double totalMovimentacoes;
    private Double saldoInicial;
    private Double saldoAtual;
    private Double valorPago;

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> endereco) {
        this.enderecos = endereco;
    }

    public Double getMovimentacoesReceita() {
        return movimentacoesReceita;
    }

    public void setMovimentacoesReceita(Double movimentacoesReceita) {
        this.movimentacoesReceita = movimentacoesReceita;
    }

    public Double getMovimentacoesDespesa() {
        return movimentacoesDespesa;
    }

    public void setMovimentacoesDespesa(Double movimentacoesDespesa) {
        this.movimentacoesDespesa = movimentacoesDespesa;
    }

    public Double getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(Double saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public Double getSaldoAtual() {
        return saldoAtual;
    }

    public void setSaldoAtual(Double saldoAtual) {
        this.saldoAtual = saldoAtual;
    }

    public Double getValorPago() {
        return valorPago;
    }

    public void setValorPago(Double valorPago) {
        this.valorPago = valorPago;
    }

    public Double getTotalMovimentacoes() {
        return totalMovimentacoes;
    }

    public void setTotalMovimentacoes(Double totalMovimentacoes) {
        this.totalMovimentacoes = totalMovimentacoes;
    }

}
