package br.com.application.blooddonation.model.doacao.dto;

import br.com.application.blooddonation.model.doacao.Doacao;
import br.com.application.blooddonation.model.doador.dto.DetalhesDoadorDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public final class DetalhesDoacaoDto extends RepresentationModel<DetalhesDoacaoDto> {
    private String id;
    private DetalhesDoadorDto doador;
    private LocalDate data;
    private BigDecimal quantidade;

    public DetalhesDoacaoDto(Doacao doacao) {
        this(doacao.getId(), new DetalhesDoadorDto(doacao.getDoador()), doacao.getData(), doacao.getQuantidade());
    }

}
