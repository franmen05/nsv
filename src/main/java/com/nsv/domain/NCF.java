/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nsv.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;

/**
 *
 * @author you_k
 */
@Entity
@Table(name = "ncf")
public class NCF implements Serializable, Cloneable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Id
    @NotEmpty
    private String sequence;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;
//
//    @Enumerated(EnumType.STRING)
//    private NCFStatus status;

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
//
//    public NCF(NCF ncf, String sequence) {
//        this.setSequence(sequence);
//        this.setSerie(serie);
//        setType(type);
//    }

    @PrePersist
    public void prePersist() {
        createDate = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        lastUpdateDate = new Date();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
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
        return "NCF{" + "sequence=" + sequence + ", createDate=" + createDate + ", lastUpdateDate=" + lastUpdateDate + ", serie=" + serie + ", type=" + type + ", invoice=" + invoice + ", from=" + from + ", to=" + to + '}';
    }

}
