package com.raj.sgcr.controller;

import com.raj.sgcr.domain.model.Organizador;
import com.raj.sgcr.domain.model.Usuario;
import com.raj.sgcr.domain.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Controller
@RequestMapping(value = "/")
public class HomeController {
    private CorridaRepository corridaRepository;
    private KitRepository kitRepository;
    private LoteRepository loteRepository;
    private PercursoRepository percursoRepository;
    private OrganizadorRepository organizadorRepository;
    private AtletaRepository atletaRepository;

    @Autowired
    public HomeController(CorridaRepository corridaRepository,
                          KitRepository kitRepository,
                          LoteRepository loteRepository,
                          PercursoRepository percursoRepository,
                          OrganizadorRepository organizadorRepository,
                          AtletaRepository atletaRepository) {
        this.corridaRepository = corridaRepository;
        this.kitRepository = kitRepository;
        this.loteRepository = loteRepository;
        this.percursoRepository = percursoRepository;
        this.organizadorRepository = organizadorRepository;
        this.atletaRepository = atletaRepository;
    }

    @GetMapping(value = "")
    public String corridas(Model model, HttpServletRequest request) {
        if (Usuario.isOrganizador(request)) {
            model.addAttribute("dataAtual", new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis())));
            model.addAttribute("title", "Dashboard");
            model.addAttribute("corridas", corridaRepository.findByOrganizador((Organizador) Usuario.getUsuario(request)));
            return "organizador/corridas";
        } else if (Usuario.isAdministrador(request)) {
            model.addAttribute("dataAtual", new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis())));
            model.addAttribute("title", "Dashboard");
            model.addAttribute("numCorridas", corridaRepository.count());
            model.addAttribute("numOrganizadores", organizadorRepository.count());
            model.addAttribute("numAtletas", organizadorRepository.count());
            return "administrador/home";
        }
        model.addAttribute("dataAtual", new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis())));
        model.addAttribute("title", "Dashboard");
        model.addAttribute("corridas", corridaRepository.findAll());
        model.addAttribute("numCorridas", corridaRepository.count());
        model.addAttribute("numKit", kitRepository.count());
        model.addAttribute("numLote", loteRepository.count());
        model.addAttribute("numPercurso", percursoRepository.count());
        return "home";
    }

}