package br.com.application.blooddonation.model.doacao.dto;

import br.com.application.blooddonation.model.doacao.Doacao;
import br.com.application.blooddonation.model.tiposanguineo.TipoSanguineo;
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
public final class ListagemDoacaoDto extends RepresentationModel<ListagemDoacaoDto> {
    private String id;
    private TipoSanguineo tipoSanguineo;
    private LocalDate data;
    private BigDecimal quantidade;

    public ListagemDoacaoDto(Doacao doacao) {
        this(doacao.getId(), doacao.getDoador().getTipoSanguineo(), doacao.getData(), doacao.getQuantidade());
    }
}
