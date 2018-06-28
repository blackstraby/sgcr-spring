package com.raj.sgcr.controller;

import com.raj.sgcr.domain.model.Administrador;
import com.raj.sgcr.domain.model.Usuario;
import com.raj.sgcr.domain.repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@RequestMapping(value = "/administrador")
public class AdministradorController {

    @Autowired
    private AdministradorRepository administradorRepository;

    @GetMapping(value = "")
    public String administradores(Model model) {
        model.addAttribute("administradores", administradorRepository.findAll());
        model.addAttribute("operacao", "listar");
        model.addAttribute("title", "Lista Administrador");
        model.addAttribute("botaoOperacao", "Listar Admin");
        return "administrador/pesquisar";
    }

    @GetMapping(value = "add")
    public String displayAdministradorForm(Model model) {
        model.addAttribute("operacao", "adicionar");
        model.addAttribute("title", "Adicionar Administrador");
        model.addAttribute("botaoOperacao", "Adicionar Administrador");
        return "administrador/manter";
    }

    @PostMapping(value = "add")
    public String processAdministradorForm(@ModelAttribute Administrador admin) {
        administradorRepository.save(admin);
        return "redirect:/administrador";
    }

    @GetMapping(value = "edit/{id}")// site.com/administrador/edit/1/
    public String administradorEdit(Model model, @PathVariable Long id) {
        Optional<Administrador> admin = administradorRepository.findById(id);
        model.addAttribute("operacao", "editar");
        model.addAttribute("botaoOperacao", "Editar Administrador");
        model.addAttribute("title", "Editar Admin");
        if (admin.isPresent()) {
            model.addAttribute("administrador", admin.get());
        }
        return "administrador/manter";
    }

    @PostMapping(value = "edit/{id}")
    public String edit(@ModelAttribute Administrador admin, Model model,
                       @PathVariable Long id) throws Exception {
        if (id.equals(admin.getId())) {
            administradorRepository.save(admin);
        } else {
            model.addAttribute("error", "Dados incorretos");
        }
        return "redirect:/administrador";
    }

    @GetMapping(value = "delete/{id}")
    public String administradorDelete(Model model, @PathVariable Long id) {
        Optional<Administrador> admin = administradorRepository.findById(id);
        model.addAttribute("operacao", "deletar");
        model.addAttribute("title", "Excluir Administrador");
        model.addAttribute("botaoOperacao", "Excluir Administrador");
        if (admin.isPresent()) {
            model.addAttribute("administrador", admin.get());
        }
        return "administrador/manter";
    }

    @PostMapping(value = "delete/{id}")
    public String delete(@PathVariable Long id, @ModelAttribute Administrador admin) {
        administradorRepository.delete(admin);
        return "redirect:/administrador";
    }

    @PostMapping(value = "perfil")
    public String editarPerfil(Model model, @ModelAttribute Administrador administrador, HttpServletRequest request) {
        if(Usuario.isAdministrador(request)) {
            Administrador usuario = (Administrador) Usuario.getUsuario(request);
            administrador.setId(usuario.getId());
            administradorRepository.save(administrador);
            request.getSession().setAttribute("usuario", administrador);
            model.addAttribute("operacao", "editar");
            model.addAttribute("botaoOperacao", "Editar");
            model.addAttribute("title", "Perfil de Administrador");
            model.addAttribute("administrador", Usuario.getUsuario(request));
            model.addAttribute("action", "/administrador/perfil");
            model.addAttribute("msgSucesso", "Dados alterados com sucesso!");
            return "administrador/manter";
        }
        return "redirect:/perfil";
    }

    @PostMapping(value = "deletar")
    public String deletarPerfil(@ModelAttribute Administrador administrador, HttpServletRequest request) {
        if(Usuario.isAdministrador(request)) {
            Administrador usuario = (Administrador) Usuario.getUsuario(request);
            administrador.setId(usuario.getId());
            administradorRepository.delete(administrador);
            request.getSession().removeAttribute("usuario");
            request.getSession().removeAttribute("permissao");
            return "redirect:/";
        }
        return "redirect:/perfil";
    }
}
