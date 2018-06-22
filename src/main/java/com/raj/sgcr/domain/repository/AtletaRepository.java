package com.raj.sgcr.domain.repository;

import com.raj.sgcr.domain.model.Atleta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Component
@Repository
public interface AtletaRepository  extends CrudRepository<Atleta, Long>{
    Iterable<Atleta> findByNomeContaining(String nome);

    @Override
    Iterable<Atleta> findAll();
}
