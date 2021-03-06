
package com.nsv.domain;

import javax.persistence.*;
import java.io.Serializable;

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

    public static  AdditionalExpenseItem build(AdditionalExpense additionalExpense,Double amount) {
        var r= new AdditionalExpenseItem();
        r.additionalExpense = additionalExpense;
        r.amount = amount;
        return r;

    }

    public AdditionalExpenseItem() {}
    
    
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