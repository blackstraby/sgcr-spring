package com.raj.sgcr.controller;

import com.raj.sgcr.domain.model.Percurso;
import com.raj.sgcr.domain.repository.OrganizadorRepository;
import com.raj.sgcr.domain.repository.PercursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(value = "/percurso")
public class PercursoController {
    private PercursoRepository percursoRepository;
    private OrganizadorRepository organizadorRepository;

    @Autowired
    public PercursoController(PercursoRepository percursoRepository,
                             OrganizadorRepository organizadorRepository) {
        this.percursoRepository = percursoRepository;
        this.organizadorRepository = organizadorRepository;
    }

    @GetMapping(value = "")
    public String percursos(Model model){
        model.addAttribute("operacao", "listar");
        model.addAttribute("title", "Lista de Percurso");
        model.addAttribute("percursos", percursoRepository.findAll());
        return "percurso/pesquisar";
    }

    @GetMapping(value = "add")
    public String getPercursoAdd(Model model){
        model.addAttribute("operacao", "adicionar");
        model.addAttribute("organizadores", organizadorRepository.findAll());
        model.addAttribute("title", "Adicionar Percurso");
        model.addAttribute("botaoOperacao", "Adicionar Percurso");

        return "percurso/manter";
    }

    @PostMapping(value = "add")
    public String postPercursoAdd(Model model, @ModelAttribute Percurso percurso){
        model.addAttribute("title", "Adicionar Percurso");

        percursoRepository.save(percurso);
        return "redirect:/percurso";
    }

    @GetMapping(value = "edit/{id}")
    public String getPercursoEdit(Model model, @PathVariable Long id) {
        model.addAttribute("operacao", "editar");
        model.addAttribute("title", "Editar Percurso");
        model.addAttribute("botaoOperacao", "Editar Percurso");
        model.addAttribute("organizadores", organizadorRepository.findAll());
        Optional<Percurso> percurso = percursoRepository.findById(id);
        if (percurso.isPresent()){
            model.addAttribute("percurso", percurso.get());
        }
        return "percurso/manter";
    }

    @PostMapping(value = "edit/{id}")
    public String postPercursoEdit(@ModelAttribute Percurso percurso, Model model,
                                  @PathVariable Long id) throws Exception {
        if (id.equals(percurso.getId())) {
            percursoRepository.save(percurso);
        } else {
            model.addAttribute("error", "Dados incorretos");
        }
        return "redirect:/percurso";
    }

    @GetMapping(value = "delete/{id}")
    public String getPercursoDelete(Model model, @PathVariable Long id) {
        model.addAttribute("operacao", "deletar");
        model.addAttribute("title", "Excluir Percurso");
        model.addAttribute("botaoOperacao", "Excluir Percurso");
        model.addAttribute("organizadores", organizadorRepository.findAll());
        Optional<Percurso> percurso = percursoRepository.findById(id);
        if (percurso.isPresent()) {
            model.addAttribute("percurso", percurso.get());
        }

        return "percurso/manter";
    }

    @PostMapping(value = "delete/{id}")
    public String postPercursoDelete(@PathVariable Long id, @ModelAttribute Percurso percurso) {
        percursoRepository.delete(percurso);
        return "redirect:/percurso";
    }

}
