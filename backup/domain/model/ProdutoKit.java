package com.raj.sgcr.domain.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@Accessors(chain = true)
public class ProdutoKit {
    @Id
    @GeneratedValue
    private int id;
    private String descricao;
    private Double valor;
    private Kit kit;
    private Produto produto;
    private Corrida corrida;

    private int kitId;
    private int produtoId;
    private int corridaId;
}
