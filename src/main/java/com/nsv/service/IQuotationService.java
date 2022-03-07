/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nsv.service;

import com.nsv.domain.Company;
import com.nsv.domain.Quotation;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author you_k
 */
public interface IQuotationService {    

    Quotation save(Quotation q);

    Optional<Quotation> findById(Long id);
    List<Quotation> findAll();
    List<Quotation> findByCustomerId(Long id);
    List<Quotation> findAll(Long customerID,Company co);
    List<Quotation> findByCompany(Company co);

    void delete(Long id);
//    
//    List<AdditionalExpense> findAdditonalExpenseByName(String term) ;
//
//    public AdditionalExpense findAdditonalExpense(Long aLong);
//    public List<Tax> findTaxesByTaxGroup(Long aLong);
//    public List<Payment> findPaymentByInvoice(Long  accountingClosingId);
//    public Iterable<PaymentType> findAllPaymentType();
//    public Optional<PaymentType> findPaymentType(Long accountingClosingId);
//    
//    
//    public Iterable<Currency> findAllCurrencies() ;
//    public Optional<Currency> findCurrency(Long accountingClosingId) ;
//
//    public List<AdditionalExpense> findAllAdditionalExpenses();
//
//    public void saveAdditionalExpense(AdditionalExpense additionalExpense);
}
