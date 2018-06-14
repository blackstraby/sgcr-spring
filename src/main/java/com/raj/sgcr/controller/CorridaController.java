package com.raj.sgcr.controller;

import com.raj.sgcr.domain.model.Corrida;
import com.raj.sgcr.domain.repository.CorridaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(value = "corridas")
public class CorridaController {

    @Autowired
    private CorridaRepository corridaRepository;

    @GetMapping(value = "")
    public String corridas(Model model){
        model.addAttribute("operacao", "listar");
        model.addAttribute("title", "Lista de corridas");
        model.addAttribute("corridas", corridaRepository.findAll());
        return "corrida/corridas";
    }

    @GetMapping(value = "add")
    public String getCorridasAdd(Model model){
        model.addAttribute("operacao", "adicionar");
        model.addAttribute("title", "Adicionar corrida");
        return "corrida/add";
    }

    @PostMapping(value = "add")
    public String postCorridasAdd(Model model, @ModelAttribute Corrida corrida){
        model.addAttribute("title", "Adicionar corrida");
        corridaRepository.save(corrida);
        return "redirect:";
    }

    @GetMapping(value = "edit/{id}")
    public String getCorridaEdit(Model model, @PathVariable Long id) {
        model.addAttribute("operacao", "editar");
        model.addAttribute("title", "Editar corrida");
        Optional<Corrida> corrida = corridaRepository.findById(id);
        if (corrida.isPresent()){
            model.addAttribute("corrida", corrida.get());
        }
        return "corrida/edit";
    }

    @PostMapping(value = "edit/{id}")
    public String postCorridaEdit(@ModelAttribute Corrida corrida, Model model,
                                  @PathVariable Long id) throws Exception {
        if (id.equals(corrida.getId())) {
            corridaRepository.save(corrida);
        } else {
            model.addAttribute("error", "Dados incorretos");
        }
        return "redirect:/corridas";
    }

    @GetMapping(value = "delete/{id}")
    public String getCorridaDelete(Model model, @PathVariable Long id) {
        model.addAttribute("operacao", "deletar");
        model.addAttribute("title", "Excluir corrida");
        Optional<Corrida> corrida = corridaRepository.findById(id);
        if (corrida.isPresent()) {
            model.addAttribute("corrida", corrida.get());
        }

        return "corrida/delete";
    }

    @PostMapping(value = "delete/{id}")
    public String postCorridaDelete(@PathVariable Long id, @ModelAttribute Corrida corrida) {
        corridaRepository.delete(corrida);
        return "redirect:/corridas";
    }

}
