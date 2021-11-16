/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nsv.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

/**
 *
 * @author you_k
 */
@Entity
@Table(name = "ncf")
public class NCF extends AbstractBaseEntity implements  Cloneable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Id
    @NotEmpty
    private String sequence;

    @ManyToOne(fetch = FetchType.LAZY)
    private NCFSerie serie;

    @ManyToOne(fetch = FetchType.LAZY)
    private NCFType type;

    @OneToOne(fetch = FetchType.LAZY)
    private Invoice invoice;

    @Transient
    private Long from;
    @Transient
    private Long to;

    public NCF() {
    }


    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public NCFSerie getSerie() {
        return serie;
    }

    public void setSerie(NCFSerie serie) {
        this.serie = serie;
    }

    public NCFType getType() {
        return type;
    }

    public void setType(NCFType type) {
        this.type = type;
    }

    public Long getFrom() {
        return from;
    }

    public void setFrom(Long from) {
        this.from = from;
    }

    public Long getTo() {
        return to;
    }

    public void setTo(Long to) {
        this.to = to;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public NCF clone() throws CloneNotSupportedException {
        return (NCF) super.clone();
    }

    @Override
    public String toString() {
        return "NCF{" + "sequence=" + sequence + ", createDate=" + getCreateDate() + ", lastUpdateDate=" + getLastUpdateDate() +
                ", serie=" + serie + ", type=" + type + ", invoice=" + invoice + ", from=" + from + ", to=" + to + '}';
    }

}
