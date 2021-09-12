/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nsv.service;

import com.nsv.dao.IAdditionalExpenseDao;
import com.nsv.dao.ICurrencyDao;
import com.nsv.dao.ICustomerDao;
import com.nsv.dao.IInvoiceDao;
import com.nsv.dao.IPaymentDao;
import com.nsv.dao.IPaymentTypeDao;
import com.nsv.dao.IProductDao;
import com.nsv.dao.ITaxDao;
import com.nsv.domain.AdditionalExpense;
import com.nsv.domain.Invoice;
import com.nsv.domain.Payment;
import com.nsv.domain.PaymentType;
import com.nsv.domain.Tax;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author you_k
 */
@Service
public class InvoiceServiceImpl implements IInvoiceService {

    @Autowired 
    private ICustomerDao customerDao;

    @Autowired
    private IProductDao productDao;
    
    @Autowired
    private ITaxDao taxDao;
    
    @Autowired
    private IAdditionalExpenseDao additionalExpenseDao;

    @Autowired
    private IInvoiceDao invoiceDao;

    @Autowired
    private IPaymentDao paymentDao;

    @Autowired
    private IPaymentTypeDao paymentTypeDao;

    @Autowired
    private ICurrencyDao currencyDao;

    @Override
    public List<Tax> findTaxesByTaxGroup(Long id) {
      return taxDao.findByTaxGroup(id);
    }
    @Override
    public Double totalTaxesByTaxGroup(Long id) {
        return taxDao.findByTaxGroup(id)
                .stream()
                .reduce((tax, tax2) -> {
                      tax.setValue(tax.getValue()+tax2.getValue());
                      return tax;
                  })
                .orElse(new Tax())
                .getValue();
    }
    
    @Override
    public Invoice saveInvoice(Invoice invoice) {
       return invoiceDao.save(invoice);
    }

    @Override
    public Invoice findInvoiceById(Long id) {
        return invoiceDao.findById(id).get();
    }

    @Override
    public void deleteInvoice(Long id) {
        invoiceDao.deleteById(id);
    }

    @Override
    public List<AdditionalExpense> findAdditionalExpenseByName(String term) {
        return additionalExpenseDao.findByNameContainingIgnoreCase(term);
    }

    @Override
    public AdditionalExpense findAdditionalExpense(Long aLong) {
        return additionalExpenseDao.findById(aLong).get();
    }

    @Override
    public List<Payment> findPaymentByInvoice(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterable<PaymentType> findAllPaymentType() {
        return paymentTypeDao.findAll();
    }

    @Override
    public Optional<PaymentType> findPaymentType(Long id) {
        return paymentTypeDao.findById(id);
    }
    
//    @Override
//    public Iterable<Currency> findAllCurrencies() {
//        return currencyDao.findAll();
//    }
//    
//    @Override
//    public Optional<Currency> findCurrency(Long id) {
//        return currencyDao.findById(id);
//    }

    @Override
    public List<AdditionalExpense> findAllAdditionalExpenses() {
        return (List<AdditionalExpense>) additionalExpenseDao.findAll();
    }

    @Override
    public Page<AdditionalExpense> findAllAdditionalExpenses(Pageable pageable) {
           return  additionalExpenseDao.findAll(pageable);
    }
    
    

    @Override
    public void saveAdditionalExpense(AdditionalExpense additionalExpense) {
        additionalExpenseDao.save(additionalExpense);
    }
    
}
