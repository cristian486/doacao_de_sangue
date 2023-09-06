package br.com.application.blooddonation.service;

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
        return doadorRepository.findAllByHabilitadoTrue(pageable).map(ListagemDoadorDto::new);
    }

    public DetalhesDoadorDto detalhar(String id) {
        return new DetalhesDoadorDto(this.findDoadorById(id));
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
