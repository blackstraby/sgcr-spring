package com.raj.sgcr.domain.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@Entity
@Accessors(chain = true)
public class Ranking {
    @Id
    @GeneratedValue
    private Long id;
    private  String nome;
    private int intervaloFaixaEtaria;
    private int idadeInicial;

    @ManyToOne
    Administrador administrador;
}
