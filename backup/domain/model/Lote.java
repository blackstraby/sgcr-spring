package com.raj.sgcr.domain.model;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import com.raj.sgcr.domain.model.Corrida;

@Data
@Entity
@Accessors(chain = true)

public class Lote {
    @Id
    @GeneratedValue
    private int id;
    private String tipo;
    private double preco;
    private String dataInicio;
    private String dataFinal;
   // private Corrida corrida;

    private int corridaId;
}
