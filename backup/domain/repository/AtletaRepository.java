package com.raj.sgcr.domain.repository;

import com.raj.sgcr.domain.model.Atleta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface AtletaRepository  extends CrudRepository<Atleta, Long>{
}
