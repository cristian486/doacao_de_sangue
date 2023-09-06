package br.com.application.blooddonation.model.contato;

import br.com.application.blooddonation.model.contato.dto.AtualizarContatoDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Contato {

    @Id
    private String id;
    private String email;
    private String telefoneFixo;
    private String celular;

    public void atualizar(AtualizarContatoDto contato) {
        if (contato.email() != null && !contato.email().isBlank())
            this.email = contato.email();

        if (contato.telefoneFixo() != null && !contato.telefoneFixo().isBlank())
            this.telefoneFixo = contato.telefoneFixo();

        if (contato.celular() != null && !contato.celular().isBlank())
            this.celular = contato.celular();
    }
}