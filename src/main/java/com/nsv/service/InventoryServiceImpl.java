/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nsv.service;

import com.nsv.dao.ICustomerDao;
import com.nsv.dao.IProductDao;
import com.nsv.domain.Product;
import com.nsv.exception.NSVException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author you_k
 */
@Service
public class InventoryServiceImpl implements IInventoryService {

    @Autowired
    private ICustomerDao customerDao;

    @Autowired
    private IProductDao productDao;
    
    
    
    @Override
    public List<Product> findAllProduct() {
        return (List<Product> )productDao.findAll();
    }

    @Override
    public Page<Product> findAllProduct(Pageable pageable) {
        return productDao.findAll(pageable);
    }

    @Override
    public void saveProduct(Product product) {
        productDao.findById(product.getId()).ifPresentOrElse(p -> {
            p.setStatus(product.getStatus());
            p.setPrice(product.getPrice());
            p.setName(product.getName());
            p.setDescription(product.getDescription());
            productDao.save(p);

        },() ->productDao.save(product));

    }

    @Override
    public Product findProduct(Long id) throws NSVException {
        return productDao.findById(id).orElseThrow( () -> new NSVException("Producto no fue encontrado"));
    }

    @Override
    public void deleteProduct(Long id) {
        customerDao.deleteById(id);
    }

    @Override
    public List<Product> findProductByName(String term) {
        return productDao.findByNameContainingIgnoreCase(term);
    }
    
    

}
