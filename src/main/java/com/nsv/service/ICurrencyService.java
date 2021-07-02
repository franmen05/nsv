/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nsv.service;

import com.nsv.domain.Currency;
import com.nsv.domain.ExchangeRate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author you_k
 */
public interface ICurrencyService {

    List<Currency> findAll();

    Page<Currency> findAll(Pageable pageable);
    
    void save(Currency customer);

    Optional<Currency> find(Long id);

    void delete(Long id);
    
    
    Iterable<ExchangeRate> findAllExchangeRate();

    Page<ExchangeRate> findAllExchangeRate(Pageable pageable);
    
    void saveExchangeRate(ExchangeRate customer);

    Optional<ExchangeRate> findExchangeRate(Long id);

    void deleteExchangeRate(Long id);
}
