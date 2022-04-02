/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nsv.service;

import com.nsv.dao.*;
import com.nsv.domain.*;
import com.nsv.exception.NSVException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author you_k
 */
@Service
public class InvoiceServiceImpl implements IInvoiceService {

    private static final String REDIRECT_ = "redirect:/accountingclosing/";

    private final ICustomerDao customerDao;
    private final IProductDao productDao;
    private final ITaxDao taxDao;
    private final IAdditionalExpenseDao additionalExpenseDao;
    private final IInvoiceDao invoiceDao;
    private final IPaymentDao paymentDao;
    private final IPaymentTypeDao paymentTypeDao;
    private final ICurrencyDao currencyDao;
    private final AccountingClosingService accountingClosingService;

    public InvoiceServiceImpl(ICustomerDao customerDao, IProductDao productDao, ITaxDao taxDao,
                              IAdditionalExpenseDao additionalExpenseDao, IInvoiceDao invoiceDao,
                              IPaymentDao paymentDao, IPaymentTypeDao paymentTypeDao, ICurrencyDao currencyDao,
                              AccountingClosingService accountingClosingService) {
        this.customerDao = customerDao;
        this.productDao = productDao;
        this.taxDao = taxDao;
        this.additionalExpenseDao = additionalExpenseDao;
        this.invoiceDao = invoiceDao;
        this.paymentDao = paymentDao;
        this.paymentTypeDao = paymentTypeDao;
        this.currencyDao = currencyDao;
        this.accountingClosingService = accountingClosingService;
    }

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
    public Invoice saveInvoice(Invoice invoice) throws NSVException {

        accountingClosingService.getAccountOpen()
                .orElseThrow( () -> new NSVException(AccountingClosingService.MSJ_ERROR_IS_NOT_OPEN));

       return invoiceDao.save(invoice);
    }

    @Override
    public Invoice findInvoiceById(Long id) {
        return invoiceDao.findById(id).get();
    }

    @Override
    public Optional<Invoice> findInvoice(Long id, Customer cu) {
        return invoiceDao.findByIdAndCustomer_Id(id,cu.getId());
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
