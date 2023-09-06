package br.com.application.blooddonation.model.contato.dto;

import jakarta.validation.constraints.Email;


public record AtualizarContatoDto(@Email String email, String telefoneFixo, String celular) {

}
