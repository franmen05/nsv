package com.nsv.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

/**
 *
 * @author you_k 
 */
@Entity
@Table(name = "inventory")
public class Inventory implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String description;
    
    @NotEmpty
    private String comment;
    
    @Temporal(TemporalType.DATE)
    private Date createDate;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;
    
    
    // TODO: hacer que fetch sea Lazy
    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER,orphanRemoval = true)
    @JoinColumn(name = "invoice_id")
    private List<InvoiceItem> items;

    public Inventory() {
        
        this.items= new ArrayList<InvoiceItem>();
    }
    
    public Double getTotal(){
        Double total=0.0;
        
//        for(InvoiceItem i: items)
//            total+=i.total();
//        
        total = items.stream().map((i) -> i.total()).reduce(total, (accumulator, _item) -> accumulator + _item);
        
        return total;
    }
    
    @PrePersist
    public void prePersist() {
        this.createDate= new Date();
    }
        
    public void addItem(InvoiceItem item){
        items.add(item);
    }
    
    public void removeItem(int item){
        items.remove(item);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<InvoiceItem> getItems() {
        return items;
    }

    public void setItems(List<InvoiceItem> items) {
        this.items = items;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }   
}