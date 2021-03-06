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
public class Atleta extends  UsuarioComum  implements Serializable {
    private String tamCamisa;
    private String apelido;

}
