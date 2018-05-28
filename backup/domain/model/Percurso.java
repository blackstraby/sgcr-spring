package com.raj.sgcr.domain.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Data
@Entity
@Accessors(chain = true)
public class Percurso {
    @Id
    @GeneratedValue
    private int id;
    private String descricao;
    private Double quilometragem;
    private Organizador organizador;

    private int organizadorId;
}
