package com.raj.sgcr.controller;

import com.raj.sgcr.domain.model.Administrador;
import com.raj.sgcr.domain.model.Usuario;
import com.raj.sgcr.domain.repository.AdministradorRepository;
import com.raj.sgcr.domain.repository.AtletaRepository;
import com.raj.sgcr.domain.repository.OrganizadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/login")
public class LoginController {
    private AdministradorRepository administradorRepository;
    private OrganizadorRepository organizadorRepository;
    private AtletaRepository atletaRepository;

    @Autowired
    public LoginController(AdministradorRepository administradorRepository, OrganizadorRepository organizadorRepository, AtletaRepository atletaRepository) {
        this.administradorRepository = administradorRepository;
        this.organizadorRepository = organizadorRepository;
        this.atletaRepository = atletaRepository;
    }

    @GetMapping(value = "")
    public String login() {
        return "/auth/login";
    }

    @PostMapping(value = "")
    public String efetuarLogin(@ModelAttribute Usuario usuario, Model model) {
        if (administradorRepository.findByEmailAndSenha(usuario.getEmail(), usuario.getSenha()).isPresent()) {
            System.out.println("LOGADO COMO ADMINISTRADOR!!!");
        } else if (organizadorRepository.findByEmailAndSenha(usuario.getEmail(), usuario.getSenha()).isPresent()) {
            System.out.println("LOGADO COMO ORGANIZADOR!!!");
        } else if (atletaRepository.findByEmailAndSenha(usuario.getEmail(), usuario.getSenha()).isPresent()) {
            System.out.println("LOGADO COMO ATLETA!!");
        } else {
            System.out.println("USUÁRIO NÃO ENCONTRADO!!!");
        }
        return "/auth/login";
    }

}