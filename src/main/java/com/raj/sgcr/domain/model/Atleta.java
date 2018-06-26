package com.raj.sgcr.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Accessors(chain = true)
public class Atleta extends Usuario implements Serializable {
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

    private String tamCamisa;
    private String apelido;

    @OneToMany(mappedBy="atleta")
    private Set<Inscricao> inscricao;

}
