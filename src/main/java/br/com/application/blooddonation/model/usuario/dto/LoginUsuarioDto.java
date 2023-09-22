package br.com.application.blooddonation.model.usuario.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginUsuarioDto(@NotBlank String usuario, @NotBlank String senha) {
}
