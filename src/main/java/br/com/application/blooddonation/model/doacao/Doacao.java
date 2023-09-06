package br.com.application.blooddonation.model.doacao;

import br.com.application.blooddonation.model.doador.Doador;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Doacao {

    @Id
    private String id;
    private Doador doador;
    private LocalDate data;
    private BigDecimal quantidade;

}
