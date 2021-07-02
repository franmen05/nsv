package com.nsv.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "Quotations")
public class Quotation implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty
    private String description;
    
    private String comment;
    private String contact;
    private String email;
    /**
     * is a %
     */
    private Float discount;
    
    
    private Double total;
    
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
        
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;
    
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private Subsidiary subsidiary;   
    
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    private Currency currency;      
    
    // TODO: hacer que fetch sea Lazy
    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER,orphanRemoval = true)
    @JoinColumn(name = "quotation_id")
    private List<QuotationItem> items; 

    public Quotation() {
        
        init();
    }
 
    public Quotation(Customer c) {
        this.customer = c;
        init();   
    }
    
    @PrePersist
    public void prePersist(){
       createDate= new Date();
    }
    
    
    @PreUpdate
    public void preUpdate(){
        lastUpdateDate= new Date();
    }
    

    public void  init() {
        
        this.items= new ArrayList<>();
    }
    
    public Double getTotal(){
        total=  calculateTotalItem();
//        Double total=0.0;
        if(discount==null) return total;
        if(discount>0)
            total=total-(total*discount);
        return total;
    }
    
//    public Double calculeTotalAdtionalExpensive(Double _total){
//        return addtionalExpensesItems.stream().map((i) -> i.total()).reduce(_total, (accumulator, _item) -> accumulator + _item);    
//    }
    
    public Double calculateTotalItem(){
//       Double _total=0;
        return items.stream().map((i) -> i.total()).reduce(0.0, (accumulator, _item) -> accumulator + _item);
        
    }

    public void setTotal(Double total) {
        this.total = total;
    } 
    
    public void clear() {
        
        this.items.clear();
    }
    
    public void addItem(QuotationItem item){  
        items.add(item);
    }
    
    public void removeItem(int item){
        items.remove(item);
    }
    
    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
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
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }


    public Subsidiary getSubsidiary() {
        return subsidiary;
    }

    public void setSubsidiary(Subsidiary subsidiary) {
        this.subsidiary = subsidiary;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public List<QuotationItem> getItems() {
        return items;
    }

    public void setItems(List<QuotationItem> items) {
        this.items = items;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}