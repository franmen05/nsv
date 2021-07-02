/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nsv.service;

import com.nsv.domain.AdditionalExpense;
import com.nsv.domain.Currency;
import com.nsv.domain.Invoice;
import com.nsv.domain.Payment;
import com.nsv.domain.PaymentType;
import com.nsv.domain.Tax;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author you_k
 */
public interface IInvoiceService {    

    Invoice saveInvoice(Invoice invoice);

     Invoice findInvoiceById(Long id);

    void deleteInvoice(Long id);
    
    List<AdditionalExpense> findAdditonalExpenseByName(String term) ;

    public AdditionalExpense findAdditonalExpense(Long aLong);
    public List<Tax> findTaxesByTaxGroup(Long aLong);
    public List<Payment> findPaymentByInvoice(Long  id);
    public Iterable<PaymentType> findAllPaymentType();
    public Optional<PaymentType> findPaymentType(Long id);
    
//    
//    public Iterable<Currency> findAllCurrencies() ;
//    public Optional<Currency> findCurrency(Long id) ;

    public List<AdditionalExpense> findAllAdditionalExpenses();
    public Page<AdditionalExpense> findAllAdditionalExpenses(Pageable pageable);

    public void saveAdditionalExpense(AdditionalExpense additionalExpense);
}
