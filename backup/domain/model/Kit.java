package com.raj.sgcr.domain.model;


import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@Accessors(chain = true)
public class Kit {
    @Id
    @GeneratedValue
    private int id;
    private String nome;
    private String descricao;
    private String imagem;
    private String tipoChip;
    private double preco;
    private Organizador organizador;

    private int organizadorId;

}
