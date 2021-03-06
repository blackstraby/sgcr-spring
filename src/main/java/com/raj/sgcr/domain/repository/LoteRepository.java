package com.raj.sgcr.domain.repository;

import com.raj.sgcr.domain.model.Corrida;
import com.raj.sgcr.domain.model.Lote;
import org.springframework.data.repository.CrudRepository;

public interface LoteRepository extends CrudRepository<Lote, Long> {
    Iterable<Lote> findAllByCorrida(Corrida corrida);
}
