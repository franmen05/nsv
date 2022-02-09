/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nsv.service;

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
//    Optional<Currency> find(Long id);
//
//    void delete(Long id);
//
//
//    Iterable<ExchangeRate> findAllExchangeRate();
//
//    Page<ExchangeRate> findAllExchangeRate(Pageable pageable);
//
//    void saveExchangeRate(ExchangeRate customer);
//
//    Optional<ExchangeRate> findExchangeRate(Long id);
//
//    void deleteExchangeRate(Long id);

    void doClose();
    void doOpen();
}
