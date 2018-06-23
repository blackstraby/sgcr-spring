package com.raj.sgcr.controller;

import com.raj.sgcr.domain.model.Administrador;
import com.raj.sgcr.domain.repository.AdministradorRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping(value = "/administrador")
public class AdministradorController {

    @Autowired
    private AdministradorRepository adminRepository;

    @GetMapping(value = "")
    public String administradores(Model model) {
        model.addAttribute("administradores", adminRepository.findAll());
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
        adminRepository.save(admin);
        return "redirect:/administrador";
    }

    @GetMapping(value = "edit/{id}")// site.com/administrador/edit/1/
    public String administradorEdit(Model model, @PathVariable Long id) {
        Optional<Administrador> admin = adminRepository.findById(id);
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
            adminRepository.save(admin);
        } else {
            model.addAttribute("error", "Dados incorretos");
        }
        return "redirect:/administrador";
    }

    @GetMapping(value = "delete/{id}") // site.com/administrador/delete/1/
    public String corredorDelete(Model model, @PathVariable Long id) {
        Optional<Administrador> admin = adminRepository.findById(id);
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
        adminRepository.delete(admin);
        return "redirect:/administrador";
    }

    @GetMapping(value = "report")
    @ResponseBody
    public String getRpt1(HttpServletResponse response) throws JRException, IOException {
        InputStream jasperStream = this.getClass().getResourceAsStream("/reports/report_atleta.jasper");
        Map<String,Object> params = new HashMap<>();
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());

        response.setContentType("application/x-pdf");
        response.setHeader("Content-disposition", "inline; filename=report_atleta.pdf");

        final OutputStream outStream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);

        return "redirect:/administrador";
    }
}
