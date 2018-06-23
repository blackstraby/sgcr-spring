package com.raj.sgcr.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Accessors(chain = true)
public class CartaoCredito extends Pagamento implements Serializable {

    private String numero;
    private String codigoSeguranca;
    private String validade;
    private String nomeTitular;

    @OneToMany(mappedBy = "cartaoCredito")
    private Set<Inscricao> inscricao;
}
