package br.com.application.blooddonation.model.endereco.dto;

import br.com.application.blooddonation.model.endereco.Endereco;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CadastroEnderecoDto(@NotBlank(message = "Obrigatório o preenchimento da rua/logradouro")
                                  String rua,
                                  @Min(1)
                                  @NotNull(message = "Obrigatório o preenchimento do número do edifício")
                                  Integer numero,
                                  @NotBlank(message = "Obrigatório o preenchimento do bairro")
                                  String bairro,
                                  @Pattern(regexp = "\\d{8}", message = "É esperado oito dígitos sem ponto ou traço.")
                                  @NotBlank
                                  String cep) {

    public Endereco toDomain() {
        return new Endereco(null, this.rua,  this.numero,  this.bairro,  this.cep);
    }
}
