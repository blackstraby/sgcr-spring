package com.raj.sgcr.domain.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Accessors(chain = true)
public class Corrida implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private Long serialVersionUID = 1L;
    private String nome;
    private Date horario;
    private Date data;
    private Integer maxPessoa;
    private String banner;
    private String descricao;
    private String cep;
    private String estado;
    private String cidade;
    private String bairro;
    private String logradouro;
    private String numero;
    private Integer edicao;
    private Integer ativo;
    private Date dataInicioRetiradaKit;
    private Date dataFinalRetiradaKit;
    @ManyToOne
    private Organizador organizador;
}
