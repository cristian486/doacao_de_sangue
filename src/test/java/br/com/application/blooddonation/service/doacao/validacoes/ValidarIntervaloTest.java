package br.com.application.blooddonation.service.doacao.validacoes;

import br.com.application.blooddonation.infra.exception.DoacaoException;
import br.com.application.blooddonation.model.contato.Contato;
import br.com.application.blooddonation.model.doacao.Doacao;
import br.com.application.blooddonation.model.doador.Doador;
import br.com.application.blooddonation.model.doador.Sexo;
import br.com.application.blooddonation.model.endereco.Endereco;
import br.com.application.blooddonation.model.tiposanguineo.TipoSanguineo;
import br.com.application.blooddonation.repository.DoacaoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


class ValidarIntervaloTest {

    @Mock
    private DoacaoRepository doacaoRepository;
    private ValidarIntervalo validarIntervalo;
    private Doador doador;
    private Doacao doacao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.doador = getDoador(Sexo.MASCULINO);
        this.doacao = getDoacoes(0L).get(0);
        this.validarIntervalo = new ValidarIntervalo(doacaoRepository);
    }

    @ParameterizedTest
    @MethodSource("getSexos")
    @DisplayName("A doação de sangue deve ser permitida se for a primeira vez")
    void permitirDoacaoCasoSejaPrimeira(Sexo sexo) {
        this.doador = getDoador(sexo);
        Assertions.assertDoesNotThrow(() -> validarIntervalo.validar(doador, doacao));
    }

    @Test
    @DisplayName("A doação não deve ser permitida caso um homem tente doar antes do intervalo de dois meses")
    void impedirDoacaoParaSexoMasculinoComIntervaloMenorDeDoisMeses() {
        List<Doacao> doacoes = getDoacoes(0L, 1L);
        Mockito
        .when(doacaoRepository.findDoacaoByDoador_IdOrderByDataDesc(Mockito.any()))
        .thenReturn(doacoes);

        Assertions.assertThrows(DoacaoException.class, () -> validarIntervalo.validar(doador, doacao));
    }

    @Test
    @DisplayName("A doação deve ser permitida caso um homem tente doar no intervalo de exatos dois meses")
    void permitirDoacaoParaSexoMasculinoComIntervaloDeDoisMeses() {
        List<Doacao> doacoes = getDoacoes(2L);
        Mockito
        .when(doacaoRepository.findDoacaoByDoador_IdOrderByDataDesc(Mockito.any()))
        .thenReturn(doacoes);
        Assertions.assertDoesNotThrow(() -> validarIntervalo.validar(doador, doacao));
    }

    @Test
    @DisplayName("A doação não deve ser permitida caso uma mulher tente doar antes do intervalo de três meses")
    void impedirDoacaoParaSexoFeminoComIntervaloMenorDeTresMeses() {
        List<Doacao> doacoes = getDoacoes(0L, 1L, 2L);
        Mockito
                .when(doacaoRepository.findDoacaoByDoador_IdOrderByDataDesc(Mockito.any()))
                .thenReturn(doacoes);
        Assertions.assertThrows(DoacaoException.class, () -> validarIntervalo.validar(doador, doacao));
    }

    @Test
    @DisplayName("A doação deve ser permitida caso uma mulher tente doar após o intervalo de três meses")
    void permitirDoacaoParaSexoFeminoComIntervaloDeTresMeses() {
        List<Doacao> doacoes = getDoacoes(3L);
        Mockito
                .when(doacaoRepository.findDoacaoByDoador_IdOrderByDataDesc(Mockito.any()))
                .thenReturn(doacoes);
        Assertions.assertDoesNotThrow(() -> validarIntervalo.validar(doador, doacao));
    }



    private static List<Sexo> getSexos() {
        return List.of(Sexo.MASCULINO, Sexo.FEMININO);
    }

    private List<Doacao> getDoacoes(long... mesesSubtrair) {
        if(mesesSubtrair == null) return List.of();

        return Arrays.stream(mesesSubtrair)
                .mapToObj(valor -> new Doacao(UUID.randomUUID().toString(), doador, LocalDate.now().minusMonths(valor), new BigDecimal("1.5")))
                .collect(Collectors.toList());
    }

    private Doador getDoador(Sexo sexo) {
        return new Doador(null, "Nome Doador", "Cpf Doador", "Rg Doador", sexo, LocalDate.now(), new BigDecimal("1.80"), new BigDecimal("70"), Boolean.TRUE, TipoSanguineo.AB_POSITIVO, new Contato(), new Endereco());
    }
}