package br.com.application.blooddonation.infra.security;

import br.com.application.blooddonation.model.usuario.dto.TokenUsuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Component
public class JwtUtils {

    @Value("${spring.security.jwt.secret}")
    private String secret;

    public TokenUsuario gerarTokenJWT(String usuario) {
        String tokenJwt = JWT
                .create()
                .withIssuer("Blood Donation")
                .withExpiresAt(getExpirationDate())
                .withSubject(usuario)
                .sign(getAlgorithm());
        return new TokenUsuario(tokenJwt);
    }

    public String verificarToken(String tokenJwt) {
        return JWT.require(getAlgorithm())
                .withIssuer("Blood Donation")
                .build()
                .verify(tokenJwt)
                .getSubject();
    }

    private Algorithm getAlgorithm() {
        return Algorithm.HMAC256(secret);
    }

    private Instant getExpirationDate() {
        return Instant.now().plus(3L, ChronoUnit.HOURS);
    }

}
