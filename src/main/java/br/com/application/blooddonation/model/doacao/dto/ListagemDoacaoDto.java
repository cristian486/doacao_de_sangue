package br.com.application.blooddonation.model.doacao.dto;

import br.com.application.blooddonation.model.doacao.Doacao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record ListagemDoacaoDto(String id, String doadorId, LocalDate data, BigDecimal quantidade) {


    public ListagemDoacaoDto(Doacao doacao) {
        this(doacao.getId(), doacao.getDoador().getId(), doacao.getData(), doacao.getQuantidade());
    }
}
