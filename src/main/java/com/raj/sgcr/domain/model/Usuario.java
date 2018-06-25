package com.raj.sgcr.domain.model;

import lombok.Data;
import lombok.experimental.Accessors;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Data
@Entity
@Accessors(chain = true)
public class Usuario implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String nome;
    private String email;
    private String senha;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "usuario_regra", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "regra_id"))
    private Set<Regra> regras;
}
