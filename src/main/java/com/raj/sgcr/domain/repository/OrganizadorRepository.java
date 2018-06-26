package com.raj.sgcr.domain.repository;


import com.raj.sgcr.domain.model.Corrida;
import com.raj.sgcr.domain.model.Organizador;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface OrganizadorRepository extends CrudRepository<Organizador, Long> {

    Optional<Corrida> findByEstado(String estado);

    @Query(value = "select distinct estado from organizador", nativeQuery = true)
    List<String> findDistinctByEstado();

    Optional<Organizador> findByEmailAndSenha(String email, String senha);

}
