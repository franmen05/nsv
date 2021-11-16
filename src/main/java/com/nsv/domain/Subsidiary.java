package com.nsv.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

/**
 *
 * @author you_k
 */ 
@Entity
@Table(name = "subsidiaries")
public class Subsidiary  extends AbstractBaseEntity {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty
    private String name;
    
//    @Email
    private String email;
    
    private String comment;
    
    private String address;
    
                    
    @Enumerated(EnumType.STRING)
    private GenericStatus status;

    
    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

    public Subsidiary() {    }

    
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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public GenericStatus getStatus() {
        return status;
    }

    public void setStatus(GenericStatus status) {
        this.status = status;
    }
    
}
