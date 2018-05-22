package com.raj.sgcr.domain.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
@Accessors(chain = true)
public class Ranking implements Serializable {
    @Id
    @GeneratedValue
    private int id;
    private String nome;
    private int intervaloFaixaEtaria;
    private int idadeInicial;
    private String tempoLargada;
    private String tempoChegada;
    private String tempo;
    private int quilometragem;
    private double pace;
//    private Administrador administrador;
//    private Atleta atleta;

    private int administradorId;
    private int atletaId;
}
