package com.raj.sgcr.controller;

import com.raj.sgcr.domain.model.Usuario;
import com.raj.sgcr.domain.repository.AdministradorRepository;
import com.raj.sgcr.domain.repository.AtletaRepository;
import com.raj.sgcr.domain.repository.OrganizadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UsuarioController {

    @Autowired
    private AdministradorRepository administradorRepository;
    private OrganizadorRepository organizadorRepository;
    private AtletaRepository atletaRepository;

    @Autowired
    public UsuarioController(AdministradorRepository administradorRepository,
                             OrganizadorRepository organizadorRepository,
                             AtletaRepository atletaRepository) {
        this.administradorRepository = administradorRepository;
        this.organizadorRepository = organizadorRepository;
        this.atletaRepository = atletaRepository;
    }

    @GetMapping(value = "perfil")
    public String getPerfil(Model model, HttpServletRequest request) {
        model.addAttribute("operacao", "editar");
        model.addAttribute("botaoOperacao", "Editar");

        if (Usuario.isAdministrador(request)) {
            model.addAttribute("title", "Perfil de Administrador");
            model.addAttribute("administrador", Usuario.getUsuario(request));
            model.addAttribute("action", "/administrador/perfil");
            model.addAttribute("actionDelete", "/administrador/deletar");
            return "administrador/manter";
        } else if (Usuario.isOrganizador(request)) {
            model.addAttribute("title", "Perfil de Organizador");
            model.addAttribute("organizador", Usuario.getUsuario(request));
            model.addAttribute("action", "/organizador/perfil");
            model.addAttribute("actionDelete", "/organizador/deletar");
            return "organizador/manter";
        } else if (Usuario.isAtleta(request)) {
            model.addAttribute("title", "Perfil de Atleta");
            model.addAttribute("atleta", Usuario.getUsuario(request));
            model.addAttribute("action", "/atleta/perfil");
            model.addAttribute("actionDelete", "/atleta/deletar");
            return "atleta/manter";
        }
        return "redirect:/";
    }
}
