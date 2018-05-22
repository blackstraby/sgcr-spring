package com.raj.sgcr.domain.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Data
@MappedSuperclass
@Accessors(chain = true)
public abstract class Pagamento implements Serializable {
    @Id
    @GeneratedValue
    private int id;
    private Inscricao inscricao;
    private Corrida inscricaoCorrida;
    private int inscricaoId;
    private int inscricaoCorridaId;
}
