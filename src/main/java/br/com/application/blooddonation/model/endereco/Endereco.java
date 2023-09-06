package br.com.application.blooddonation.model.endereco;

import br.com.application.blooddonation.model.endereco.dto.AtualizarEnderecoDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {

    @Id
    private String id;
    private String rua;
    private Integer numero;
    private String bairro;
    private String cep;

    public void atualizar(AtualizarEnderecoDto endereco) {
        if(endereco.rua() != null && !endereco.rua().isBlank())
            this.rua = endereco.rua();

        if(endereco.numero() != null && !this.numero.equals(endereco.numero()))
            this.numero = endereco.numero();

        if(endereco.bairro() != null && !endereco.bairro().isBlank())
            this.bairro = endereco.bairro();

        if(endereco.cep() != null && !endereco.cep().isBlank())
            this.cep = endereco.cep();
    }
}
