package com.raj.sgcr.domain.model;

import lombok.Data;
import lombok.experimental.Accessors;
import javax.persistence.Entity;
import java.io.Serializable;


@Data
@Entity
@Accessors(chain = true)
public class Administrador extends Usuario implements Serializable {

}
