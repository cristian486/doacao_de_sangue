package br.com.application.blooddonation.model.contato.dto;

import br.com.application.blooddonation.infra.validation.OneCannotBeNullAndEmpty;
import br.com.application.blooddonation.model.contato.Contato;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@OneCannotBeNullAndEmpty(campos = {"telefoneFixo", "celular"})
public record CadastroContatoDto(@Email
                                 @NotBlank(message = "Obrigatório o preenchimento do e-mail")
                                 String email,
                                 String telefoneFixo,
                                 String celular) {

    public Contato toDomain() {
        return new Contato(null, this.email, this.telefoneFixo, this.celular);
    }
}
