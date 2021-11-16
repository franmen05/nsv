package com.nsv.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 *
 * @author you_k
 */
@Entity
@Table(name = "customers")
public class Customer  extends AbstractBaseEntity {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty
    private String name;
    
//    @NotEmpty
    private String lastName;
    
//    @Email
    private String email;
    /**
     * document National Identification
     */
    private String passport;
    private String rnc;
    
    private String comment;
    private String phone;
    private String phone2;
    private String address;
    
    @Enumerated(EnumType.STRING)
    private GenericStatus status;

    
    //TODO: Analizar si estas  lista  de  factura se debe mantener en  aqui
//    @JsonManagedReference
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JsonBackReference
    @OneToMany( fetch = FetchType.LAZY,mappedBy = "customer",cascade = CascadeType.ALL)
    private List<Invoice> invoices;

    public Customer() {
//        invoices = new ArrayList<>();
    }
    
    public void addInvoice(Invoice invoice){
        invoices.add(invoice);
    }
    
    public void removeInvoice(Invoice invoice){
        invoices.remove(invoice);
    }
    
//    get and set
    
    
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public GenericStatus getStatus() {
        return status;
    }

    public void setStatus(GenericStatus status) {
        this.status = status;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRnc() {
        return rnc;
    }

    public void setRnc(String rnc) {
        this.rnc = rnc;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }
    
}
