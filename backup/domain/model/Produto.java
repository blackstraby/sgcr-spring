package com.raj.sgcr.domain.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@Accessors(chain = true)
public class Produto {
    @Id
    @GeneratedValue
    private int id;
    private String nome;
    private Administrador administrador;

    private int administradorId;
}
