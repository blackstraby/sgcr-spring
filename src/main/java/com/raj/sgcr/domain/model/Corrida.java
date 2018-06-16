package com.raj.sgcr.domain.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Accessors(chain = true)
public class Corrida implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private Long serialVersionUID = 1L;
    private String nome;
    private String horario;
    private String data;
    private Integer maxPessoa;
    private String banner;
    private String descricao;
    private String cep;
    private String estado;
    private String cidade;
    private String bairro;
    private String logradouro;
    private String numero;
    private Integer ativo;
    private String dataInicioRetiradaKit;
    private String dataFinalRetiradaKit;
    @ManyToOne
    private Organizador organizador;
}