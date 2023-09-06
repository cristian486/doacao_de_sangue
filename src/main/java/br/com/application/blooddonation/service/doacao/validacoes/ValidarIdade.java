package br.com.application.blooddonation.service.doacao.validacoes;

import br.com.application.blooddonation.infra.exception.DoacaoException;
import br.com.application.blooddonation.model.doacao.Doacao;
import br.com.application.blooddonation.model.doador.Doador;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ValidarIdade implements ValidacaoDoacao {
    @Override
    public void validar(Doador doador, Doacao doacao) {
        LocalDate nascimentoDoador = doador.getDataDeNascimento();
        LocalDate dataAtual = LocalDate.now();
        int idadeDoDoador = dataAtual.getYear() - nascimentoDoador.getYear();

        if(idadeDoDoador < 16 || idadeDoDoador > 69)
            throw new DoacaoException("A idade do doador deve ser entre dezesseis e sessenta e nove anos");
    }
}
