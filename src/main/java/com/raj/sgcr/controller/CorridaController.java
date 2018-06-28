package com.raj.sgcr.controller;

import com.raj.sgcr.domain.model.Corrida;
import com.raj.sgcr.domain.model.Organizador;
import com.raj.sgcr.domain.model.Usuario;
import com.raj.sgcr.domain.repository.CorridaRepository;
import com.raj.sgcr.domain.repository.OrganizadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Controller
@RequestMapping(value = "/corrida")
public class CorridaController {
    private CorridaRepository corridaRepository;
    private OrganizadorRepository organizadorRepository;

    @Autowired
    public CorridaController(CorridaRepository corridaRepository,
                             OrganizadorRepository organizadorRepository) {
        this.corridaRepository = corridaRepository;
        this.organizadorRepository = organizadorRepository;
    }

    @GetMapping(value = "")
    public String corridas(Model model){
        model.addAttribute("operacao", "listar");
        model.addAttribute("title", "Lista de corridas");
        model.addAttribute("corridas", corridaRepository.findAll());
        model.addAttribute("botaoOperacao", "Listar Corridas");
        return "corrida/pesquisar";
    }

    @GetMapping(value = "add")
    public String getCorridasAdd(HttpServletRequest request, Model model){
        if(Usuario.hasPermissao(request, "ORGANIZADOR")) {
            model.addAttribute("operacao", "adicionar");
            model.addAttribute("organizadores", organizadorRepository.findAll());
            model.addAttribute("title", "Adicionar corrida");
            model.addAttribute("botaoOperacao", "Adicionar Corrida");
            return "corrida/manter";
        }
        return "redirect:/";
    }

    @PostMapping(value = "add")
    public String postCorridasAdd(HttpServletRequest request, Model model, @ModelAttribute Corrida corrida){
        if(Usuario.isOrganizador(request)) {
            corrida.setOrganizador((Organizador) request.getSession().getAttribute("usuario"));
            model.addAttribute("title", "Adicionar corrida");
            model.addAttribute("msgSucesso", "Corrida " + corrida.getNome() + " criada com sucesso!");
            corridaRepository.save(corrida);
        } else {
            model.addAttribute("msgErro", "Você não tem permissão para executar esta ação!");
        }
        return "corrida/manter";
    }

    @GetMapping(value = "edit/{id}")
    public String getCorridaEdit(Model model, @PathVariable Long id, HttpServletRequest request) {
        if (Usuario.isOrganizador(request)) {
            model.addAttribute("operacao", "editar");
            model.addAttribute("botaoOperacao", "Editar Corrida");
            model.addAttribute("title", "Editar Corrida");
            Optional<Corrida> corrida = corridaRepository.findByIdAndOrganizador(id,(Organizador) Usuario.getUsuario(request));
            if (corrida.isPresent()){
                model.addAttribute("corrida", corrida.get());
            } else {
                model.addAttribute("msgErro", "Corrida inválida!");
            }
            return "corrida/manter";
        }
        return "redirect:/login";
    }

    @PostMapping(value = "edit/{id}")
    public String postCorridaEdit(@ModelAttribute Corrida corrida, Model model,
                                  @PathVariable Long id, HttpServletRequest request) throws Exception {
        if (Usuario.isOrganizador(request)) {
            if(corridaRepository.findByIdAndOrganizador(id,(Organizador) Usuario.getUsuario(request)).isPresent()) {
                corrida.setOrganizador((Organizador) Usuario.getUsuario(request));
                corridaRepository.save(corrida);
            } else {
                model.addAttribute("msgErro", "Corrida inválida!");
                model.addAttribute("dataAtual", new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis())));
                model.addAttribute("title", "Dashboard");
                model.addAttribute("corridas", corridaRepository.findByOrganizador((Organizador) Usuario.getUsuario(request)));
                return "organizador/corridas";
            }
            model.addAttribute("msgSucesso", "Corrida " + corrida.getNome() + " alterada com sucesso!");
            model.addAttribute("dataAtual", new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis())));
            model.addAttribute("title", "Dashboard");
            model.addAttribute("corridas", corridaRepository.findByOrganizador((Organizador) Usuario.getUsuario(request)));
            return "organizador/corridas";
        }
        return "redirect:/";
    }

    @GetMapping(value = "delete/{id}")
    public String getCorridaDelete(Model model, @PathVariable Long id) {
        model.addAttribute("operacao", "deletar");
        model.addAttribute("title", "Excluir Corrida");
        model.addAttribute("botaoOperacao", "Excluir Corrida");
        model.addAttribute("organizadores", organizadorRepository.findAll());
        Optional<Corrida> corrida = corridaRepository.findById(id);
        if (corrida.isPresent()) {
            model.addAttribute("corrida", corrida.get());
        }

        return "corrida/manter";
    }

    @PostMapping(value = "delete/{id}")
    public String postCorridaDelete(@PathVariable Long id, @ModelAttribute Corrida corrida) {
        corridaRepository.delete(corrida);
        return "redirect:/corrida";
    }

}