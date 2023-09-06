package br.com.application.blooddonation.service.doacao.validacoes;

import br.com.application.blooddonation.infra.exception.DoacaoException;
import br.com.application.blooddonation.model.doacao.Doacao;
import br.com.application.blooddonation.model.doador.Doador;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ValidarPeso implements ValidacaoDoacao {
    @Override
    public void validar(Doador doador, Doacao doacao) {
        BigDecimal pesoDoDoador = doador.getPeso();

        if (pesoDoDoador.compareTo(new BigDecimal("50.0")) < 0)
            throw new DoacaoException("O doador deve pesar mais de cinquenta quilos");
    }
}
