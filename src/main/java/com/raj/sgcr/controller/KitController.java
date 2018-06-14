package com.raj.sgcr.controller;

import com.raj.sgcr.domain.model.Kit;
import com.raj.sgcr.domain.repository.OrganizadorRepository;
import com.raj.sgcr.domain.repository.KitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(value = "/kit")
public class KitController {
    private KitRepository kitRepository;
    private OrganizadorRepository organizadorRepository;

    @Autowired
    public KitController (KitRepository kitRepository,
                              OrganizadorRepository organizadorRepository) {
        this.kitRepository = kitRepository;
        this.organizadorRepository = organizadorRepository;
    }

    @GetMapping(value = "")
    public String kits(Model model){
        model.addAttribute("operacao", "listar");
        model.addAttribute("title", "Lista de Kit");
        model.addAttribute("kits", kitRepository.findAll());
        return "kit/pesquisar";
    }

    @GetMapping(value = "add")
    public String getKitAdd(Model model){
        model.addAttribute("operacao", "adicionar");
        model.addAttribute("organizadores", organizadorRepository.findAll());
        model.addAttribute("title", "Adicionar Kit");
        model.addAttribute("botaoOperacao", "Adicionar Kit");

        return "kit/manter";
    }

    @PostMapping(value = "add")
    public String postKitAdd(Model model, @ModelAttribute Kit kit){
        model.addAttribute("title", "Adicionar Kit");

        kitRepository.save(kit);
        return "redirect:/kit";
    }

    @GetMapping(value = "edit/{id}")
    public String getKitEdit(Model model, @PathVariable Long id) {
        model.addAttribute("operacao", "editar");
        model.addAttribute("title", "Editar Kit");
        model.addAttribute("botaoOperacao", "Editar Kit");
        model.addAttribute("organizadores", organizadorRepository.findAll());
        Optional<Kit> kit = kitRepository.findById(id);
        if (kit.isPresent()){
            model.addAttribute("kit", kit.get());
        }
        return "kit/manter";
    }

    @PostMapping(value = "edit/{id}")
    public String postKitEdit(@ModelAttribute Kit kit, Model model,
                                   @PathVariable Long id) throws Exception {
        if (id.equals(kit.getId())) {
            kitRepository.save(kit);
        } else {
            model.addAttribute("error", "Dados incorretos");
        }
        return "redirect:/kit";
    }

    @GetMapping(value = "delete/{id}")
    public String getKitDelete(Model model, @PathVariable Long id) {
        model.addAttribute("operacao", "deletar");
        model.addAttribute("title", "Excluir Kit");
        model.addAttribute("botaoOperacao", "Excluir Kit");
        model.addAttribute("organizadores", organizadorRepository.findAll());
        Optional<Kit> kit = kitRepository.findById(id);
        if (kit.isPresent()) {
            model.addAttribute("kit", kit.get());
        }

        return "kit/manter";
    }

    @PostMapping(value = "delete/{id}")
    public String postKitDelete(@PathVariable Long id, @ModelAttribute Kit kit) {
        kitRepository.delete(kit);
        return "redirect:/kit";
    }

}
