package br.com.application.blooddonation.model.usuario.dto;

import jakarta.validation.constraints.NotBlank;

public record CadastroUsuarioDto(@NotBlank
                                 String usuario,
                                 @NotBlank
                                 String senha) {
}
