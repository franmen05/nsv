/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nsv.service;

import com.nsv.domain.Company;
import com.nsv.domain.GenericStatus;
import com.nsv.domain.Invoice;
import com.nsv.domain.Product;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author you_k
 */
public interface ICompanyService {

    List<Company> findAll();

    Page<Company> findAll(Pageable pageable);
    List<Company> findAllByStatus(GenericStatus status);
    
    void save(Company customer);

    Company find(Long id);

    void delete(Long id);   
}
