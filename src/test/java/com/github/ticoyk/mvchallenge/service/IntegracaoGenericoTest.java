package com.github.ticoyk.mvchallenge.service;

import com.github.ticoyk.mvchallenge.constants.TipoTransacao;
import com.github.ticoyk.mvchallenge.model.Cliente;
import com.github.ticoyk.mvchallenge.model.Transacao;
import com.github.ticoyk.mvchallenge.model.report.XPTOReport;
import com.github.ticoyk.mvchallenge.repository.TransacaoRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
@WebAppConfiguration
@Transactional
public class IntegracaoGenericoTest {
    
    @Autowired 
    private XPTORelatorioService xptoRelatorioService;

    @Autowired
    private ClienteService clienteService;

    @Autowired 
    private TransacaoRepository transacaoRepository;

    @Test
    void chequeSeOsComponentesExistemNoContexto() {
        assertThat(xptoRelatorioService, notNullValue());
        assertThat(clienteService, notNullValue());
        assertThat(transacaoRepository, notNullValue());
    }

    @Test
    void chequeSeXPTOSeraCriado() {
        Cliente cliente = this.clienteService.encontrarClientePorNome("XPTO");
        assertEquals(cliente.getNome(), "XPTO");
    }

    @Test
    void chequeXPTORelatorio() {
        Cliente cliente = this.clienteService.encontrarClientePorNome("Novo Cliente PF");
        assertEquals(cliente.getNome(), "Novo Cliente PF");
       
        this.transacaoRepository.save(new Transacao(
            TipoTransacao.DESPESA, 
            (double) 500, 
            cliente.getContas().get(0)
            ));
        
        LocalDate from = LocalDate.parse("2020-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate to = LocalDate.now();

        XPTOReport xptoReport = this.xptoRelatorioService.gerarRelatorioXPTOEntreDatas(from, to.plusDays(1));
        assertEquals((double) 500, xptoReport.getMovimentacoes().get(0).getValorMovimentacao());
    }

    

}
