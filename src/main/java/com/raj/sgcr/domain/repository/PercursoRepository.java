package com.raj.sgcr.domain.repository;

import com.raj.sgcr.domain.model.Organizador;
import com.raj.sgcr.domain.model.Percurso;
import org.springframework.data.repository.CrudRepository;

public interface PercursoRepository extends CrudRepository<Percurso, Long> {
    Iterable<Percurso> findAllByOrganizador(Organizador organizador);
}
