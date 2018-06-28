package com.raj.sgcr.controller;

import com.raj.sgcr.domain.model.Corrida;
import com.raj.sgcr.domain.model.Organizador;
import com.raj.sgcr.domain.model.Usuario;
import com.raj.sgcr.domain.repository.CorridaRepository;
import com.raj.sgcr.domain.repository.OrganizadorRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRSaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Optional;

@Controller
@RequestMapping(value = "/relatorio")
public class RelatorioController {
    @Autowired
    private CorridaRepository corridaRepository;

    @Autowired
    private OrganizadorRepository organizadorRepository;

    public JasperReport gerarJasper(String relatorio, String nome) throws JRException {
        InputStream relatorioStream = getClass().getResourceAsStream(relatorio);

        if (relatorioStream == null)
            return null;

        JasperReport jasperReport = JasperCompileManager.compileReport(relatorioStream);
        JRSaver.saveObject(jasperReport, nome + ".jasper");
        return jasperReport;
    }

    public Connection abrirConexao() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost/SGCR2", "root", "");

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public JasperPrint exibirRelatorio(JasperReport relatorio, HashMap parametros, Connection conexao) throws JRException {
        // parametros.put("PAR_codCurso", Integer.parseInt(request.getParameter("txtCodCurso")));
        return JasperFillManager.fillReport(relatorio, parametros, conexao);
    }

    @GetMapping(value = "")
    public String percursos(Model model, HttpServletRequest request) {
        if (Usuario.isAdministrador(request)) {
        return "relatorio/pesquisar";

        }
        return "redirect:login";
    }


    @GetMapping(value = "gerar/{report}/{tipo}/{parametro}")// site.com/administrador/edit/1/
    public void gerarRelatorioCorridaEstado(HttpServletRequest request, HttpServletResponse response,
                                            @PathVariable String report,@PathVariable String tipo, @PathVariable String parametro)
            throws JRException, IOException, SQLException {
        String nomeRelatorio = report;
        Connection conexao = this.abrirConexao();


        JasperReport relatorio = this.gerarJasper("/reports/" + nomeRelatorio + ".jrxml", nomeRelatorio);
        if (relatorio != null) {
            HashMap parametros = new HashMap();

            parametros.put("P_"+tipo, parametro);

            JasperPrint JP = JasperFillManager.fillReport(relatorio, parametros, conexao);
            byte[] relat = JasperExportManager.exportReportToPdf(this.exibirRelatorio(relatorio, parametros, conexao));
            response.setHeader("Content-Disposition", "attachment;filename=" + nomeRelatorio + ".pdf");
            response.setContentType("application/pdf");
            response.getOutputStream().write(relat);
        }
        conexao.close();

    }



    @GetMapping(value = "{report}")
    public void gerarRelatorio(HttpServletRequest request, HttpServletResponse response, @PathVariable String report) throws JRException, IOException, SQLException {
        String nomeRelatorio = report;
        Connection conexao = this.abrirConexao();


        JasperReport relatorio = this.gerarJasper("/reports/" + nomeRelatorio + ".jrxml", nomeRelatorio);
        if (relatorio != null) {
            HashMap parametros = new HashMap();
            byte[] relat = JasperExportManager.exportReportToPdf(this.exibirRelatorio(relatorio, parametros, conexao));
            response.setHeader("Content-Disposition", "attachment;filename=" + nomeRelatorio + ".pdf");
            response.setContentType("application/pdf");
            response.getOutputStream().write(relat);
        }
        conexao.close();

    }


    @GetMapping(value = "corrida")
    public String relatorioCorrida(Model model) {
        model.addAttribute("operacao", "busca");
        model.addAttribute("title", "Relatorio Corrida");

        model.addAttribute("estados", corridaRepository.findDistinctByEstado());
        model.addAttribute("organizadores", organizadorRepository.findAll());

        model.addAttribute("parametro", "");
        model.addAttribute("relatorio", "");

        return "relatorio/corrida";
    }

    @RequestMapping(value = "gerarRelatorioCorridaEstado")
    public String relatorioCorridaEstado(Model model, @RequestParam("estado") String estado) {
        model.addAttribute("operacao", "busca");
        model.addAttribute("title", "Relatorio Corrida");

        model.addAttribute("parametro", estado);
        model.addAttribute("tipo", "estado");

        model.addAttribute("relatorio", "report_corridaEstado");

        model.addAttribute("estados", corridaRepository.findDistinctByEstado());
        model.addAttribute("organizadores", organizadorRepository.findAll());


        return "relatorio/corrida";
    }

    @RequestMapping(value = "gerarRelatorioCorridaOrganizador")
    public String relatorioCorridaOrganizador(Model model, @RequestParam("organizador") String nome) {


        model.addAttribute("operacao", "busca");
        model.addAttribute("title", "Relatorio Corrida");

        model.addAttribute("organizadores", organizadorRepository.findAll());
        model.addAttribute("parametro", nome );
        model.addAttribute("tipo", "organizador");

        model.addAttribute("relatorio", "report_corridaOrganizador");


        return "relatorio/corrida";
    }



    @GetMapping(value = "organizador")
    public String relatorioOrganizador(Model model) {
        model.addAttribute("operacao", "busca");
        model.addAttribute("title", "Relatorio Organizador");

        model.addAttribute("estados", organizadorRepository.findDistinctByEstado());
        model.addAttribute("organizadores", organizadorRepository.findAll());

        model.addAttribute("parametro", "");
        model.addAttribute("relatorio", "");

        return "relatorio/organizador";
    }


    @RequestMapping(value = "gerarRelatorioOrganizadorEstado")
    public String relatorioOrganizadorEstado(Model model, @RequestParam("estado") String estado) {
        model.addAttribute("operacao", "busca");
        model.addAttribute("title", "Relatorio Organizador");

        model.addAttribute("parametro", estado);
        model.addAttribute("tipo", "Estado");

        model.addAttribute("relatorio", "report_organizadorEstado");

        model.addAttribute("estados", corridaRepository.findDistinctByEstado());
        model.addAttribute("organizadores", organizadorRepository.findAll());


        return "relatorio/organizador";
    }

    @RequestMapping(value = "gerarRelatorioOrganizadorSexo")
    public String relatorioOrganizadorSexo(Model model, @RequestParam("sexo") String sexo) {


        model.addAttribute("operacao", "busca");
        model.addAttribute("title", "Relatorio Organizador");

        model.addAttribute("organizadores", organizadorRepository.findAll());
        model.addAttribute("parametro", sexo );
        model.addAttribute("tipo", "sexo");

        model.addAttribute("relatorio", "report_organizadorSexo");


        return "relatorio/organizador";
    }



}