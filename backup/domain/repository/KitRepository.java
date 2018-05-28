package com.raj.sgcr.domain.repository;

import com.raj.sgcr.domain.model.Kit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface KitRepository extends CrudRepository<Kit, Long> {
}
