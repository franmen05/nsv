package com.nsv.domain;

import javax.persistence.*;

@Entity
@Table(name = "Refund_items")
public class RefundItem extends AbstractBaseEntity {

    @Id
    private   Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private  InvoiceItem invoiceItem;


    public static RefundItem build(Long id, InvoiceItem invoiceItem) {
        var r= new  RefundItem();

        r.id = id;
        r.invoiceItem = invoiceItem;

        return r;
    }

    public Long getId() {
        return id;
    }

    public InvoiceItem getInvoiceItem() {
        return invoiceItem;
    }

}
