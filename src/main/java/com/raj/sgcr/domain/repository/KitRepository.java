package com.raj.sgcr.domain.repository;

import com.raj.sgcr.domain.model.Kit;
import com.raj.sgcr.domain.model.Organizador;
import org.springframework.data.repository.CrudRepository;

public interface KitRepository extends CrudRepository<Kit, Long> {
    Iterable<Kit> findAllByOrganizador(Organizador organizador);
}
