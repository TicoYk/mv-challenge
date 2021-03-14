package com.github.ticoyk.mvchallenge.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.github.ticoyk.mvchallenge.model.Transacao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TransacaoRepository extends CrudRepository<Transacao, Long>{
    @Query(value="SELECT transacao FROM Transacao transacao where transacao.dataCriacao > subtrairData(:date, :days)")
    List<Transacao> findTransacaoWithDayDifferenceByDay(@Param("date") LocalDateTime date, @Param("days")Integer days);

    @Query(value = 
    "SELECT count(TRANSACAO.ID) FROM CLIENTE " +
    "INNER JOIN CONTA ON CLIENTE.ID = CONTA.CLIENTE_ID " +
    "INNER JOIN TRANSACAO ON CONTA.ID = TRANSACAO.CONTA_ID "+
    "WHERE CLIENTE.ID = :clienteId AND TRANSACAO.DATA_CRIACAO > subtrairData(:date, :days)", nativeQuery = true)
    Integer findTransacaoCountByClienteAndDate(@Param("clienteId") Long clienteId, @Param("date") LocalDateTime date, @Param("days")Integer days);
}
