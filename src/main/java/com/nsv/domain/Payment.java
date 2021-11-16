package com.nsv.domain;

import javax.persistence.*;

/**
 *
 * @author you_k
 */
@Entity
@Table(name = "payments")
public class Payment   extends AbstractBaseEntity{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String description;
    
    @Column(nullable = false)
    private Double value;

    @ManyToOne(fetch = FetchType.LAZY)
    private PaymentType paymentType;

    public Payment() { }

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

    public Double getValue() {
        return value;
    }

    public void setValue(Double price) {
        this.value = price;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }
    
    
    
}
