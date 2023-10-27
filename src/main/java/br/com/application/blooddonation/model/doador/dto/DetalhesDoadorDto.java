package br.com.application.blooddonation.model.doador.dto;

import br.com.application.blooddonation.model.contato.dto.DetalhesContatoDto;
import br.com.application.blooddonation.model.doador.Doador;
import br.com.application.blooddonation.model.endereco.dto.DetalhesEnderecoDto;
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
public final class DetalhesDoadorDto extends RepresentationModel<DetalhesDoadorDto> {
    private String id;
    private String nome;
    private String cpf;
    private String rg;
    private LocalDate dataDeNascimento;
    private BigDecimal altura;
    private BigDecimal peso;
    private TipoSanguineo tipoSanguineo;
    private DetalhesContatoDto contato;
    private DetalhesEnderecoDto endereco;

    public DetalhesDoadorDto(Doador doador) {
        this(doador.getId(), doador.getNome(), doador.getCpf(), doador.getRg(), doador.getDataDeNascimento(),
                doador.getAltura(), doador.getPeso(), doador.getTipoSanguineo(),
                new DetalhesContatoDto(doador.getContato()), new DetalhesEnderecoDto(doador.getEndereco()));
    }
}
