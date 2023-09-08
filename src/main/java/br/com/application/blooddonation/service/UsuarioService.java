package br.com.application.blooddonation.service;

import br.com.application.blooddonation.infra.exception.DoacaoException;
import br.com.application.blooddonation.model.usuario.Usuario;
import br.com.application.blooddonation.model.usuario.dto.CadastroUsuarioDto;
import br.com.application.blooddonation.model.usuario.dto.DetalhesUsuarioDto;
import br.com.application.blooddonation.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsuarioService {

    private final PasswordEncoder encoder;
    private final UsuarioRepository usuarioRepository;

    Usuario getUsuarioById(String id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new DoacaoException("O usuário requisitado não foi encontrado"));
    }

    public DetalhesUsuarioDto detalhar(String id) {
        return new DetalhesUsuarioDto(getUsuarioById(id));
    }


    public String cadastrar(CadastroUsuarioDto dados) {
        Usuario usuario = new Usuario(null, dados.usuario(), encoder.encode(dados.senha()), Boolean.TRUE);
        usuarioRepository.save(usuario);
        return usuario.getId();
    }

    public void deletar(String id) {
        Usuario usuario = getUsuarioById(id);
        usuario.desabilitar();
    }
}
