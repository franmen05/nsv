
package com.nsv.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.Column;
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
@Table(name = "invoice_items")
public class InvoiceItem implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long quantity;
    
    private String description;
    
    /**
     * <b> ( product  price on date creation item)</b> the cost can be different than product.price 
     * 
     */
    @Column(precision = 10,scale = 2)
    private Double cost;
    
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
    
    @Column(precision = 10,scale = 2)
    private Float discount;

    public InvoiceItem() {
    }
    
    public void addProduct(Product p) {
        setProduct(p);
        setCost(p.getPrice());
        setDescription(p.getName());
    }
    
    public Double total(){
        
        if(cost==null)return 0.0d;
        Double total= subTotal();
        if(discount!=null)
            return  total-discountTotal();
        return total;
    }
    
    public Double subTotal(){
        
        if(cost==null)return 0.0d;
        Double total= cost*quantity;
        return total;
    }
    
    public Double discountTotal(){
        
        if(discount==null) return 0.0d;
        return subTotal()*discount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Float getDiscount() {
        if(discount==null)
            return 0.0f;
        
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double price) {
        this.cost = price;
    }

}