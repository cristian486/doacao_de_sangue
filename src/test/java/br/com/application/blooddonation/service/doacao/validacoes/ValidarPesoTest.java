package br.com.application.blooddonation.service.doacao.validacoes;

import br.com.application.blooddonation.infra.exception.DoacaoException;
import br.com.application.blooddonation.model.contato.Contato;
import br.com.application.blooddonation.model.doacao.Doacao;
import br.com.application.blooddonation.model.doador.Doador;
import br.com.application.blooddonation.model.doador.Sexo;
import br.com.application.blooddonation.model.doador.dto.AtualizarDoadorDto;
import br.com.application.blooddonation.model.endereco.Endereco;
import br.com.application.blooddonation.model.tiposanguineo.TipoSanguineo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

class ValidarPesoTest {

    private ValidarPeso validarPeso;
    private Doador doador;
    private Doacao doacao;

    @BeforeEach
    void setUp() {
        this.doador = new Doador(null, "Nome Doador", "Cpf Doador", "Rg Doador", Sexo.MASCULINO, LocalDate.now(), new BigDecimal("1.80"), new BigDecimal("70"), Boolean.TRUE, TipoSanguineo.AB_POSITIVO, new Contato(), new Endereco());
        this.doacao = new Doacao(null, doador, LocalDate.now(), new BigDecimal("1.0"));
        this.validarPeso = new ValidarPeso();
    }

    @Test
    @DisplayName("Não deve ser permitido a doação de sangue de pessoas abaixo de cinquenta quilos")
    void impedirDoacaoDePessoasAbaixoDeCinquentaQuilos() {
        AtualizarDoadorDto atualizarDoadorDto = this.dadosAtualizar(new BigDecimal("45.5"));
        this.doador.atualizar(atualizarDoadorDto);
        Assertions.assertThrowsExactly(DoacaoException.class, () -> validarPeso.validar(doador, doacao));
    }

    @Test
    @DisplayName("Permitir a doação de sangue de pessoas com cinquenta quilos")
    void permitirDoacaoDePessoasComDeCinquentaQuilos() {
        AtualizarDoadorDto atualizarDoadorDto = this.dadosAtualizar(new BigDecimal("50.0"));
        this.doador.atualizar(atualizarDoadorDto);
        Assertions.assertDoesNotThrow( () -> validarPeso.validar(doador, doacao));
    }

    @Test
    @DisplayName("Permitir a doação de sangue de pessoas acima de cinquenta quilos")
    void permitirDoacaoDePessoasAcimaDeCinquentaQuilos() {
        AtualizarDoadorDto atualizarDoadorDto = this.dadosAtualizar(new BigDecimal("75.0"));
        this.doador.atualizar(atualizarDoadorDto);
        Assertions.assertDoesNotThrow(() -> validarPeso.validar(doador, doacao));
    }



    private AtualizarDoadorDto dadosAtualizar(BigDecimal peso) {
        return new AtualizarDoadorDto(null, null, null, null, LocalDate.now(), null, peso, null, null, null);
    }
}