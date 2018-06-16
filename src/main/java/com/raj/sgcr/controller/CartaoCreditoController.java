package com.raj.sgcr.controller;

import com.raj.sgcr.domain.model.CartaoCredito;
import com.raj.sgcr.domain.repository.CartaoCreditoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@Controller
@RequestMapping(value = "cartaoCredito")
public class CartaoCreditoController {
    @Autowired
    private CartaoCreditoRepository cartaoCreditoRepository;

    @GetMapping(value = "")
    public String cartaoCredito(Model model) {
        model.addAttribute("operacao", "listar");
        model.addAttribute("title", "Lista Cartões de Credito");
        model.addAttribute("botaoOperacao", "Listar");

        model.addAttribute("cartaoCreditos", cartaoCreditoRepository.findAll());
        return "cartaoCredito/pesquisar";
    }

    @GetMapping(value = "add")
    public String displayCartaoCreditoForm(Model model) {
        model.addAttribute("operacao", "adicionar");
        model.addAttribute("title", "Adicionar Cartao de Credito");
        model.addAttribute("botaoOperacao", "Adicionar");

        return "cartaoCredito/manter";
    }

    @PostMapping(value = "add")
    public String processCartaoCreditoForm(@ModelAttribute CartaoCredito cartaoCredito) {
        cartaoCreditoRepository.save(cartaoCredito);
        return "redirect:/cartaoCredito";
    }

    @GetMapping(value = "edit/{id}") // site.com/cartaoCredito/edit
    public String cartaoCreditoEdit(Model model, @PathVariable Long id) {
        Optional<CartaoCredito> cartaoCredito = cartaoCreditoRepository.findById(id);
        model.addAttribute("operacao", "editar");
        model.addAttribute("title", "Editar Cartão Credito");
        model.addAttribute("botaoOperacao", "Editar");

        if (cartaoCredito.isPresent()) {
            model.addAttribute("cartaoCredito", cartaoCredito.get());
        }
        return "cartaoCredito/manter";
    }

    @PostMapping(value = "edit/{id}") // site.com/cartaoCredito/edit/1/
    public String edit(@ModelAttribute CartaoCredito cartaoCredito, Model model,
                       @PathVariable Long id) throws Exception {
        if (id.equals(cartaoCredito.getId())) {
            cartaoCreditoRepository.save(cartaoCredito);
        } else {
            model.addAttribute("error", "Dados incorretos");
        }
        return "redirect:/cartaoCredito";
    }

    @GetMapping(value = "delete/{id}") // site.com/cartaoCredito/delete/1
    public String cartaoCreditoDelete(Model model, @PathVariable Long id) {
        Optional<CartaoCredito> cartaoCredito = cartaoCreditoRepository.findById(id);
        model.addAttribute("operacao", "deletar");
        model.addAttribute("title", "Excluir Cartao de Credito");
        model.addAttribute("botaoOperacao", "Excluir");
        if (cartaoCredito.isPresent()) {
            model.addAttribute("cartaoCredito", cartaoCredito.get());
        }
        return "cartaoCredito/manter";
    }

    @PostMapping(value = "delete/{id}")
    public String delete(@PathVariable Long id, @ModelAttribute CartaoCredito cartaoCredito) {
        cartaoCreditoRepository.delete(cartaoCredito);
        return "redirect:/cartaoCredito";
    }
}
