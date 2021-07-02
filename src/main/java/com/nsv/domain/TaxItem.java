
package com.nsv.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author you_k
 */
@Entity
@Table(name = "tax_items")
public class TaxItem implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "item_id")
    private Long id;
    
//    @Min(value = 1)
//    private Long quantity;
    
    private String description;
    private Double value;
    
    
//    @ManyToOne(fetch = FetchType.LAZY)
//    private Tax tax;
//    
//    public Double total(){
//        return  tax.getValue()*quantity;
//    }
    public TaxItem() {
    }

    public TaxItem( Tax tax) {
        this.description= tax.getName();
        this.value= tax.getValue();
    }    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setValue(Double value) {
        this.value = value;
    }
    
    

}