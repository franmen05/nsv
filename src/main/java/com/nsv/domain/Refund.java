package com.nsv.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Refunds")
public class Refund extends AbstractBaseEntity {

    @Id
    private Long id;

    private Long invoiceId;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER,orphanRemoval = true)
    @JoinColumn(name = "refund_id")
    private List<RefundItem> items;

    public Refund(Long id) {
        this.id = id;
    }

    public Refund() {
        this.items= new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<RefundItem> getItems() {
        return items;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }
}
