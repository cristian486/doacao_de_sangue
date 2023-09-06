package br.com.application.blooddonation.model.endereco.dto;

import br.com.application.blooddonation.model.endereco.Endereco;

public record DetalhesEnderecoDto(String id, String rua, Integer numero, String bairro, String cep) {

    public DetalhesEnderecoDto(Endereco endereco) {
        this(endereco.getId(), endereco.getRua(), endereco.getNumero(), endereco.getBairro(), endereco.getCep());
    }
}
