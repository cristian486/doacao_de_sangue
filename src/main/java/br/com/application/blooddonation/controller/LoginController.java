package br.com.application.blooddonation.controller;

import br.com.application.blooddonation.infra.security.JwtUtils;
import br.com.application.blooddonation.model.usuario.Usuario;
import br.com.application.blooddonation.model.usuario.dto.LoginUsuarioDto;
import br.com.application.blooddonation.model.usuario.dto.TokenUsuario;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@AllArgsConstructor
public class LoginController {

    private final JwtUtils jwtUtils;
    private final AuthenticationManager manager;

    @PostMapping
    public ResponseEntity<TokenUsuario> login(@RequestBody @Valid LoginUsuarioDto loginUsuarioDto) {
        UsernamePasswordAuthenticationToken authenticationCredentials = new UsernamePasswordAuthenticationToken(loginUsuarioDto.usuario(), loginUsuarioDto.senha());
        Authentication authenticate = manager.authenticate(authenticationCredentials);
        String usuario = ((Usuario) authenticate.getPrincipal()).getUsuario();
        TokenUsuario tokenUsuario = jwtUtils.gerarTokenJWT(usuario);
        return ResponseEntity.status(HttpStatus.OK).body(tokenUsuario);
    }
}
