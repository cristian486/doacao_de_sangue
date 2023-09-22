package br.com.application.blooddonation.model.usuario.dto;

import br.com.application.blooddonation.model.usuario.Usuario;
import jakarta.validation.constraints.NotBlank;

public record DetalhesUsuarioDto(String id, String usuario, String senha) {

    public DetalhesUsuarioDto(Usuario usuario) {
        this(usuario.getId(), usuario.getUsuario(), usuario.getSenha());
    }
}
