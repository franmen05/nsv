/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nsv.dao;

import com.nsv.domain.Customer;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author you_k
 */
@Repository
public interface ICustomerDao extends PagingAndSortingRepository<Customer, Long> {

//    public void findAll(Pageable pageable);
//    List<User> findByNameContainingIgnoreCase(String term);
    List<Customer> findByNameContainingIgnoreCase(String term);
    List<Customer> findByPassportContainingIgnoreCase(String term);
    List<Customer> findByRncContainingIgnoreCase(String term);    
    
}
