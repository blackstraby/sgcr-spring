package com.raj.sgcr.controller;

import com.raj.sgcr.domain.model.Atleta;
import com.raj.sgcr.domain.repository.AtletaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(value = "/atleta")
public class AtletaController {

    @Autowired
    private AtletaRepository corredorRepository;

    @GetMapping(value = "")
    public String corredores(Model model) {
        model.addAttribute("operacao", "listar");
        model.addAttribute("title", "Lista Atleta");
        model.addAttribute("botaoOperacao", "Listar Atleta");

        model.addAttribute("atletas", corredorRepository.findAll());
        return "atleta/pesquisar";
    }

    @GetMapping(value = "add")
    public String displayCorredorForm(Model model) {
        model.addAttribute("operacao", "adicionar");
        model.addAttribute("title", "Adicionar atleta");
        model.addAttribute("botaoOperacao", "Adicionar Atleta");

        return "atleta/manter";
    }

    @PostMapping(value = "add")
    public String processCorredorForm(@ModelAttribute Atleta corredor) {
        corredorRepository.save(corredor);
        return "redirect:/atleta";
    }

    @GetMapping(value = "edit/{id}") // site.com/atleta/edit
    public String corredorEdit(Model model, @PathVariable Long id) {
        Optional<Atleta> corredor = corredorRepository.findById(id);
        model.addAttribute("operacao", "editar");
        model.addAttribute("title", "Editar atleta");
        model.addAttribute("botaoOperacao", "Editar Atleta");

        if (corredor.isPresent()) {
            model.addAttribute("atleta", corredor.get());
        }
        return "atleta/manter";
    }

    @PostMapping(value = "edit/{id}") // site.com/atleta/edit/1/
    public String edit(@ModelAttribute Atleta corredor, Model model,
                       @PathVariable Long id) throws Exception {
        if (id.equals(corredor.getId())) {
            corredorRepository.save(corredor);
        } else {
            model.addAttribute("error", "Dados incorretos");
        }
        return "redirect:/atleta";
    }

    @GetMapping(value = "delete/{id}") // site.com/atleta/delete/1
    public String corredorDelete(Model model, @PathVariable Long id) {
        Optional<Atleta> corredor = corredorRepository.findById(id);
        model.addAttribute("operacao", "deletar");
        model.addAttribute("title", "Excluir atleta");
        model.addAttribute("botaoOperacao", "Excluir Atleta");
        if (corredor.isPresent()) {
            model.addAttribute("atleta", corredor.get());
        }
        return "atleta/manter";
    }

    @PostMapping(value = "delete/{id}")
    public String delete(@PathVariable Long id, @ModelAttribute Atleta corredor) {
        corredorRepository.delete(corredor);
        return "redirect:/atleta";
    }
}
