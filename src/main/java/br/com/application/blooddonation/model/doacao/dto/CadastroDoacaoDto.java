package br.com.application.blooddonation.model.doacao.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CadastroDoacaoDto(@NotBlank(message = "Obrigatório o envio do ID do doador")
                                String doadorId,
                                @NotNull(message = "Obrigatório o envio da data da doação")
                                @JsonFormat(pattern = "dd/MM/yyyy")
                                @PastOrPresent(message = "A data fornecida deve ser atual ou passada")
                                LocalDate data,
                                @NotNull(message = "Obrigatório o envio da quantidade doada")
                                @DecimalMin("0.1")
                                BigDecimal quantidade) {
}
