package com.nsv.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author you_k
 */ 
@Entity
@Table(name = "companies")
public class Company  extends AbstractBaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty
    private String name;
    
//    @Email
    private String email;
    private String RNC;
    
    private String comment;
    
    private String address;
    
    @Enumerated(EnumType.STRING)
    private GenericStatus status;

//    @DateTimeFormat(pattern = "yyy-mm-ddd")
    @OneToMany( fetch = FetchType.LAZY,mappedBy = "company",cascade = CascadeType.ALL)
    private List<Subsidiary> subsidiaries;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private Currency currency;      
    
    
//    @OneToMany( fetch = FetchType.LAZY,mappedBy = "company",cascade = CascadeType.ALL)
//    private List<User> users;
  

    public Company() {
        
    }
    

    
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


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Subsidiary> getSubsidiaries() {
        return subsidiaries;
    }

    public void setSubsidiaries(List<Subsidiary> subsidiaries) {
        this.subsidiaries = subsidiaries;
    }

    public String getRNC() {
        return RNC;
    }

    public void setRNC(String RNC) {
        this.RNC = RNC;
    }

    public GenericStatus getStatus() {
        return status;
    }

    public void setStatus(GenericStatus status) {
        this.status = status;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
//        System.out.println("com.nsv.domain.Company.equals()");
//        if (getClass() != obj.getClass()) {
//            return false;
//        }
        final Company other = (Company) obj;
        System.out.println("com.nsv.domain.Company.equals() :: "+other.getId());
        return Objects.equals(this.id, other.getId());
    }
    
}
