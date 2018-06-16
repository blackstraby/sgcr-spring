package com.raj.sgcr.controller;

import com.raj.sgcr.domain.model.Boleto;
import com.raj.sgcr.domain.repository.BoletoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(value = "boleto")
public class BoletoController {
    @Autowired
    private BoletoRepository boletoRepository;

    @GetMapping(value = "")
    public String boleto(Model model) {
        model.addAttribute("operacao", "listar");
        model.addAttribute("title", "Lista Boletos");
        model.addAttribute("botaoOperacao", "Listar Boletos");

        model.addAttribute("boletos", boletoRepository.findAll());
        return "boleto/pesquisar";
    }

    @GetMapping(value = "add")
    public String displayBoletoForm(Model model) {
        model.addAttribute("operacao", "adicionar");
        model.addAttribute("title", "Adicionar boleto");
        model.addAttribute("botaoOperacao", "Adicionar Boleto");

        return "boleto/manter";
    }

    @PostMapping(value = "add")
    public String processBoletoForm(@ModelAttribute Boleto boleto) {
        boletoRepository.save(boleto);
        return "redirect:/boleto";
    }

    @GetMapping(value = "edit/{id}") // site.com/boleto/edit
    public String boletoEdit(Model model, @PathVariable Long id) {
        Optional<Boleto> boleto = boletoRepository.findById(id);
        model.addAttribute("operacao", "editar");
        model.addAttribute("title", "Editar boleto");
        model.addAttribute("botaoOperacao", "Editar Boleto");

        if (boleto.isPresent()) {
            model.addAttribute("boleto", boleto.get());
        }
        return "boleto/manter";
    }

    @PostMapping(value = "edit/{id}") // site.com/boleto/edit/1/
    public String edit(@ModelAttribute Boleto boleto, Model model,
                       @PathVariable Long id) throws Exception {
        if (id.equals(boleto.getId())) {
            boletoRepository.save(boleto);
        } else {
            model.addAttribute("error", "Dados incorretos");
        }
        return "redirect:/boleto";
    }

    @GetMapping(value = "delete/{id}") // site.com/boleto/delete/1
    public String boletoDelete(Model model, @PathVariable Long id) {
        Optional<Boleto> boleto = boletoRepository.findById(id);
        model.addAttribute("operacao", "deletar");
        model.addAttribute("title", "Excluir boleto");
        model.addAttribute("botaoOperacao", "Excluir Boleto");
        if (boleto.isPresent()) {
            model.addAttribute("boleto", boleto.get());
        }
        return "boleto/manter";
    }

    @PostMapping(value = "delete/{id}")
    public String delete(@PathVariable Long id, @ModelAttribute Boleto boleto) {
        boletoRepository.delete(boleto);
        return "redirect:/boleto";
    }
}
