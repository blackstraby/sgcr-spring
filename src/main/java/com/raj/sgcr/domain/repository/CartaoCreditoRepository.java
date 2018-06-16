package com.raj.sgcr.domain.repository;

import com.raj.sgcr.domain.model.CartaoCredito;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CartaoCreditoRepository extends CrudRepository<CartaoCredito,Long> {
}
