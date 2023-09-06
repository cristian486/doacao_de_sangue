package br.com.application.blooddonation.model.doador.dto;

import br.com.application.blooddonation.model.contato.dto.AtualizarContatoDto;
import br.com.application.blooddonation.model.contato.dto.CadastroContatoDto;
import br.com.application.blooddonation.model.doador.Doador;
import br.com.application.blooddonation.model.doador.Sexo;
import br.com.application.blooddonation.model.endereco.dto.AtualizarEnderecoDto;
import br.com.application.blooddonation.model.endereco.dto.CadastroEnderecoDto;
import br.com.application.blooddonation.model.tiposanguineo.TipoSanguineo;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public record AtualizarDoadorDto(@NotBlank(message = "Obrigatório o preenchimento do nome")
                                String nome,
                                @Pattern(regexp = "\\d{11}", message = "São esperados onze dígitos sem ponto ou traço")
                                String cpf,
                                @Pattern(regexp = "\\d{9,13}", message = "São esperados de nove a treze dígitos sem ponto ou traço")
                                String rg,
                                Sexo sexo,
                                @Past
                                @JsonFormat(pattern = "dd/MM/yyyy")
                                LocalDate dataDeNascimento,
                                @Positive
                                BigDecimal altura,
                                @Positive
                                BigDecimal peso,
                                TipoSanguineo tipoSanguineo,
                                @Valid
                                AtualizarContatoDto contato,
                                @Valid
                                AtualizarEnderecoDto endereco) {
}
