package br.com.application.blooddonation.service;


import br.com.application.blooddonation.infra.exception.DoacaoException;
import br.com.application.blooddonation.model.doacao.Doacao;
import br.com.application.blooddonation.model.doacao.dto.CadastroDoacaoDto;
import br.com.application.blooddonation.model.doacao.dto.DetalhesDoacaoDto;
import br.com.application.blooddonation.model.doacao.dto.ListagemDoacaoDto;
import br.com.application.blooddonation.model.doador.Doador;
import br.com.application.blooddonation.repository.DoacaoRepository;
import br.com.application.blooddonation.service.doacao.validacoes.ValidacaoDoacao;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class DoacaoService {

    private final DoadorService doadorService;
    private final List<ValidacaoDoacao> validacoes;
    private final DoacaoRepository doacaoRepository;


    Doacao findDoacaoById(String id) {
        return doacaoRepository.findById(id).orElseThrow(() -> new DoacaoException("A doação requisitada não foi encontrada"));
    }

    public Page<ListagemDoacaoDto> listar(Pageable pageable) {
        return doacaoRepository.findAll(pageable).map(ListagemDoacaoDto::new);
    }

    public DetalhesDoacaoDto detalhar(String id) {
        return new DetalhesDoacaoDto(findDoacaoById(id));
    }

    public String cadastrar(CadastroDoacaoDto cadastroDoacao) {
        Doador doador = doadorService.findDoadorById(cadastroDoacao.doadorId());
        Doacao doacao = new Doacao(null, doador, cadastroDoacao.data(), cadastroDoacao.quantidade());
        validacoes.forEach(e -> e.validar(doador, doacao));
        doacaoRepository.save(doacao);
        return doacao.getId();
    }

}
