package com.raj.sgcr.domain.repository;

import com.raj.sgcr.domain.model.Administrador;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Component
@Repository
public interface AdministradorRepository extends CrudRepository<Administrador, Long> {
    Optional<Administrador> findByEmailAndSenha(String email, String senha);
}
