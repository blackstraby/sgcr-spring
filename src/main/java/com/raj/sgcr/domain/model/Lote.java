package com.raj.sgcr.domain.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;


@Data
@Entity
@Accessors(chain = true)

public class Lote {
    @Id
    @GeneratedValue
    private Long id;
    private String tipo;
    private Double preco;
    private String dataInicio;
    private String dataFinal;
    @ManyToOne
    private Corrida corrida;

}
