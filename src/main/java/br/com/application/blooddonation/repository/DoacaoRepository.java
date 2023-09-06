package br.com.application.blooddonation.repository;

import br.com.application.blooddonation.model.doacao.Doacao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DoacaoRepository extends MongoRepository<Doacao, String> {

    List<Doacao> findDoacaoByDoador_IdOrderByDataDesc(String id);
}
