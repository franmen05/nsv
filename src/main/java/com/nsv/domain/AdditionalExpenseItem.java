
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
@Table(name = "aditional_expense_items")
public class AdditionalExpenseItem implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "item_id")
    private Long id;
    
//    @Min(value = 1)
    private Double amount;
    
    private String description;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private AdditionalExpense additionalExpense;
    
    private Float discount;

    public AdditionalExpenseItem() {
    }
    
    
    public Double total(){
        return amount;
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

    public AdditionalExpense getAdditionalExpense() {
        return additionalExpense;
    }

    public void setAdditionalExpense(AdditionalExpense additionalExpense) {
        this.additionalExpense = additionalExpense;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
    
    
}