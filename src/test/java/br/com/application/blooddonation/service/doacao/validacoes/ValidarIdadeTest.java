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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
import java.util.stream.Stream;


class ValidarIdadeTest {


    private ValidarIdade validarIdade;
    private Doador doador;
    private Doacao doacao;

    @BeforeEach
    void setUp() {
        this.doador = new Doador(null, "Nome Doador", "Cpf Doador", "Rg Doador", Sexo.MASCULINO, LocalDate.now(), new BigDecimal("1.80"), new BigDecimal("70"), Boolean.TRUE, TipoSanguineo.AB_POSITIVO, new Contato(), new Endereco());
        this.doacao = new Doacao(null, doador, LocalDate.now(), new BigDecimal("1.0"));
        this.validarIdade = new ValidarIdade();
    }


    @ParameterizedTest
    @MethodSource("datasDeNascimento")
    @DisplayName("Não deve ser permitida a doação de sangue de pessoas que tenham menos de dezesseis ou mais de sessenta e nova anos")
    void impedirDoacaoDePessoasComMenosDeDezesseisOuMaisDeSessentaNoveAnos(LocalDate nascimento) {
        AtualizarDoadorDto atualizarDoadorDto = this.dadosAtualizar(nascimento);
        this.doador.atualizar(atualizarDoadorDto);
        Assertions.assertThrowsExactly(DoacaoException.class, () -> validarIdade.validar(doador, doacao));
    }

    @Test
    @DisplayName("Deve ser permitida a doação de sangue de pessoas que tenham entre dezesseis ou sessenta e nova anos")
    void permitirDoacaoDePessoasEntreDezesseisOuSessentaNoveAnos() {
        AtualizarDoadorDto atualizarDoadorDto = this.dadosAtualizar(LocalDate.now().minusYears(25));
        this.doador.atualizar(atualizarDoadorDto);
        Assertions.assertDoesNotThrow(() -> validarIdade.validar(doador, doacao));
    }


    private static Stream<Arguments> datasDeNascimento() {
        return Stream.of(
                Arguments.of(LocalDate.now().minusYears(70)),
                Arguments.of(LocalDate.now().minusYears(14))
        );
    }

    private AtualizarDoadorDto dadosAtualizar(LocalDate dataDeNascimento) {
        return new AtualizarDoadorDto(null, null, null, null, dataDeNascimento, null, null, null, null, null);
    }

}