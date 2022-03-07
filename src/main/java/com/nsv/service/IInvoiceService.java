/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nsv.service;

import com.nsv.domain.*;
import com.nsv.exception.NSVException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author you_k
 */
public interface IInvoiceService {    

    Invoice saveInvoice(Invoice invoice) throws NSVException;
    Invoice findInvoiceById(Long id);
    void deleteInvoice(Long id);

    List<Tax> findTaxesByTaxGroup(Long aLong);
    Double totalTaxesByTaxGroup(Long aLong);

    List<Payment> findPaymentByInvoice(Long  id);
    Iterable<PaymentType> findAllPaymentType();
    Optional<PaymentType> findPaymentType(Long id);

//
//    public Iterable<Currency> findAllCurrencies() ;
//    public Optional<Currency> findCurrency(Long id) ;

    List<AdditionalExpense> findAdditionalExpenseByName(String term) ;
    AdditionalExpense findAdditionalExpense(Long aLong);
    List<AdditionalExpense> findAllAdditionalExpenses();
    Page<AdditionalExpense> findAllAdditionalExpenses(Pageable pageable);
    void saveAdditionalExpense(AdditionalExpense additionalExpense);
}
