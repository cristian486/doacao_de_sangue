package br.com.application.blooddonation.model.doador.dto;

import br.com.application.blooddonation.model.contato.dto.CadastroContatoDto;
import br.com.application.blooddonation.model.doador.Doador;
import br.com.application.blooddonation.model.doador.Sexo;
import br.com.application.blooddonation.model.endereco.dto.CadastroEnderecoDto;
import br.com.application.blooddonation.model.tiposanguineo.TipoSanguineo;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CadastroDoadorDto(@NotBlank(message = "Obrigatório o preenchimento do nome")
                                String nome,
                                @Pattern(regexp = "\\d{11}", message = "São esperados onze dígitos sem ponto ou traço")
                                @NotBlank(message = "Obrigatório o preenchimento do CPF")
                                String cpf,
                                @Pattern(regexp = "\\d{9,13}", message = "São esperados de nove a treze dígitos sem ponto ou traço")
                                @NotBlank(message = "Obrigatório o preenchimento do RG")
                                String rg,
                                @NotNull
                                Sexo sexo,
                                @Past
                                @JsonFormat(pattern = "dd/MM/yyyy")
                                @NotNull(message = "Obrigatório o preenchimento da data de nascimento")
                                LocalDate dataDeNascimento,
                                @Positive
                                @NotNull(message = "Obrigatório o preenchimento da altura")
                                BigDecimal altura,
                                @Positive
                                @NotNull(message = "Obrigatório o preenchimento do peso")
                                BigDecimal peso,
                                @NotNull
                                TipoSanguineo tipoSanguineo,
                                @Valid
                                @NotNull(message = "Obrigatório o envio dos dados de contato")
                                CadastroContatoDto contato,
                                @Valid
                                @NotNull(message = "Obrigatório o envio dos dados de endereço")
                                CadastroEnderecoDto endereco) {

    public Doador toDomain() {
        return new Doador(null, this.nome, this.cpf,  this.rg,  this.sexo, this.dataDeNascimento,  this.altura,
                this.peso,  Boolean.TRUE, this.tipoSanguineo, this.contato.toDomain(),  this.endereco.toDomain());
    }
}
