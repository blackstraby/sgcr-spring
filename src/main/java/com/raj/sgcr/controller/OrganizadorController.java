package com.raj.sgcr.controller;

import com.raj.sgcr.domain.model.Organizador;
import com.raj.sgcr.domain.model.Usuario;
import com.raj.sgcr.domain.repository.CorridaRepository;
import com.raj.sgcr.domain.repository.OrganizadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
        model.addAttribute("title", "Cadastro organizador");
        model.addAttribute("botaoOperacao", "Cadastrar");

        return "organizador/manter";
    }

    @PostMapping(value = "add")
    public String processOrganizadorForm(@ModelAttribute Organizador organizador, Model model) {
        organizadorRepository.save(organizador);
        model.addAttribute("msgSucesso", "Cadastro como organizador efetuado com sucesso!");
        return "auth/login";
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
        model.addAttribute("title", "Busca Corrida");
        model.addAttribute("botaoOperacao", "Buscar");

        model.addAttribute("organizadores", organizadorRepository.findAll());
        model.addAttribute("corridas", corridaRepository.findAll());
        return "organizador/buscaCorridaOrganizador";
    }
    @RequestMapping(value = "/buscarCorrida")
    public String organizadorBusca(Model model,@RequestParam("organizador") Long id) {
        Optional<Organizador> organizador = organizadorRepository.findById(id);
        model.addAttribute("operacao", "busca");
        model.addAttribute("title", "Busca Corrida");
        model.addAttribute("botaoOperacao", "Buscar");

        model.addAttribute("organizadores", organizadorRepository.findAll());
        model.addAttribute("corridas", corridaRepository.findByOrganizador(organizador.get()));
        return "organizador/buscaCorridaOrganizador";
    }

    @PostMapping(value = "perfil")
    public String editarPerfil(Model model, @ModelAttribute Organizador organizador, HttpServletRequest request) {
        if(Usuario.isOrganizador(request)) {
            Organizador usuario = (Organizador) Usuario.getUsuario(request);
            organizador.setId(usuario.getId());
            organizadorRepository.save(organizador);
            request.getSession().setAttribute("usuario", organizador);
            model.addAttribute("operacao", "editar");
            model.addAttribute("botaoOperacao", "Editar");
            model.addAttribute("title", "Perfil de Organizador");
            model.addAttribute("organizador", Usuario.getUsuario(request));
            model.addAttribute("action", "/organizador/perfil");
            model.addAttribute("msgSucesso", "Dados alterados com sucesso!");
            return "organizador/manter";
        }
        return "redirect:/perfil";
    }

    @PostMapping(value = "deletar")
    public String deletarPerfil(@ModelAttribute Organizador organizador, HttpServletRequest request) {
        if(Usuario.isOrganizador(request)) {
            Organizador usuario = (Organizador) Usuario.getUsuario(request);
            organizador.setId(usuario.getId());
            organizadorRepository.delete(organizador);
            request.getSession().removeAttribute("usuario");
            request.getSession().removeAttribute("permissao");
            return "redirect:/";
        }
        return "redirect:/perfil";
    }
}
