package com.nsv.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

/**
 *
 * @author you_k
 */
@Entity
@Table(name = "taxes")
public class Tax  implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty
    private String name;
    
    private String description;
    /**
     * valor % del impuesto
     */
    @NotEmpty
    private Double value;
    
            
    @Enumerated(EnumType.STRING)
    private GenericStatus status;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
        
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private TaxGroup taxGroup;

    public Tax() {
    }

    
    
    @PrePersist
    public void prePersist(){
       createDate= new Date();
    }
    
    
    @PreUpdate
    public void preUpdate(){
        lastUpdateDate= new Date();
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

    public Double getValue() {
        return value;
    }

    public void setValue(Double price) {
        this.value = price;
    }

    public GenericStatus getStatus() {
        return status;
    }

    public void setStatus(GenericStatus status) {
        this.status = status;
    }

    public TaxGroup getTaxGroup() {
        return taxGroup;
    }

    public void setTaxGroup(TaxGroup taxGroup) {
        this.taxGroup = taxGroup;
    }    

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
        
    
}
