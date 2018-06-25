package com.raj.sgcr.controller;

import com.raj.sgcr.domain.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    public HomeController(CorridaRepository corridaRepository,
                          KitRepository kitRepository,
                          LoteRepository loteRepository,
                          PercursoRepository percursoRepository    ) {
        this.corridaRepository = corridaRepository;
        this.kitRepository = kitRepository;
        this.loteRepository = loteRepository;
        this.percursoRepository = percursoRepository;
    }

    @GetMapping(value = "")
    public String corridas(Model model) {
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