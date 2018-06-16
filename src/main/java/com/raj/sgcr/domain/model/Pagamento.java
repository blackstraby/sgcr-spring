package com.raj.sgcr.domain.model;




import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;



@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class  Pagamento implements  Serializable{

    @Id
    private Long id;

    @OneToMany(mappedBy = "pagamento")
    private Set<Inscricao> inscricao;
}
