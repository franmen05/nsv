package com.nsv.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author you_k
 */
@Entity
@Table(name = "additional_expenses")
public class AdditionalExpense  implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty
    private String name;
    
    private String description;
    
//    @NotEmpty
//    private Double cost;
    
    @Enumerated(EnumType.STRING)
    private GenericStatus status;

    
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    
        
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;

    public AdditionalExpense() {
    }
    
    
    
    
    @PrePersist
    public void prePersist(){
       createDate= new Date();
    }
    
    
    @PreUpdate
    public void preUpdate(){
        lastUpdateDate= new Date();
    }
    public AdditionalExpense inactive(){
        this.setStatus(GenericStatus.INACTIVE);
        return this;
    }
    public AdditionalExpense active(){
        this.setStatus(GenericStatus.ACTIVE);
        return this;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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

    public GenericStatus getStatus() {
        return status;
    }

    public void setStatus(GenericStatus status) {
        this.status = status;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
    
    
     
}
