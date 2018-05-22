package com.raj.sgcr.domain.repository;

import com.raj.sgcr.domain.model.Ranking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface RankingRepository extends CrudRepository<Ranking, Long> {
}
