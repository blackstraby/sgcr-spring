package com.raj.sgcr.service;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.raj.sgcr.domain.model.Regra;

import com.raj.sgcr.domain.model.Usuario;
import com.raj.sgcr.domain.repository.RegraRepository;
import com.raj.sgcr.domain.repository.UsuarioRepository;

@Service("userService")
public class UserServiceImpl implements UserService{

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Qualifier("regraRepository")
    @Autowired
    private RegraRepository regraRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Usuario findUsuarioByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    @Override
    public void saveUsuario(Usuario usuario) {
        usuario.setSenha(bCryptPasswordEncoder.encode(usuario.getSenha()));
        Regra usuarioRegra = regraRepository.findByRegra("ADMIN");
        usuario.setRegras(new HashSet<Regra>(Arrays.asList(usuarioRegra)));
        usuarioRepository.save(usuario);
    }

}