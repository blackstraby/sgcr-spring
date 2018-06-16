package com.raj.sgcr.domain.repository;

import com.raj.sgcr.domain.model.Inscricao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InscricaoRepository extends CrudRepository<Inscricao, Long> {
}
