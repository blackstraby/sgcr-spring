package com.raj.sgcr.controller;

import com.raj.sgcr.domain.model.Administrador;
import com.raj.sgcr.domain.model.Atleta;
import com.raj.sgcr.domain.model.Organizador;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
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

    @GetMapping(value = "/login")
    public String login(HttpServletRequest request) {
        return (Usuario.isLogado(request)) ? "redirect:/" : "/auth/login";
    }

    @PostMapping(value = "/login")
    public String efetuarLogin(@ModelAttribute Usuario usuario, Model model, HttpSession session) {
        if (administradorRepository.findByEmailAndSenha(usuario.getEmail(), usuario.getSenha()).isPresent()) {
            Optional<Administrador> administrador = administradorRepository.findByEmailAndSenha(usuario.getEmail(), usuario.getSenha());
            session.setAttribute("usuario", administrador.get());
            session.setAttribute("permissao", "ADMIN");
            model.addAttribute("loginSucesso", "Você está logado como ADMINISTRADOR");
        } else if (organizadorRepository.findByEmailAndSenha(usuario.getEmail(), usuario.getSenha()).isPresent()) {
            Optional<Organizador> organizador = organizadorRepository.findByEmailAndSenha(usuario.getEmail(), usuario.getSenha());
            session.setAttribute("usuario", organizador.get());
            session.setAttribute("permissao", "ORGANIZADOR");
            model.addAttribute("loginSucesso", "Você está logado como ORGANIZADOR");
        } else if (atletaRepository.findByEmailAndSenha(usuario.getEmail(), usuario.getSenha()).isPresent()) {
            Optional<Atleta> atleta = atletaRepository.findByEmailAndSenha(usuario.getEmail(), usuario.getSenha());
            session.setAttribute("usuario", atleta.get());
            session.setAttribute("permissao", "ATLETA");
            model.addAttribute("msgSucesso", "Você está logado como ATLETA");
        } else {
            model.addAttribute("msgErro", "Email ou senha inválido");
            return "auth/login";
        }
        return "redirect:/";
    }

    @GetMapping(value = "/logout")
    public String logout(@ModelAttribute Usuario usuario, Model model, HttpSession session) {
        session.removeAttribute("usuario");
        session.removeAttribute("permissao");
        return "redirect:/login";
    }

}