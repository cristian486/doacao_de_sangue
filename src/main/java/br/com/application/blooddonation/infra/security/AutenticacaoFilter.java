package br.com.application.blooddonation.infra.security;

import br.com.application.blooddonation.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AutenticacaoFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final UsuarioRepository repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        extrairToken(request)
                .ifPresent(t -> {
                    String usuario = jwtUtils.verificarToken(t);
                    UserDetails entidade = repository.findUsuarioByUsuarioAndHabilitadoTrue(usuario);
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(entidade, null, entidade.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                });
        filterChain.doFilter(request, response);
    }

    private Optional<String> extrairToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");

        if (authorization == null)
            return Optional.empty();

        return Optional.of(authorization.replace("Bearer ", ""));
    }
}
