package com.raj.sgcr.controller;

import com.raj.sgcr.domain.model.Corrida;
import com.raj.sgcr.domain.repository.CorridaRepository;
import com.raj.sgcr.domain.repository.OrganizadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(value = "/corrida")
public class CorridaController {
    private CorridaRepository corridaRepository;
    private OrganizadorRepository organizadorRepository;

    @Autowired
    public CorridaController(CorridaRepository corridaRepository,
                             OrganizadorRepository organizadorRepository) {
        this.corridaRepository = corridaRepository;
        this.organizadorRepository = organizadorRepository;
    }

    @GetMapping(value = "")
    public String corridas(Model model){
        model.addAttribute("operacao", "listar");
        model.addAttribute("title", "Lista de corridas");
        model.addAttribute("corridas", corridaRepository.findAll());
        model.addAttribute("botaoOperacao", "Listar Corridas");
        return "corrida/pesquisar";
    }

    @GetMapping(value = "add")
    public String getCorridasAdd(Model model){
        model.addAttribute("operacao", "adicionar");
        model.addAttribute("organizadores", organizadorRepository.findAll());
        model.addAttribute("title", "Adicionar corrida");
        model.addAttribute("botaoOperacao", "Adicionar Corrida");
        return "corrida/manter";
    }

    @PostMapping(value = "add")
    public String postCorridasAdd(Model model, @ModelAttribute Corrida corrida){
        model.addAttribute("title", "Adicionar corrida");
        corridaRepository.save(corrida);
        return "redirect:/corrida";
    }

    @GetMapping(value = "edit/{id}")
    public String getCorridaEdit(Model model, @PathVariable Long id) {
        model.addAttribute("organizadores", organizadorRepository.findAll());
        model.addAttribute("operacao", "editar");
        model.addAttribute("botaoOperacao", "Editar Corrida");
        model.addAttribute("title", "Editar Corrida");
        Optional<Corrida> corrida = corridaRepository.findById(id);
        if (corrida.isPresent()){
            model.addAttribute("corrida", corrida.get());
        }
        return "corrida/manter";
    }

    @PostMapping(value = "edit/{id}")
    public String postCorridaEdit(@ModelAttribute Corrida corrida, Model model,
                                  @PathVariable Long id) throws Exception {
        if (id.equals(corrida.getId())) {
            corridaRepository.save(corrida);
        } else {
            model.addAttribute("error", "Dados incorretos");
        }
        return "redirect:/corrida";
    }

    @GetMapping(value = "delete/{id}")
    public String getCorridaDelete(Model model, @PathVariable Long id) {
        model.addAttribute("operacao", "deletar");
        model.addAttribute("title", "Excluir Corrida");
        model.addAttribute("botaoOperacao", "Excluir Corrida");
        model.addAttribute("organizadores", organizadorRepository.findAll());
        Optional<Corrida> corrida = corridaRepository.findById(id);
        if (corrida.isPresent()) {
            model.addAttribute("corrida", corrida.get());
        }

        return "corrida/manter";
    }

    @PostMapping(value = "delete/{id}")
    public String postCorridaDelete(@PathVariable Long id, @ModelAttribute Corrida corrida) {
        corridaRepository.delete(corrida);
        return "redirect:/corrida";
    }

}