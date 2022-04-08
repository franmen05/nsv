/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nsv.service;

import com.nsv.dao.ICustomerDao;
import com.nsv.dao.IInvoiceDao;
import com.nsv.dao.IProductDao;
import com.nsv.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author you_k
 */
@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private ICustomerDao customerDao;

    @Autowired
    private IProductDao productDao;

    @Autowired
    private IInvoiceDao invoiceDao;

    @Override
    public List<Customer> findAll() {
        return (List<Customer> )customerDao.findAll();
    }

    @Override
    public Page<Customer> findAll(Pageable pageable) {
        return customerDao.findAll(pageable);
    }

    @Override
    public void save(Customer customer) {
        customerDao.save(customer);
    }
    
    @Override
    public Customer find(Long id) {
        
        Optional<Customer> c = customerDao.findById(id);
        return c.orElse(null);
    }

    @Override
    public List<Customer> find(String term) {
        
        List<Customer> customers;
        try{
            Customer c =find(Long.parseLong(term));
            if(c!=null){
                customers = new ArrayList<>();
                customers.add(c);
                return customers;
            }
        
        }catch(Exception ex){ }
        
        customers = customerDao.findByNameContainingIgnoreCase(term);
        if(!customers.isEmpty())
            return customers;
        
        customers = customerDao.findByPassportContainingIgnoreCase(term);
        if(!customers.isEmpty())
            return customers;
        
        customers = customerDao.findByRncContainingIgnoreCase(term);
        if(!customers.isEmpty())
            return customers;
        
        return null;
    }

    @Override
    public void delete(Long id) {
        customerDao.deleteById(id);
    }

}
