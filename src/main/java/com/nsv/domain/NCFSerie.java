/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nsv.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

/**
 *
 * @author you_k
 */
@Entity
@Table(name = "ncf_series")
public class NCFSerie extends AbstractBaseEntity {
    
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotEmpty
    private String id;
    
    private String description;
                   
    @Enumerated(EnumType.STRING)
    private GenericStatus status;

    public NCFSerie() {
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
