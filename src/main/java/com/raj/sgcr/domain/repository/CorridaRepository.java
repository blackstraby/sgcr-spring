package com.raj.sgcr.domain.repository;

import com.raj.sgcr.domain.model.Corrida;
import com.raj.sgcr.domain.model.Organizador;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CorridaRepository extends CrudRepository<Corrida, Long> {
    Iterable<Corrida> findByOrganizador(Organizador organizador);

    Optional<Corrida> findByIdAndOrganizador(Long aLong, Organizador organizador);

    @Query(value = "select distinct estado from corrida", nativeQuery = true)
    List<String> findDistinctByEstado();


}
