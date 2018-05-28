package com.raj.sgcr.controller;

import com.raj.sgcr.domain.model.*;
import com.raj.sgcr.domain.repository.*;
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
    private final CorridaRepository corridaRepository;
    private final InscricaoRepository inscricaoRepository;
    private final LoteRepository loteRepository;

    @Autowired
    public TesteController(AdministradorRepository administradorRepository, AtletaRepository atletaRepository, OrganizadorRepository organizadorRepository, CorridaRepository corridaRepository, InscricaoRepository inscricaoRepository, LoteRepository loteRepository) {
        this.administradorRepository = administradorRepository;
        this.atletaRepository = atletaRepository;
        this.organizadorRepository = organizadorRepository;
        this.corridaRepository = corridaRepository;
        this.inscricaoRepository = inscricaoRepository;
        this.loteRepository = loteRepository;
    }

    @GetMapping
    public Administrador initAdmin() {
        Administrador admin = new Administrador()
                .setNome("Jonas Gomes")
                .setEmail("jonas@gmail.com")
                .setSenha("1234");

        Atleta atleta = (Atleta) new Atleta()
                .setTamCamisa("M")
                .setApelido("Jhon")
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
/*
        Organizador organizador = (Organizador) new Organizador()
                .setNome("Sara Lance")
                .setEmail("sara@gmail.com")
                .setBairro("NandaParbat")
                .setCep("2425154")
                .setLogradouro("Estrada 66")
                .setComplemento("")
                .setDataNascimento("30/12/18")
                .setCelular("123")
                .setCpf("123")
                .setEstado("RJ")
                .setNumero("168")
                .setCidade("Rio de Janeiro")
                .setSexo("F")
                .setTelefone("123")
                .setSenha("123");
*/
        Corrida corrida = new Corrida()
                .setAtivo(true)
                .setBairro("Sao Jose")
                .setBanner("")
                .setCep("36031258")
                .setCidade("Juiz de Fora")
                .setData("24/05/18")
                .setDataFinalRetiradaKit("29/05/18")
                .setDataInicioRetiradaKit("24/05/18")
                .setDescricao("Corrida de Teste")
                .setEdicao("1")
                .setEstado("MG")
                .setHorario("08:00:00")
                .setLogradouro("Rua dos Silva")
                .setMaxPessoa(250)
                .setNome("Corrida Pasifica")
                .setNumero("5");
                //.setOrganizador()
                //.setOrganizadorId();



        Lote lote = new Lote()
               // .setCorrida(corrida)
                .setCorridaId(corrida.getId())
                .setDataFinal("30/05/18")
                .setDataInicio("26/05/18")
                .setPreco(250)
                .setTipo("VIP");

        Inscricao inscricao = new Inscricao()
               // .setAtleta(atleta)
                .setAtletaId(atleta.getId())
              //  .setCorrida(corrida)
                .setCorridaId(corrida.getId())
                .setDataCompra("29/05/18")
                //.setKitId()
                .setKitRetirado(false)
              //  .setLote(lote)
                .setLoteId(lote.getId())
                .setNumeroPeito("66")
                .setPago(true)
                //.setPercursoId()
                .setTempoChegada("00:00:00")
                .setTempoFinal("00:00:00")
                .setTempoLargada("00:00:00");

        atletaRepository.save(atleta);
       // organizadorRepository.save(organizador);
        corridaRepository.save(corrida);
        loteRepository.save(lote);
        inscricaoRepository.save(inscricao);
        return administradorRepository.save(admin);
    }


    private Administrador validarAdministrador(Administrador admin) { return  admin; }

    private Atleta validarAtleta(Atleta atleta ) { return  atleta ; }

    private Organizador validarOrganizador(Organizador organizador) { return  organizador; }

}
