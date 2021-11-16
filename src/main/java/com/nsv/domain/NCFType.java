package com.nsv.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

/**
 *
 * @author you_k
 */
@Entity
@Table(name = "ncf_types")
public class NCFType  extends AbstractBaseEntity{
    
    @Id
    @NotEmpty
    private String id;
    
    private String description;
                   
    @Enumerated(EnumType.STRING)
    private GenericStatus status;


    public NCFType() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public GenericStatus getStatus() {
        return status;
    }

    public void setStatus(GenericStatus status) {
        this.status = status;
    }
}
