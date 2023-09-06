package br.com.application.blooddonation.model.doador;

import br.com.application.blooddonation.model.contato.Contato;
import br.com.application.blooddonation.model.doador.dto.AtualizarDoadorDto;
import br.com.application.blooddonation.model.endereco.Endereco;
import br.com.application.blooddonation.model.tiposanguineo.TipoSanguineo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Document
@AllArgsConstructor
@NoArgsConstructor
public class Doador {

    @Id
    private String id;
    private String nome;
    private String cpf;
    private String rg;
    private Sexo sexo;
    private LocalDate dataDeNascimento;
    private BigDecimal altura;
    private BigDecimal peso;
    private Boolean habilitado = Boolean.TRUE;
    private TipoSanguineo tipoSanguineo;
    private Contato contato;
    private Endereco endereco;

    public void desabilitarCadastro() {
        this.habilitado = Boolean.FALSE;
    }

    public void atualizar(AtualizarDoadorDto atualizarDoadorDto) {
        if (atualizarDoadorDto.nome() != null && !atualizarDoadorDto.nome().isEmpty())
            this.nome = atualizarDoadorDto.nome();

        if (atualizarDoadorDto.cpf() != null && !atualizarDoadorDto.cpf().isEmpty())
            this.cpf = atualizarDoadorDto.cpf();

        if (atualizarDoadorDto.rg() != null && !atualizarDoadorDto.rg().isEmpty())
            this.rg = atualizarDoadorDto.rg();

        if (atualizarDoadorDto.sexo() != null && !this.sexo.equals(atualizarDoadorDto.sexo()))
            this.sexo = atualizarDoadorDto.sexo();

        if (atualizarDoadorDto.dataDeNascimento() != null && !atualizarDoadorDto.dataDeNascimento().equals(this.dataDeNascimento))
            this.dataDeNascimento = atualizarDoadorDto.dataDeNascimento();

        if (atualizarDoadorDto.altura() != null && !atualizarDoadorDto.altura().equals(this.altura))
            this.altura = atualizarDoadorDto.altura();

        if (atualizarDoadorDto.peso() != null && !atualizarDoadorDto.peso().equals(this.peso))
            this.peso = atualizarDoadorDto.peso();

        if (atualizarDoadorDto.tipoSanguineo() != null && !atualizarDoadorDto.tipoSanguineo().equals(this.tipoSanguineo))
            this.tipoSanguineo = atualizarDoadorDto.tipoSanguineo();

        if (atualizarDoadorDto.contato() != null)
            this.contato.atualizar(atualizarDoadorDto.contato());

        if (atualizarDoadorDto.endereco() != null)
            this.endereco.atualizar(atualizarDoadorDto.endereco());
    }
}
