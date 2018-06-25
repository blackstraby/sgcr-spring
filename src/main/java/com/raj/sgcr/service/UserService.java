package com.raj.sgcr.service;

import com.raj.sgcr.domain.model.Usuario;

public interface UserService {
    public Usuario findUsuarioByEmail(String email);
    public void saveUsuario(Usuario user);
}