package com.raj.sgcr.domain.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;


@Data
@Entity
@Accessors(chain = true)
public class Administrador implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String nome;
    @Column(unique=true)
    private String email;
    private String senha;

}
