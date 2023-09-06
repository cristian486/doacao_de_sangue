package br.com.application.blooddonation.controller;

import br.com.application.blooddonation.model.doador.dto.AtualizarDoadorDto;
import br.com.application.blooddonation.model.doador.dto.CadastroDoadorDto;
import br.com.application.blooddonation.model.doador.dto.DetalhesDoadorDto;
import br.com.application.blooddonation.model.doador.dto.ListagemDoadorDto;
import br.com.application.blooddonation.service.DoadorService;
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
@RequestMapping("/doadores")
@AllArgsConstructor
public class DoadorController {

    private final DoadorService doadorService;

    @GetMapping
    public ResponseEntity<Page<ListagemDoadorDto>> listar(@PageableDefault(sort = "id", size = 15) Pageable pageable) {
        Page<ListagemDoadorDto> listagem = doadorService.listar(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(listagem);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesDoadorDto> detalhar(@PathVariable("id") String id) {
        DetalhesDoadorDto detalhes = doadorService.detalhar(id);
        return ResponseEntity.status(HttpStatus.OK).body(detalhes);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> cadastrar(@RequestBody @Valid CadastroDoadorDto cadastroDoadorDto, UriComponentsBuilder componentsBuilder) {
        String id = doadorService.cadastrar(cadastroDoadorDto);
        URI uri = componentsBuilder.path("/doadores/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(uri).body(id);
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable("id") String id, @RequestBody @Valid AtualizarDoadorDto atualizarDoadorDto) {
        doadorService.atualizar(id, atualizarDoadorDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable("id") String id) {
        doadorService.excluir(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
