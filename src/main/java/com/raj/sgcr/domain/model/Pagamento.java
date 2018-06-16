package com.raj.sgcr.domain.model;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.experimental.Accessors;

import javax.persistence.*;


@Data
@Entity
@Accessors(chain = true)
public class Pagamento {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Corrida Corrida;

    //private Inscricao inscricao;
}
