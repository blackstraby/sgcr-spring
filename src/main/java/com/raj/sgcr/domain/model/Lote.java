package com.raj.sgcr.domain.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;


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
    @OneToMany(mappedBy="lote")
    private Set<Inscricao> inscricao;
}
