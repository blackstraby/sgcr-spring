package com.raj.sgcr.controller;

import com.raj.sgcr.domain.model.Atleta;
import com.raj.sgcr.domain.model.Usuario;
import com.raj.sgcr.domain.repository.AtletaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@RequestMapping(value = "/atleta")
public class AtletaController {

    @Autowired
    private AtletaRepository atletaRepository;

    @GetMapping(value = "")
    public String corredores(Model model, HttpServletRequest request) {
        if (Usuario.isOrganizador(request) || Usuario.isAdministrador(request)) {
            model.addAttribute("operacao", "listar");
            model.addAttribute("title", "Lista Atleta");
            model.addAttribute("botaoOperacao", "Listar Atleta");
            model.addAttribute("atletas", atletaRepository.findAll());
            return "atleta/pesquisar";
        }
        return "redirect:/";
    }

    @GetMapping(value = "add")
    public String displayCorredorForm(Model model, HttpServletRequest request) {
        if (!Usuario.isLogado(request)) {
            model.addAttribute("operacao", "adicionar");
            model.addAttribute("title", "Cadastro atleta");
            model.addAttribute("botaoOperacao", "Cadastrar");
            return "atleta/manter";
        }
        return "redirect:/login";
    }

    @PostMapping(value = "add")
    public String processCorredorForm(@ModelAttribute Atleta corredor, Model model) {
        atletaRepository.save(corredor);
        model.addAttribute("msgSucesso", "Cadastro como atleta efetuado com sucesso!");
        return "auth/login";
    }

    @GetMapping(value = "edit/{id}")
    public String corredorEdit(Model model, @PathVariable Long id) {
        Optional<Atleta> corredor = atletaRepository.findById(id);
        model.addAttribute("operacao", "editar");
        model.addAttribute("title", "Editar atleta");
        model.addAttribute("botaoOperacao", "Editar Atleta");

        if (corredor.isPresent()) {
            model.addAttribute("atleta", corredor.get());
        }
        return "atleta/manter";
    }

    @PostMapping(value = "edit/{id}")
    public String edit(@ModelAttribute Atleta corredor, Model model,
                       @PathVariable Long id) throws Exception {
        if (id.equals(corredor.getId())) {
            atletaRepository.save(corredor);
        } else {
            model.addAttribute("error", "Dados incorretos");
        }
        return "redirect:/atleta";
    }

    @GetMapping(value = "delete/{id}") // site.com/atleta/delete/1
    public String corredorDelete(Model model, @PathVariable Long id) {
        Optional<Atleta> corredor = atletaRepository.findById(id);
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
        atletaRepository.delete(corredor);
        return "redirect:/atleta";
    }

    @RequestMapping(value = "/busca")
    public String busca(Model model, @RequestParam("nome") String nome, HttpServletRequest request) {
        if (Usuario.isOrganizador(request) || Usuario.isAdministrador(request)) {
            model.addAttribute("atletas", atletaRepository.findByNomeContaining(nome));
            model.addAttribute("operacao", "buscar");
            model.addAttribute("title", "Busca Atleta");
            model.addAttribute("botaoOperacao", "buscar Atleta");
            return "atleta/pesquisar";
        }
        return "redirect:/";
    }

    @PostMapping(value = "perfil")
    public String editarPerfil(Model model, @ModelAttribute Atleta atleta, HttpServletRequest request) {
        if (Usuario.isAtleta(request)) {
            Atleta usuario = (Atleta) Usuario.getUsuario(request);
            atleta.setId(usuario.getId());
            atletaRepository.save(atleta);
            request.getSession().setAttribute("usuario", atleta);
            model.addAttribute("operacao", "editar");
            model.addAttribute("botaoOperacao", "Editar");
            model.addAttribute("title", "Perfil de Atleta");
            model.addAttribute("atleta", Usuario.getUsuario(request));
            model.addAttribute("action", "/atleta/perfil");
            model.addAttribute("msgSucesso", "Dados alterados com sucesso!");
            return "atleta/manter";
        }
        return "redirect:/perfil";
    }

    @PostMapping(value = "deletar")
    public String deletarPerfil(@ModelAttribute Atleta atleta, HttpServletRequest request) {
        if (Usuario.isAtleta(request)) {
            Atleta usuario = (Atleta) Usuario.getUsuario(request);
            atleta.setId(usuario.getId());
            atletaRepository.delete(atleta);
            request.getSession().removeAttribute("usuario");
            request.getSession().removeAttribute("permissao");
            return "redirect:/";
        }
        return "redirect:/perfil";
    }
}
