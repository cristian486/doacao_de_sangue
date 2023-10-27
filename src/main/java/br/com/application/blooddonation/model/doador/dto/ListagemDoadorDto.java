package br.com.application.blooddonation.model.doador.dto;

import br.com.application.blooddonation.model.doador.Doador;
import br.com.application.blooddonation.model.tiposanguineo.TipoSanguineo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public final class ListagemDoadorDto extends RepresentationModel<ListagemDoadorDto> {
    private String id;
    private String nome;
    private TipoSanguineo tipoSanguineo;

    public ListagemDoadorDto(Doador doador) {
        this(doador.getId(), doador.getNome(), doador.getTipoSanguineo());
    }
}
