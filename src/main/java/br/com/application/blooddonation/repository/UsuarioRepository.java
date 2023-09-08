package br.com.application.blooddonation.repository;

import br.com.application.blooddonation.model.usuario.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String> {

    UserDetails findUsuarioByUsuarioAndHabilitadoTrue(String username);
}
