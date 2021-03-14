package com.github.ticoyk.mvchallenge.repository;

import java.time.LocalDate;
import java.util.List;

import com.github.ticoyk.mvchallenge.model.Transacao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TransacaoRepository extends CrudRepository<Transacao, Long>{
    @Query(value="SELECT transacao FROM Transacao transacao WHERE transacao.dataCriacao > subtrairData(:date, :days)")
    List<Transacao> findTransacaoWithDayDifferenceByDay(@Param("date") LocalDate date, @Param("days")Integer days);

    @Query(value = 
    "SELECT transacao FROM Cliente cliente " +
    "INNER JOIN Conta conta ON cliente = conta.cliente " +
    "INNER JOIN Transacao transacao ON conta = transacao.conta "+
    "WHERE cliente.id = :clienteId AND transacao.dataCriacao BETWEEN :data1 AND :data2")
    List<Transacao> findTransacaoByClientIdBetweenDates(
        @Param("clienteId")Long clienteId, 
        @Param("data1") LocalDate date1, 
        @Param("data2") LocalDate data2);

    @Query(value = 
    "SELECT transacao FROM Cliente cliente " +
    "INNER JOIN Conta conta ON cliente = conta.cliente " +
    "INNER JOIN Transacao transacao ON conta = transacao.conta "+
    "WHERE cliente.id = :clienteId")
    List<Transacao> findTransacaoByClientId(
        @Param("clienteId")Long clienteId);

    @Query(value = 
    "SELECT transacao FROM Cliente cliente " +
    "INNER JOIN Conta conta ON cliente = conta.cliente " +
    "INNER JOIN Transacao transacao ON conta = transacao.conta "+
    "WHERE cliente.id = :clienteId AND transacao.dataCriacao < :data1")
    List<Transacao> findTransacaoByClientIdFromStartToDate(
        @Param("clienteId")Long clienteId, 
        @Param("data1") LocalDate date1);

    @Query(value = 
    "SELECT sum(TRANSACAO.VALOR) FROM CLIENTE " +
    "INNER JOIN CONTA ON CLIENTE.ID = CONTA.CLIENTE_ID " +
    "INNER JOIN TRANSACAO ON CONTA.ID = TRANSACAO.CONTA_ID "+
    "WHERE CLIENTE.ID = :clienteId AND " +
    "TRANSACAO.IDENTIFICADOR = CLIENTE.ID", nativeQuery = true)
    Double findByClienteValoPagoXPTO(@Param("clienteId") Long clienteId);

    @Query(value = 
    "SELECT count(TRANSACAO.ID) FROM CLIENTE " +
    "INNER JOIN CONTA ON CLIENTE.ID = CONTA.CLIENTE_ID " +
    "INNER JOIN TRANSACAO ON CONTA.ID = TRANSACAO.CONTA_ID "+
    "WHERE CLIENTE.ID = :clienteId AND " +
    "TRANSACAO.DATA_CRIACAO > subtrairData(:date, :days) AND " +
    "TRANSACAO.IDENTIFICADOR IS NULL", nativeQuery = true)
    Integer findTransacaoCountByClienteAndDate(@Param("clienteId") Long clienteId, @Param("date") LocalDate date, @Param("days")Integer days);

}
