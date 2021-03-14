package com.github.ticoyk.mvchallenge.model.report;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class Movimentacao {

    private String clienteNome;
    private Integer movimentacao = 0;
    private Double valorMovimentacao = (double) 0;

    public Movimentacao(){}
    public Movimentacao(String clienteNome) {
        this.clienteNome = clienteNome;
    }

    public Movimentacao(String clienteNome, Integer movimentacao, Double valorMovimentacao) {
        this.clienteNome = clienteNome;
        this.movimentacao = movimentacao;
        this.valorMovimentacao = valorMovimentacao;
    }

    public String getCliente() {
        return clienteNome;
    }
    public void setCliente(String clienteNome) {
        this.clienteNome = clienteNome;
    }
    public Integer getMovimentacao() {
        return movimentacao;
    }
    public void setMovimentacao(Integer movimentacao) {
        this.movimentacao = movimentacao;
    }
    public Double getValorMovimentacao() {
        return valorMovimentacao;
    }
    public void setValorMovimentacao(Double valorMovimentacao) {
        this.valorMovimentacao = valorMovimentacao;
    }

    public void addTransacaoValor(Double valor){
        this.valorMovimentacao += valor;
        this.movimentacao++;
    }

    public String getClienteNome() {
        return clienteNome;
    }

    public void setClienteNome(String clienteNome) {
        this.clienteNome = clienteNome;
    }

}
