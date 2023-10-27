package br.com.application.blooddonation.service;

import br.com.application.blooddonation.controller.DoadorController;
import br.com.application.blooddonation.infra.exception.DoacaoException;
import br.com.application.blooddonation.model.doador.Doador;
import br.com.application.blooddonation.model.doador.dto.AtualizarDoadorDto;
import br.com.application.blooddonation.model.doador.dto.CadastroDoadorDto;
import br.com.application.blooddonation.model.doador.dto.DetalhesDoadorDto;
import br.com.application.blooddonation.model.doador.dto.ListagemDoadorDto;
import br.com.application.blooddonation.repository.DoadorRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class DoadorService {

    private final DoadorRepository doadorRepository;


    Doador findDoadorById(String id) {
        return doadorRepository.findById(id).orElseThrow(() -> new DoacaoException("O doador requisitado não foi encontrado"));
    }

    public Page<ListagemDoadorDto> listar(Pageable pageable) {
        Page<ListagemDoadorDto> listagem = doadorRepository.findAllByHabilitadoTrue(pageable).map(ListagemDoadorDto::new);
        listagem.forEach(d -> d.add(WebMvcLinkBuilder.linkTo(DoadorController.class).slash(d.getId()).withSelfRel()));
        return listagem;
    }

    public DetalhesDoadorDto detalhar(String id) {
        DetalhesDoadorDto detalhes = new DetalhesDoadorDto(this.findDoadorById(id));
        detalhes.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DoadorController.class).listar(Pageable.ofSize(15))).withRel("doadores"));
        return detalhes;
    }

    public String cadastrar(CadastroDoadorDto cadastroDoadorDto) {
        Doador doador = cadastroDoadorDto.toDomain();
        doadorRepository.save(doador);
        return doador.getId();
    }

    public void atualizar(String id, AtualizarDoadorDto atualizarDoadorDto) {
        Doador doador = this.findDoadorById(id);
        doador.atualizar(atualizarDoadorDto);
    }

    public void excluir(String id) {
        Doador doador = this.findDoadorById(id);
        doador.desabilitarCadastro();
    }
}
