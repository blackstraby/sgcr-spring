package com.raj.sgcr.controller;

import com.raj.sgcr.domain.model.Corrida;
import com.raj.sgcr.domain.repository.CorridaRepository;
<<<<<<< HEAD
=======
import com.raj.sgcr.domain.repository.OrganizadorRepository;
>>>>>>> 0d6a7b9b5dc182aebc92e44e3da8adf2e090693f
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(value = "corridas")
public class CorridaController {
<<<<<<< HEAD

    @Autowired
    private CorridaRepository corridaRepository;
=======
    private CorridaRepository corridaRepository;
    private OrganizadorRepository organizadorRepository;

    @Autowired
    public CorridaController(CorridaRepository corridaRepository,
                               OrganizadorRepository organizadorRepository) {
        this.corridaRepository = corridaRepository;
        this.organizadorRepository = organizadorRepository;
    }
>>>>>>> 0d6a7b9b5dc182aebc92e44e3da8adf2e090693f

    @GetMapping(value = "")
    public String corridas(Model model){
        model.addAttribute("operacao", "listar");
        model.addAttribute("title", "Lista de corridas");
        model.addAttribute("corridas", corridaRepository.findAll());
        return "corrida/corridas";
    }

    @GetMapping(value = "add")
    public String getCorridasAdd(Model model){
        model.addAttribute("operacao", "adicionar");
<<<<<<< HEAD
        model.addAttribute("title", "Adicionar corrida");
        return "corrida/add";
=======
        model.addAttribute("organizadores", organizadorRepository.findAll());
        model.addAttribute("title", "Adicionar corrida");
        return "corrida/corridas";
>>>>>>> 0d6a7b9b5dc182aebc92e44e3da8adf2e090693f
    }

    @PostMapping(value = "add")
    public String postCorridasAdd(Model model, @ModelAttribute Corrida corrida){
        model.addAttribute("title", "Adicionar corrida");
        corridaRepository.save(corrida);
<<<<<<< HEAD
        return "redirect:";
=======
        return "redirect:/corridas";
>>>>>>> 0d6a7b9b5dc182aebc92e44e3da8adf2e090693f
    }

    @GetMapping(value = "edit/{id}")
    public String getCorridaEdit(Model model, @PathVariable Long id) {
        model.addAttribute("operacao", "editar");
        model.addAttribute("title", "Editar corrida");
<<<<<<< HEAD
=======
        model.addAttribute("organizadores", organizadorRepository.findAll());
>>>>>>> 0d6a7b9b5dc182aebc92e44e3da8adf2e090693f
        Optional<Corrida> corrida = corridaRepository.findById(id);
        if (corrida.isPresent()){
            model.addAttribute("corrida", corrida.get());
        }
<<<<<<< HEAD
        return "corrida/edit";
=======
        return "corrida/corridas";
>>>>>>> 0d6a7b9b5dc182aebc92e44e3da8adf2e090693f
    }

    @PostMapping(value = "edit/{id}")
    public String postCorridaEdit(@ModelAttribute Corrida corrida, Model model,
                                  @PathVariable Long id) throws Exception {
        if (id.equals(corrida.getId())) {
            corridaRepository.save(corrida);
        } else {
            model.addAttribute("error", "Dados incorretos");
        }
        return "redirect:/corridas";
    }

    @GetMapping(value = "delete/{id}")
    public String getCorridaDelete(Model model, @PathVariable Long id) {
        model.addAttribute("operacao", "deletar");
        model.addAttribute("title", "Excluir corrida");
<<<<<<< HEAD
=======
        model.addAttribute("organizadores", organizadorRepository.findAll());
>>>>>>> 0d6a7b9b5dc182aebc92e44e3da8adf2e090693f
        Optional<Corrida> corrida = corridaRepository.findById(id);
        if (corrida.isPresent()) {
            model.addAttribute("corrida", corrida.get());
        }

<<<<<<< HEAD
        return "corrida/delete";
=======
        return "corrida/corridas";
>>>>>>> 0d6a7b9b5dc182aebc92e44e3da8adf2e090693f
    }

    @PostMapping(value = "delete/{id}")
    public String postCorridaDelete(@PathVariable Long id, @ModelAttribute Corrida corrida) {
        corridaRepository.delete(corrida);
        return "redirect:/corridas";
    }

}
