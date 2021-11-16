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
@Table(name = "payments_type")
public class PaymentType   extends AbstractBaseEntity{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty
    private String name;
    
    private String description;    
    
    private String dgiiCode;    
                
    @Enumerated(EnumType.STRING)
    private GenericStatus status;
    
    public PaymentType() {
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDgiiCode() {
        return dgiiCode;
    }

    public void setDgiiCode(String dgiiCode) {
        this.dgiiCode = dgiiCode;
    }

    public GenericStatus getStatus() {
        return status;
    }

    public void setStatus(GenericStatus status) {
        this.status = status;
    }
    
    

    
}
