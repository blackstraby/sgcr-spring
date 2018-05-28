package com.raj.sgcr.controller;

import com.raj.sgcr.domain.model.Atleta;
import com.raj.sgcr.domain.repository.AtletaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(value = "atleta")
public class AtletaController {

    @Autowired
    private AtletaRepository corredorRepository;

    @GetMapping(value = "")
    public String corredores(Model model) {
        model.addAttribute("atletas", corredorRepository.findAll());
        return "atleta/atletas";
    }

    @GetMapping(value = "add")
    public String displayCorredorForm(Model model) {
        model.addAttribute("tittle", "Adicionar atleta");
        return "atleta/add";
    }

    @PostMapping(value = "add")
    public String processCorredorForm(@ModelAttribute Atleta corredor) {
        corredorRepository.save(corredor);
        return "redirect:";
    }

    @GetMapping(value = "edit/{id}") // site.com/corredor/edit
    public String corredorEdit(Model model, @PathVariable Long id) {
        Optional<Atleta> corredor = corredorRepository.findById(id);
        if (corredor.isPresent()){
            model.addAttribute("atleta", corredor.get());
        }
        model.addAttribute("title", "Editar atleta");
        return "atleta/edit";
    }

    @PostMapping(value = "edit/{id}") // site.com/corredor/edit/1/
    public String edit(@ModelAttribute Atleta corredor, Model model,
                       @PathVariable Long id) throws Exception {
        if (id.equals(corredor.getId())) {
            corredorRepository.save(corredor);
        } else {
            model.addAttribute("error", "Dados incorretos");
        }
        return "redirect:/atleta";
    }

    @GetMapping(value = "delete/{id}") // site.com/corredor/delete/1
    public String corredorDelete(Model model, @PathVariable Long id) {
        Optional<Atleta> corredor = corredorRepository.findById(id);
        if (corredor.isPresent()) {
            model.addAttribute("atleta", corredor.get());
        }
        model.addAttribute("tittle", "Excluir atleta");
        return "atleta/delete";
    }

    @PostMapping(value = "delete/{id}") // site.com/corredor/delete/1
    public String delete(@PathVariable Long id, @ModelAttribute Atleta corredor) {
        corredorRepository.delete(corredor);
        return "redirect:/atleta";
    }
}
