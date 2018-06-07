package com.raj.sgcr.controller;

import com.raj.sgcr.domain.model.Organizador;
import com.raj.sgcr.domain.repository.OrganizadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@Controller
@RequestMapping(value = "organizador")
public class OrganizadorController {
    @Autowired
    private OrganizadorRepository organizadorRepository;

    @GetMapping(value = "")
    public String organizadores(Model model) {
        model.addAttribute("organizadores", organizadorRepository.findAll());
        return "organizador/organizadores";
    }

    @GetMapping(value = "add")
    public String displayOrganizadorForm(Model model) {
        model.addAttribute("tittle", "Adicionar organizador");
        return "organizador/add";
    }

    @PostMapping(value = "add")
    public String processOrganizadorForm(@ModelAttribute Organizador organizador) {
        organizadorRepository.save(organizador);
        return "redirect:";
    }

    @GetMapping(value = "edit/{id}")
    public String organizadorEdit(Model model, @PathVariable Long id) {
        Optional<Organizador> organizador = organizadorRepository.findById(id);
        if (organizador.isPresent()){
            model.addAttribute("organizador", organizador.get());
        }
        model.addAttribute("title", "Editar organizador");
        return "organizador/edit";
    }

    @PostMapping(value = "edit/{id}") // site.com/corredor/edit/1/
    public String edit(@ModelAttribute Organizador organizador, Model model,
                       @PathVariable Long id) throws Exception {
        if (id.equals(organizador.getId())) {
            organizadorRepository.save(organizador);
        } else {
            model.addAttribute("error", "Dados incorretos");
        }
        return "redirect:/organizador";
    }

    @GetMapping(value = "delete/{id}") // site.com/corredor/delete/1
    public String organizadorDelete(Model model, @PathVariable Long id) {
        Optional<Organizador> organizador = organizadorRepository.findById(id);
        if (organizador.isPresent()) {
            model.addAttribute("organizador", organizador.get());
        }
        model.addAttribute("tittle", "Excluir organizador");
        return "organizador/delete";
    }

    @PostMapping(value = "delete/{id}") // site.com/corredor/delete/1
    public String delete(@PathVariable Long id, @ModelAttribute Organizador organizador) {
        organizadorRepository.delete(organizador);
        return "redirect:/organizador";
    }
}
