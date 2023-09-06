package br.com.application.blooddonation.model.doador.dto;

import br.com.application.blooddonation.model.contato.dto.DetalhesContatoDto;
import br.com.application.blooddonation.model.doador.Doador;
import br.com.application.blooddonation.model.endereco.dto.DetalhesEnderecoDto;
import br.com.application.blooddonation.model.tiposanguineo.TipoSanguineo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record DetalhesDoadorDto(String id, String nome, String cpf, String rg, LocalDate dataDeNascimento,
                                BigDecimal altura, BigDecimal peso, TipoSanguineo tipoSanguineo,
                                DetalhesContatoDto contato, DetalhesEnderecoDto endereco) {

    public DetalhesDoadorDto(Doador doador) {
        this(doador.getId(), doador.getNome(), doador.getCpf(), doador.getRg(), doador.getDataDeNascimento(),
                doador.getAltura(), doador.getPeso(), doador.getTipoSanguineo(),
                new DetalhesContatoDto(doador.getContato()), new DetalhesEnderecoDto(doador.getEndereco()));
    }
}
