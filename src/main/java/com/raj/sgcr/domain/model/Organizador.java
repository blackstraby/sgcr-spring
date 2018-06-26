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
public class Organizador extends Usuario implements Serializable {
    private String dataNascimento;
    private String sexo;
    private String cpf;
    private String cep;
    private String logradouro;
    private String bairro;
    private String complemento;
    private String numero;
    private String cidade;
    private String estado;
    private String telefone;
    private String celular;
}
