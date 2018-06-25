package com.raj.sgcr.domain.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@MappedSuperclass
@Accessors(chain = true)
public abstract class UsuarioComum implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String nome;
    private String email;
    private String senha;
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
