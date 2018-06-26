package com.raj.sgcr.controller;

import com.raj.sgcr.domain.model.Corrida;
import com.raj.sgcr.domain.model.Inscricao;
import com.raj.sgcr.domain.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Random;

@Controller
@RequestMapping(value = "/inscricao")
public class InscricaoController {
    private InscricaoRepository inscricaoRepository;
    private CorridaRepository corridaRepository;
    private AtletaRepository atletaRepository;
    private PercursoRepository percursoRepository;
    private KitRepository kitRepository;
    private LoteRepository loteRepository;

    @Autowired
    public InscricaoController(InscricaoRepository inscricaoRepository,
                               CorridaRepository corridaRepository,
                             AtletaRepository atletaRepository,
                               PercursoRepository percursoRepository,
                               KitRepository kitRepository,
                               LoteRepository loteRepository) {
        this.inscricaoRepository = inscricaoRepository;
        this.corridaRepository = corridaRepository;
        this.atletaRepository = atletaRepository;
        this.percursoRepository = percursoRepository;
        this.kitRepository = kitRepository;
        this.loteRepository = loteRepository;
    }

    @GetMapping(value = "")
    public String inscricoes(Model model){
        model.addAttribute("operacao", "listar");
        model.addAttribute("title", "Lista de inscrições");
        model.addAttribute("inscricoes", inscricaoRepository.findAll());
        model.addAttribute("botaoOperacao", "Listar Inscrições");
        return "inscricao/pesquisar";
    }

    @GetMapping(value = "add")
    public String getInscricoesAdd(Model model){
        model.addAttribute("operacao", "adicionar");
        model.addAttribute("corridas", corridaRepository.findAll());
        model.addAttribute("atletas", atletaRepository.findAll());
        model.addAttribute("percursos", percursoRepository.findAll());
        model.addAttribute("kits", kitRepository.findAll());
        model.addAttribute("lotes", loteRepository.findAll());
        model.addAttribute("title", "Adicionar inscrição");
        model.addAttribute("botaoOperacao", "Adicionar Inscrição");
        return "inscricao/manter";
    }

    @PostMapping(value = "add")
    public String postInscricoesAdd(Model model, @ModelAttribute Inscricao inscricao){
        model.addAttribute("title", "Adicionar inscrição");
        Random gerador = new Random();
        inscricao.setDataCompra("27/06/2018");
        int n = 1 + gerador.nextInt(1000);
        inscricao.setNumeroPeito(String.valueOf(n));
        inscricaoRepository.save(inscricao);
        return "redirect:/inscricao";
    }

    @GetMapping(value = "edit/{id}")
    public String getCorridaEdit(Model model, @PathVariable Long id) {
        model.addAttribute("corridas", corridaRepository.findAll());
        model.addAttribute("atletas", atletaRepository.findAll());
        model.addAttribute("percursos", percursoRepository.findAll());
        model.addAttribute("kits", kitRepository.findAll());
        model.addAttribute("lotes", loteRepository.findAll());
        model.addAttribute("operacao", "editar");
        model.addAttribute("botaoOperacao", "Editar Inscrição");
        model.addAttribute("title", "Editar Inscrição");
        Optional<Inscricao> inscricao = inscricaoRepository.findById(id);
        if (inscricao.isPresent()){
            model.addAttribute("inscricao", inscricao.get());
        }
        return "inscricao/manter";
    }

    @PostMapping(value = "edit/{id}")
    public String postInscricaoEdit(@ModelAttribute Inscricao inscricao, Model model,
                                  @PathVariable Long id) throws Exception {
        if (id.equals(inscricao.getId())) {
            inscricaoRepository.save(inscricao);
        } else {
            model.addAttribute("error", "Dados incorretos");
        }
        return "redirect:/inscricao";
    }

    @GetMapping(value = "delete/{id}")
    public String getInscricaoDelete(Model model, @PathVariable Long id) {
        model.addAttribute("operacao", "deletar");
        model.addAttribute("title", "Excluir Inscrição");
        model.addAttribute("botaoOperacao", "Excluir Inscrição");
        model.addAttribute("corridas", corridaRepository.findAll());
        model.addAttribute("atletas", atletaRepository.findAll());
        model.addAttribute("percursos", percursoRepository.findAll());
        model.addAttribute("kits", kitRepository.findAll());
        model.addAttribute("lotes", loteRepository.findAll());
        Optional<Inscricao> inscricao = inscricaoRepository.findById(id);
        if (inscricao.isPresent()) {
            model.addAttribute("inscricao", inscricao.get());
        }

        return "inscricao/manter";
    }

    @PostMapping(value = "delete/{id}")
    public String postInscricaoDelete(@PathVariable Long id, @ModelAttribute Inscricao inscricao) {
        inscricaoRepository.delete(inscricao);
        return "redirect:/inscricao";
    }

    @GetMapping(value = "corrida/{id}")
    public String getCorridaInscricao(Model model, @PathVariable Long id) {
        Optional<Corrida> corrida = corridaRepository.findById(id);
        if (corrida.isPresent()){
            model.addAttribute("corrida", corrida.get());
            model.addAttribute("atletas", atletaRepository.findAll());
            model.addAttribute("percursos", percursoRepository.findAllByOrganizador(corrida.get().getOrganizador()));
            model.addAttribute("kits", kitRepository.findAllByOrganizador(corrida.get().getOrganizador()));
            model.addAttribute("lotes", loteRepository.findAllByCorrida(corrida.get()));
            model.addAttribute("operacao", "adicionar");
            model.addAttribute("botaoOperacao", "Finalizar Inscrição");
            model.addAttribute("title", "Inscrição na corrida " + corrida.get().getNome());
        } else {
            model.addAttribute("erro", "Ops... Corrida não encontrada!");
        }
        return "inscricao/inscricao";
    }

}