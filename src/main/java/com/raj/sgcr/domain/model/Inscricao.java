package com.raj.sgcr.domain.model;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@Accessors(chain = true)

public class Inscricao {
    @Id
    @GeneratedValue
    private int id;
    private int corridaId;
   // private Corrida corrida;
    private String dataCompra;
    private String numeroPeito;
    private boolean pago;
    private boolean kitRetirado;
    private String tempoLargada;
    private String tempoChegada;
    private String tempoFinal;
   // private Atleta atleta;
    //private Percurso percurso;
   // private Kit kit;
    //private Lote lote;

    private int atletaId;
    private int percursoId;
    private int kitId;
    private int loteId;
}
