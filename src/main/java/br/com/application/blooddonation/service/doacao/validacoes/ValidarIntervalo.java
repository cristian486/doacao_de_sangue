package br.com.application.blooddonation.service.doacao.validacoes;

import br.com.application.blooddonation.model.doacao.Doacao;
import br.com.application.blooddonation.model.doador.Doador;
import br.com.application.blooddonation.repository.DoacaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@AllArgsConstructor
public class ValidarIntervalo implements ValidacaoDoacao {

    private final DoacaoRepository doacaoRepository;

    @Override
    public void validar(Doador doador, Doacao doacao) {
        doacaoRepository
        .findDoacaoByDoador_IdOrderByDataDesc(doador.getId())
        .stream()
        .findFirst()
        .ifPresent(d -> {
            LocalDate dataDaDoacao = d.getData();
            int mesesDesdeUltimaDoacao = doacao.getData().getMonthValue() - dataDaDoacao.getMonthValue();
            doador.getSexo().verificarIntervalo(mesesDesdeUltimaDoacao);
        });
    }
}
