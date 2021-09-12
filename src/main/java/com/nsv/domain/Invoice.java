package com.nsv.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author you_k 
 */
@Entity
@Table(name = "invoices")
public class Invoice implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty
    private String description;
    
    private String comment;
    /**
     * is a %
     */
    private Float discount;
    
    private Double totalWithoutTaxes;
    
    private Double totalWithTaxes;
    
    private Double total;
    
    private Double totalRefund;
    
    private Double totalPayment;
    
    private Boolean hasTax;
    
    @Column(name = "has_ncf")
    private Boolean hasNCF;
    
    @Enumerated(EnumType.STRING)
    private InvoiceStatus status;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    
        
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private PaymentType paymentType; 
    
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
    @JoinColumn(name = "invoice_id")
    private List<InvoiceItem> items; 
    
    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER,orphanRemoval = true)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinColumn(name = "invoice_id")
    private List<AdditionalExpenseItem> addtionalExpensesItems;
    
    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER,orphanRemoval = true)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinColumn(name = "invoice_id")
    private List<TaxItem> taxItems;
    
    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER,orphanRemoval = true)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinColumn(name = "invoice_id")
    private List<Payment> payments ;

    public Invoice() {
        
        init();
    }
 
    public Invoice(Customer c) {
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
        this.addtionalExpensesItems= new ArrayList<>();
        this.taxItems= new ArrayList<>();
        this.payments= new ArrayList<>();
    }
    
    
    public void calculeTotalPayment(){
        totalPayment=0.0;
        payments.forEach((t) -> totalPayment += t.getValue());
        setTotalPayment(totalPayment);
    }
    
    public Double calculeTotalWithoutTaxes(){
        Double tempTotal=0.0;
        
//        for(InvoiceItem i: items)
//            total+=i.total();

        tempTotal = calculateTotalItem(tempTotal);
        tempTotal = calculeTotalAdtionalExpensive(tempTotal);
        setTotalWithoutTaxes(tempTotal);
        return tempTotal;
    }
    
    
    public Double calculeTotalWithTaxes(){
        Double total;
        Double totalWithoutTaxes= calculeTotalWithoutTaxes();
        total = taxItems.stream().map(TaxItem::getValue).reduce(
                totalWithoutTaxes, (accumulator, _item) -> accumulator + (totalWithoutTaxes * _item)
        );
        setTotalWithTaxes(total);
        return total;
    }
    
    public Double getTotal(){
        total=  calculeTotalWithTaxes();
//        Double total=0.0;
        if(discount==null) return total;
        if(discount>0)
            total=getTotalWithTaxes()-(getTotalWithTaxes()*discount);
        return total;
    }
    
    public Double change(){        
        return (totalPayment!=null)?total - totalPayment:0d;
    }
    
    public Double totalTaxes(){
        
        if(getTotalWithTaxes()==null || getTotalWithTaxes()==0.0)  calculeTotalWithTaxes();
        if(getTotalWithTaxes()==null )  return 0.0;
        
        return getTotalWithTaxes()-getTotalWithoutTaxes();
        
    }
    
    public Double calculeTotalAdtionalExpensive(Double _total){
        return addtionalExpensesItems.stream()
                .map(AdditionalExpenseItem::total)
                .reduce(_total, Double::sum);
        
    }
    
    public Double calculateTotalItem(Double _total){       
        return items.stream().map((i) -> i.total()).reduce(
                _total, (accumulator, _item) -> accumulator + _item
        );
        
    }

    public void setTotal(Double total) {
        this.total = total;
    } 
    
    public void clear() {
        payments.clear();
        
        this.items.clear();
        this.addtionalExpensesItems.clear();
        this.taxItems.clear();
    }
    
    
    
    public void addAllPayment(List<Payment> p) {
        payments.addAll(p);
    }
    
    public void addPayment(Payment p) {
        payments.add(p);
    }
    
    public void removePayment(int item){
        payments.remove(item);
    }
    
    
    public void addTaxItems(TaxItem t) {
        taxItems.add(t);
    }
    
    public void removeTaxItem(int item){
        taxItems.remove(item);
    }
    
        
    public void addItem(InvoiceItem item){  
        items.add(item);
    }
    
    public void removeItem(int item){
        items.remove(item);
    }
    
    public void addAddtionalExpenseItem(AdditionalExpenseItem item){  
        addtionalExpensesItems.add(item);
    }
    
    public void removeAddtionalExpenseItem(int item){
        addtionalExpensesItems.remove(item);
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

    public Double getTotalWithoutTaxes() {
        return totalWithoutTaxes;
    }

    public void setTotalWithoutTaxes(Double totalWithoutTaxes) {
        this.totalWithoutTaxes = totalWithoutTaxes;
    }

    public Double getTotalWithTaxes() {
        return totalWithTaxes;
    }

    public void setTotalWithTaxes(Double totalWithTaxes) {
        this.totalWithTaxes = totalWithTaxes;
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

    public List<AdditionalExpenseItem> getAddtionalExpensesItems() {
        return addtionalExpensesItems;
    }

    public void setAddtionalExpensesItems(List<AdditionalExpenseItem> addtionalExpensesItems) {
        this.addtionalExpensesItems = addtionalExpensesItems;
    }

    public List<TaxItem> getTaxItems() {
        return taxItems;
    }

    public void setTaxItems(List<TaxItem> taxItems) {
        this.taxItems = taxItems;
    }

    public Subsidiary getSubsidiary() {
        return subsidiary;
    }

    public void setSubsidiary(Subsidiary subsidiary) {
        this.subsidiary = subsidiary;
    }
    
    public InvoiceStatus getStatus() {
        return status;
    }

    public void setStatus(InvoiceStatus status) {
        this.status = status;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public Double getTotalRefund() {
        return totalRefund;
    }

    public void setTotalRefund(Double totalRefund) {
        this.totalRefund = totalRefund;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public Boolean getHasTax() {
        return hasTax;
    }

    public void setHasTax(Boolean hasTax) {
        this.hasTax = hasTax;
    }

    public Boolean getHasNCF() {
        if(hasNCF== null) return false;
        return hasNCF;
    }

    public void setHasNCF(Boolean hasNCF) {
        this.hasNCF = hasNCF;
    }

    public Double getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(Double totalPayment) {
        this.totalPayment = totalPayment;
    }
    
}
