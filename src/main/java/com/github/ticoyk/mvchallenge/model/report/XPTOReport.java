package com.github.ticoyk.mvchallenge.model.report;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class XPTOReport {

    private LocalDate dataInicio;
    private LocalDate dataFim;

    private List<Movimentacao> movimentacoes = new ArrayList<Movimentacao>();

    public XPTOReport(){}

    public XPTOReport(LocalDate dataInicio, LocalDate dataFim) {
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public XPTOReport(LocalDate dataInicio, LocalDate dataFim, List<Movimentacao> movimentacoes) {
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.movimentacoes = movimentacoes;
    }

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

    public List<Movimentacao> getMovimentacoes() {
        return movimentacoes;
    }

    public void setMovimentacoes(List<Movimentacao> movimentacoes) {
        this.movimentacoes = movimentacoes;
    }
}