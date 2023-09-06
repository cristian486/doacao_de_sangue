package br.com.application.blooddonation.controller;

import br.com.application.blooddonation.model.doacao.dto.CadastroDoacaoDto;
import br.com.application.blooddonation.model.doacao.dto.DetalhesDoacaoDto;
import br.com.application.blooddonation.model.doacao.dto.ListagemDoacaoDto;
import br.com.application.blooddonation.service.DoacaoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/doacoes")
@AllArgsConstructor
public class DoacaoController {

    private final DoacaoService doacaoService;

    @GetMapping
    public ResponseEntity<Page<ListagemDoacaoDto>> listagem(@PageableDefault(size = 15) Pageable pageable) {
        Page<ListagemDoacaoDto> listagem = doacaoService.listar(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(listagem);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesDoacaoDto> detalhar(@PathVariable("id") String id) {
        DetalhesDoacaoDto detalhes = doacaoService.detalhar(id);
        return ResponseEntity.status(HttpStatus.OK).body(detalhes);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> cadastrar(@RequestBody @Valid CadastroDoacaoDto cadastroDoacaoDto, UriComponentsBuilder componentsBuilder) {
        String id = doacaoService.cadastrar(cadastroDoacaoDto);
        URI uri = componentsBuilder.path("/doacoes/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(uri).body(id);
    }

}
