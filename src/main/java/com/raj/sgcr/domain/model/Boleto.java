package com.raj.sgcr.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Accessors(chain = true)
public class Boleto extends Pagamento implements Serializable {

    private String codigoBarra;
    private String dataEmissao;
    private String dataVencimento;

    @OneToMany(mappedBy = "boleto")
    private Set<Inscricao> inscricao;
}
