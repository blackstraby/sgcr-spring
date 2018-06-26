package com.raj.sgcr.domain.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

@Data
@MappedSuperclass
@Accessors(chain = true)
public class Usuario implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String nome;
    @Column(unique=true)
    private String email;
    private String senha;
}
