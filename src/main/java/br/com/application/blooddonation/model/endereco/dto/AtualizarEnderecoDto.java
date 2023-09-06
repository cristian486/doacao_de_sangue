package br.com.application.blooddonation.model.endereco.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AtualizarEnderecoDto(@NotBlank(message = "Obrigatório o preenchimento da rua/logradouro")
                                    String rua,
                                    @Min(1)
                                    Integer numero,
                                    String bairro,
                                    @Pattern(regexp = "\\d{8}", message = "É esperado oito dígitos sem ponto ou traço.")
                                    String cep) {

}
