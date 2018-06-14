package com.raj.sgcr.controller;

import com.raj.sgcr.domain.model.Lote;
import com.raj.sgcr.domain.repository.CorridaRepository;
import com.raj.sgcr.domain.repository.LoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(value = "/lote")
public class LoteController {
    private LoteRepository loteRepository;
    private CorridaRepository corridaRepository;

    @Autowired
    public LoteController(LoteRepository loteRepository,
                          CorridaRepository corridaRepository) {
        this.loteRepository = loteRepository;
        this.corridaRepository = corridaRepository;
    }

    @GetMapping(value = "")
    public String lotes(Model model){
        model.addAttribute("operacao", "listar");
        model.addAttribute("title", "Lista de Lote");
        model.addAttribute("lotes", loteRepository.findAll());
        return "lote/pesquisar";
    }

    @GetMapping(value = "add")
    public String getLoteAdd(Model model){
        model.addAttribute("operacao", "adicionar");
        model.addAttribute("corridas", corridaRepository.findAll());
        model.addAttribute("title", "Adicionar Lote");
        model.addAttribute("botaoOperacao", "Adicionar Lote");

        return "lote/manter";
    }

    @PostMapping(value = "add")
    public String postLoteAdd(Model model, @ModelAttribute Lote lote){
        model.addAttribute("title", "Adicionar Lote");

        loteRepository.save(lote);
        return "redirect:/lote";
    }

    @GetMapping(value = "edit/{id}")
    public String getLoteEdit(Model model, @PathVariable Long id) {
        model.addAttribute("operacao", "editar");
        model.addAttribute("title", "Editar Lote");
        model.addAttribute("botaoOperacao", "Editar Lote");
        model.addAttribute("corridas", corridaRepository.findAll());
        Optional<Lote> lote = loteRepository.findById(id);
        if (lote.isPresent()){
            model.addAttribute("lote", lote.get());
        }
        return "lote/manter";
    }

    @PostMapping(value = "edit/{id}")
    public String postLoteEdit(@ModelAttribute Lote lote, Model model,
                                   @PathVariable Long id) throws Exception {
        if (id.equals(lote.getId())) {
            loteRepository.save(lote);
        } else {
            model.addAttribute("error", "Dados incorretos");
        }
        return "redirect:/lote";
    }

    @GetMapping(value = "delete/{id}")
    public String getLoteDelete(Model model, @PathVariable Long id) {
        model.addAttribute("operacao", "deletar");
        model.addAttribute("title", "Excluir Lote");
        model.addAttribute("botaoOperacao", "Excluir Lote");
        model.addAttribute("corridas", corridaRepository.findAll());
        Optional<Lote> lote = loteRepository.findById(id);
        if (lote.isPresent()) {
            model.addAttribute("lote", lote.get());
        }

        return "lote/manter";
    }

    @PostMapping(value = "delete/{id}")
    public String postLoteDelete(@PathVariable Long id, @ModelAttribute Lote lote) {
        loteRepository.delete(lote);
        return "redirect:/lote";
    }

}
