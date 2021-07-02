/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nsv.service;

import com.nsv.dao.ICompanyDao;
import com.nsv.domain.Company;
import com.nsv.domain.GenericStatus;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author you_k
 */
@Service
public class CompanyServiceImpl implements ICompanyService {

    @Autowired
    private ICompanyDao companyDao;

//    @Autowired
//    private IProductDao productDao;
//
//    @Autowired
//    private IInvoiceDao invoiceDao;

    @Override
    public List<Company> findAll() {
        return (List<Company> )companyDao.findAll();
    }
    
    @Override
    public List<Company> findAllByStatus(GenericStatus status) {
        return (List<Company> )companyDao.findAllByStatus(status);
    }

    @Override
    public Page<Company> findAll(Pageable pageable) {
        return companyDao.findAll(pageable);
    }

    @Override
    public void save(Company customer) {
        companyDao.save(customer);
    }

    @Override
    public Company find(Long id) {
        return companyDao.findById(id).get();
    }

    @Override
    public void delete(Long id) {
        companyDao.deleteById(id);
    }

}
