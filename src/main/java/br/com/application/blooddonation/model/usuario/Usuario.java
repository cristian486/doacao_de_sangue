package br.com.application.blooddonation.model.usuario;

import lombok.Getter;
import org.springframework.data.annotation.Id;

@Getter
public class Usuario {

    @Id
    private String id;
    private String usuario;
    private String senha;
}
