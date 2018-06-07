package com.raj.sgcr.domain.repository;

import com.raj.sgcr.domain.model.Corrida;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorridaRepository extends CrudRepository<Corrida, Long> {
}
