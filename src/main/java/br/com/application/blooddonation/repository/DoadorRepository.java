package br.com.application.blooddonation.repository;

import br.com.application.blooddonation.model.doador.Doador;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoadorRepository extends MongoRepository<Doador, String> {

    Page<Doador> findAllByHabilitadoTrue(Pageable pageable);
}
