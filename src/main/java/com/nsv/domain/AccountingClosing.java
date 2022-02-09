/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nsv.domain;

import javax.persistence.*;
import java.time.Instant;

/**
 *
 * @author franm
 */
@Entity
@Table(name = "accounting_closing")
public class AccountingClosing extends AbstractBaseEntity{

//    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @NotEmpty
//    @Column(nullable = false)
    private Boolean close;
    private Instant closedDate;

    private Boolean open;
    private Instant openDate;


    public AccountingClosing() { }

    public static AccountingClosing doClose() {
        final var ac= new AccountingClosing();
        ac.close = true;
        ac.closedDate = Instant.now();
        return ac;
    }

    public static AccountingClosing doOpen() {
        final var ac= new AccountingClosing();
        ac.open = true;
        ac.openDate = Instant.now();
        return ac;
    }

    public Long getId() {
        return id;
    }

    public Boolean getClose() {
        return close;
    }

    public Boolean getOpen() {
        return open;
    }

    public Instant getClosedDate() {
        return closedDate;
    }

    public Instant getOpenDate() {
        return openDate;
    }
}
