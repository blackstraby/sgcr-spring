package com.raj.sgcr.controller;

import com.raj.sgcr.domain.model.Corrida;
import com.raj.sgcr.domain.model.Organizador;
import com.raj.sgcr.domain.repository.CorridaRepository;
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
    @Autowired
    private CorridaRepository corridaRepository;

    @GetMapping(value = "")
    public String organizador(Model model) {
        model.addAttribute("operacao", "listar");
        model.addAttribute("title", "Lista Organizadores");
        model.addAttribute("botaoOperacao", "Listar Organizadores");

        model.addAttribute("organizadores", organizadorRepository.findAll());
        return "organizador/pesquisar";
    }

    @GetMapping(value = "add")
    public String displayOrganizadorForm(Model model) {
        model.addAttribute("operacao", "adicionar");
        model.addAttribute("title", "Adicionar organizador");
        model.addAttribute("botaoOperacao", "Adicionar Organizador");

        return "organizador/manter";
    }

    @PostMapping(value = "add")
    public String processOrganizadorForm(@ModelAttribute Organizador organizador) {
        organizadorRepository.save(organizador);
        return "redirect:/organizador";
    }

    @GetMapping(value = "edit/{id}") // site.com/organizador/edit
    public String organizadorEdit(Model model, @PathVariable Long id) {
        Optional<Organizador> organizador = organizadorRepository.findById(id);
        model.addAttribute("operacao", "editar");
        model.addAttribute("title", "Editar organizador");
        model.addAttribute("botaoOperacao", "Editar Organizador");

        if (organizador.isPresent()) {
            model.addAttribute("organizador", organizador.get());
        }
        return "organizador/manter";
    }

    @PostMapping(value = "edit/{id}") // site.com/organizador/edit/1/
    public String edit(@ModelAttribute Organizador organizador, Model model,
                       @PathVariable Long id) throws Exception {
        if (id.equals(organizador.getId())) {
            organizadorRepository.save(organizador);
        } else {
            model.addAttribute("error", "Dados incorretos");
        }
        return "redirect:/organizador";
    }

    @GetMapping(value = "delete/{id}") // site.com/organizador/delete/1
    public String organizadorDelete(Model model, @PathVariable Long id) {
        Optional<Organizador> organizador = organizadorRepository.findById(id);
        model.addAttribute("operacao", "deletar");
        model.addAttribute("title", "Excluir organizador");
        model.addAttribute("botaoOperacao", "Excluir Organizador");
        if (organizador.isPresent()) {
            model.addAttribute("organizador", organizador.get());
        }
        return "organizador/manter";
    }

    @PostMapping(value = "delete/{id}")
    public String delete(@PathVariable Long id, @ModelAttribute Organizador organizador) {
        organizadorRepository.delete(organizador);
        return "redirect:/organizador";
    }
    @GetMapping(value = "busca")
    public String busca(Model model) {
        model.addAttribute("operacao", "busca");
        model.addAttribute("title", "Busca");
        model.addAttribute("botaoOperacao", "Buscar");

        model.addAttribute("organizadores", organizadorRepository.findAll());
        model.addAttribute("corridas", corridaRepository.findAll());
        return "organizador/buscaCorridaOrganizador";
    }
    @RequestMapping(value = "/busca")
    public String organizadorBusca(Model model,@RequestParam("organizador") Long id) {
        model.addAttribute("operacao", "busca");
        model.addAttribute("title", "Busca");
        model.addAttribute("botaoOperacao", "Buscar");

        model.addAttribute("organizadores", organizadorRepository.findAll());
        model.addAttribute("corridas", corridaRepository.findByOrganizador(id));
        return "organizador/buscaCorridaOrganizador";
    }
}
