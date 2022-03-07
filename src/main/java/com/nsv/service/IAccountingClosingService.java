/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nsv.service;

import com.nsv.domain.AccountingClosing;
import com.nsv.exception.NSVException;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author franm
 */
public interface IAccountingClosingService {

//    List<Currency> findAll();
//
//    Page<Currency> findAll(Pageable pageable);
//
//    void save(Currency customer);
//
//    Optional<Currency> find(Long accountingClosingId);
//
//    void delete(Long accountingClosingId);
//
//
//    Iterable<ExchangeRate> findAllExchangeRate();
//
//    Page<ExchangeRate> findAllExchangeRate(Pageable pageable);
//
//    void saveExchangeRate(ExchangeRate customer);
//
//    Optional<ExchangeRate> findExchangeRate(Long accountingClosingId);
//
//    void deleteExchangeRate(Long accountingClosingId);

    void doClose(AccountingClosing ac) throws NSVException;
    void doOpen() throws NSVException;

    Optional<AccountingClosing> getAccountOpen();
    List<AccountingClosing> findAll();
}
