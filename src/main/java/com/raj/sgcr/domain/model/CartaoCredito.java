package com.raj.sgcr.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Accessors(chain = true)
public class CartaoCredito extends Pagamento implements Serializable {
    private String numero;
    private String codigoSeguranca;
    private String validade;
    private String nomeTitular;
}
