package com.raj.sgcr.domain.model;

import com.raj.sgcr.controller.CartaoCreditoController;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Accessors(chain = true)
public class Inscricao implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private Long serialVersionUID = 1L;
    private String dataCompra;
    private String numeroPeito;
    private boolean pago;
    private boolean kitRetirado;
    private String tempoLargada;
    private String tempoChegada;
    private String tempoFinal;
    @ManyToOne
    private Corrida corrida;
    @ManyToOne
    private Atleta atleta;
    @ManyToOne
    private Percurso percurso;
    @ManyToOne
    private Lote lote;
    @ManyToOne
    private Kit kit;
    @ManyToOne
    private Boleto boleto;
    @ManyToOne
    private CartaoCredito cartaoCredito;
}