package br.com.application.blooddonation.model.contato.dto;

import br.com.application.blooddonation.model.contato.Contato;

public record DetalhesContatoDto(String id, String email, String telefoneFixo, String celular) {

    public DetalhesContatoDto(Contato contato) {
        this(contato.getId(), contato.getEmail(), contato.getTelefoneFixo(), contato.getCelular());
    }
}
