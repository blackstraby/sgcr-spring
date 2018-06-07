package com.raj.sgcr.controller;

import com.raj.sgcr.domain.model.Administrador;
import com.raj.sgcr.domain.model.Atleta;
import com.raj.sgcr.domain.repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@Controller
@RequestMapping(value = "administrador")
public class AdministradorController {

    @Autowired
    private AdministradorRepository adminRepository;

    @GetMapping(value = "")
    public String administradores(Model model) {
        model.addAttribute("administradores", adminRepository.findAll());
        return "administrador/administradores";
    }

    @GetMapping(value = "add")
    public String displayAdministradorForm(Model model) {
        model.addAttribute("title", "Adicionar Administrador");
        return "administrador/add";

    }

    @PostMapping(value = "add")
    public String processAdministradorForm(@ModelAttribute Administrador admin) {
        adminRepository.save(admin);
        return "redirect:";
    }

    @GetMapping(value = "edit/{id}")
    public String administradorEdit(Model model, @PathVariable Long id) {
        Optional<Administrador> admin = adminRepository.findById(id);
        if (admin.isPresent()){
            model.addAttribute("administrador", admin.get());
        }
        model.addAttribute("title", "Editar atleta");
        return "administrador/edit";
    }

    @PostMapping(value = "edit/{id}")
    public String edit(@ModelAttribute Administrador admin, Model model,
                       @PathVariable Long id) throws Exception {
        if (id.equals(admin.getId())) {
            adminRepository.save(admin);
        } else {
            model.addAttribute("error", "Dados incorretos");
        }
        return "redirect:/administrador";
    }

    @GetMapping(value = "delete/{id}")
    public String corredorDelete(Model model, @PathVariable Long id) {
        Optional<Administrador> admin = adminRepository.findById(id);
        if (admin.isPresent()) {
            model.addAttribute("administrador", admin.get());
        }
        model.addAttribute("title", "Excluir Administrador");
        return "administrador/delete";
    }

    @PostMapping(value = "delete/{id}")
    public String delete(@PathVariable Long id, @ModelAttribute Administrador admin) {
        adminRepository.delete(admin);
        return "redirect:/administrador";
    }
}
