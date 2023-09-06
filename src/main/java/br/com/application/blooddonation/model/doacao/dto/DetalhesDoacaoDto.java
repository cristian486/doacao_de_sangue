package br.com.application.blooddonation.model.doacao.dto;

import br.com.application.blooddonation.model.doacao.Doacao;
import br.com.application.blooddonation.model.doador.dto.DetalhesDoadorDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record DetalhesDoacaoDto(String id, DetalhesDoadorDto doador, LocalDate data, BigDecimal quantidade) {


    public DetalhesDoacaoDto(Doacao doacao) {
        this(doacao.getId(), new DetalhesDoadorDto(doacao.getDoador()), doacao.getData(), doacao.getQuantidade());
    }
}
