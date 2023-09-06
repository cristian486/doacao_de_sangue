package br.com.application.blooddonation.service.doacao.validacoes;

import br.com.application.blooddonation.model.doacao.Doacao;
import br.com.application.blooddonation.model.doador.Doador;

public interface ValidacaoDoacao {
    public void validar(Doador doador, Doacao doacao);
}
