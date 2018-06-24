package com.raj.sgcr.domain.repository;

import com.raj.sgcr.domain.model.Corrida;
import com.raj.sgcr.domain.model.Organizador;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CorridaRepository extends CrudRepository<Corrida, Long> {

    //@Query(value="Select c from Corrida c where c.organizador.id = :id",nativeQuery = true)
    Iterable<Corrida> findByOrganizador(Long id);
}
