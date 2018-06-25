package com.raj.sgcr.domain.repository;

import com.raj.sgcr.domain.model.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
    Usuario findByEmail(String email);
}
