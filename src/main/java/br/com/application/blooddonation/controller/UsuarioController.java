package br.com.application.blooddonation.controller;

import br.com.application.blooddonation.model.usuario.dto.CadastroUsuarioDto;
import br.com.application.blooddonation.model.usuario.dto.DetalhesUsuarioDto;
import br.com.application.blooddonation.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuarios")
@AllArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesUsuarioDto> detalhar(@PathVariable("id") String id) {
        DetalhesUsuarioDto detalhes = usuarioService.detalhar(id);
        return ResponseEntity.status(HttpStatus.OK).body(detalhes);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastro(@RequestBody @Valid CadastroUsuarioDto cadastroUsuarioDto, UriComponentsBuilder builder) {
        String id = usuarioService.cadastrar(cadastroUsuarioDto);
        URI uri = builder.path("/usuarios/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(uri).body(id);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable("id") String id) {
        usuarioService.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
