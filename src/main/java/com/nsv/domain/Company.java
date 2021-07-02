package com.nsv.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "companies")
public class Company implements Serializable {

    private static final long serialVersionUID = 1L;
    
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
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;
    
    @OneToMany( fetch = FetchType.LAZY,mappedBy = "company",cascade = CascadeType.ALL)
    private List<Subsidiary> subsidiaries;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private Currency currency;      
    
    
//    @OneToMany( fetch = FetchType.LAZY,mappedBy = "company",cascade = CascadeType.ALL)
//    private List<User> users;
  

    public Company() {
        
    }
    
    @PrePersist
    public void prePersist(){
        setCreateDate(new Date());
    }
    
    
    @PreUpdate
    public void preUpdate(){
        lastUpdateDate= new Date(); 
    }
    
//    get and set 

    public Date getCreateDate() {
        return createDate;
    }

    private void setCreateDate(Date createDate) {
        this.createDate = createDate;
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

    
    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
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
    
//    public List<User> getUsers() {
//        return users;
//    }
//
//    public void setUsers(List<User> users) {
//        this.users = users;
//    }

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
        if (!Objects.equals(this.id, other.getId())) {
            return false;
        }
        return true;
    }
    
}
