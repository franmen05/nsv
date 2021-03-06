/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nsv.service;

import com.nsv.domain.Product;
import com.nsv.exception.NSVException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 *
 * @author you_k
 */
public interface IInventoryService {

    List<Product> findAllProduct();

    Page<Product> findAllProduct(Pageable pageable);
    
    void saveProduct(Product p) ;

    Product findProduct(Long id) throws NSVException;
    
    List<Product> findProductByName(String term) ;

    void deleteProduct(Long id);
}
