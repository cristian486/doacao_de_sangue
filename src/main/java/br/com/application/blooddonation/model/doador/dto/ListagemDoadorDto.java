package br.com.application.blooddonation.model.doador.dto;

import br.com.application.blooddonation.model.doador.Doador;
import br.com.application.blooddonation.model.tiposanguineo.TipoSanguineo;

import java.util.UUID;

public record ListagemDoadorDto(String id, String nome, TipoSanguineo tipoSanguineo) {

    public ListagemDoadorDto(Doador doador) {
        this(doador.getId(), doador.getNome(), doador.getTipoSanguineo());
    }
}
