package com.raj.sgcr.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Accessors(chain = true)
public class CartaoCredito extends Pagamento{
    @Id
    @GeneratedValue
    private String numero;
    private String codigoSeguranca;
    private String validade;
    private String nomeTitular;
}
