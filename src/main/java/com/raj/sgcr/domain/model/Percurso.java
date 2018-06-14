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
public class Percurso {
    @Id
    @GeneratedValue
    private Long id;
    private String descricao;
    private Double quilometragem;
    @ManyToOne
    private Organizador organizador;

}
