package com.raj.sgcr.domain.model;



import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.*;
import java.io.Serializable;
@Data
@MappedSuperclass
@Accessors(chain = true)
public abstract class  Pagamento implements Serializable{

    @Id
    @GeneratedValue
    private Long id;

}
