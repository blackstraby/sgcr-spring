package com.raj.sgcr.domain.repository;

import com.raj.sgcr.domain.model.Regra;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository("regraRepository")
public interface RegraRepository extends CrudRepository<Regra, Integer>{
    Regra findByRegra(String regra);
}