package com.raj.sgcr.domain.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Set;


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
    @OneToMany(mappedBy="percurso")
    private Set<Inscricao> inscricao;
}
