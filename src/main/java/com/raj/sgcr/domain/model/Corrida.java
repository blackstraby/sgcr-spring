package com.raj.sgcr.domain.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@Accessors(chain = true)

public class Corrida {
    @Id
    @GeneratedValue
    private int id;
    private String nome;
    private int maxPessoa;
    private String horario;
    private String data;
    private String banner;
    private String logradouro;
    private String cep;
    private String numero;
    private String cidade;
    private String estado;
    private String bairro;
    private String descricao;
    private String edicao;
    private boolean ativo;
    private String dataInicioRetiradaKit;
    private String dataFinalRetiradaKit;
    private Organizador organizador;

    private int organizadorId;

}
