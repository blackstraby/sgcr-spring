package com.raj.sgcr.controller;

import com.raj.sgcr.domain.model.Administrador;
import com.raj.sgcr.domain.model.Atleta;
import com.raj.sgcr.domain.model.Organizador;
import com.raj.sgcr.domain.repository.AdministradorRepository;
import com.raj.sgcr.domain.repository.AtletaRepository;
import com.raj.sgcr.domain.repository.OrganizadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/")
public class TesteController {

    private final AdministradorRepository administradorRepository;
    private final AtletaRepository atletaRepository;
    private final OrganizadorRepository organizadorRepository;

    @Autowired
    public TesteController(AdministradorRepository administradorRepository, AtletaRepository atletaRepository, OrganizadorRepository organizadorRepository) {
        this.administradorRepository = administradorRepository;
        this.atletaRepository = atletaRepository;
        this.organizadorRepository = organizadorRepository;
    }

    @GetMapping
    public Administrador initAdmin() {
        Administrador admin = new Administrador()
                .setNome("Jonas Gomes")
                .setEmail("jonas@gmail.com")
                .setSenha("1234");

        Atleta atleta = (Atleta) new Atleta()
                .setNome("Jonas")
                .setEmail("jonas@gmail.com")
                .setBairro("Sao Geraldo")
                .setCep("36031369")
                .setLogradouro("Jacinto Furtado de Mendonça")
                .setComplemento("")
                .setDataNascimento("17/04/1998")
                .setCelular("999271136")
                .setCpf("1235384958")
                .setEstado("MG")
                .setNumero("131")
                .setCidade("Juiz de Fora")
                .setSexo("M")
                .setTelefone("3299987")
                .setSenha("123");

        Organizador organizador = (Organizador) new Organizador()
                .setNome("Sara Lance")
                .setEmail("sara@gmail.com")
                .setBairro("Nanda Parbat")
                .setCep("2425154")
                .setLogradouro("Estrada 66")
                .setComplemento("")
                .setDataNascimento("30/12/1986")
                .setCelular("98745201")
                .setCpf("1235384958")
                .setEstado("RJ")
                .setNumero("168")
                .setCidade("Rio de Janeiro")
                .setSexo("F")
                .setTelefone("3299987")
                .setSenha("123");


        atletaRepository.save(atleta);
        organizadorRepository.save(organizador);
        return administradorRepository.save(admin);
    }


    private Administrador validarAdministrador(Administrador admin) { return  admin; }

    private Atleta validarAtleta(Atleta atleta ) { return  atleta ; }

    private Organizador validarOrganizador(Organizador organizador) { return  organizador; }

}
