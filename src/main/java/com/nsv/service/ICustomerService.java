/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nsv.service;

import com.nsv.domain.Customer;
import com.nsv.domain.Invoice;
import com.nsv.domain.Product;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author you_k
 */
public interface ICustomerService {

    List<Customer> findAll();

    Page<Customer> findAll(Pageable pageable);
    
    void save(Customer customer);

    List<Customer> find(String term);
    Customer find(Long term);

    void delete(Long id);
}
