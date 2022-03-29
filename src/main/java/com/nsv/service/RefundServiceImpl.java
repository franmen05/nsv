/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nsv.service;

import com.nsv.dao.RefundDao;
import com.nsv.domain.Refund;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author franm
 */
@Service
public class RefundServiceImpl implements IRefundService {

    private final RefundDao refundDao;

    public RefundServiceImpl(RefundDao refundDao) {
        this.refundDao = refundDao;
    }

//    @Autowired
//    private IProductDao productDao;
//
//    @Autowired
//    private IInvoiceDao invoiceDao;

    @Override
    public List<Refund> findAll() {
        return (List<Refund> )refundDao.findAll();
    }
    
//    @Override
//    public List<Company> findAllByStatus(GenericStatus status) {
//        return (List<Company> )companyDao.findAllByStatus(status);
//    }
//
//    @Override
//    public Page<Company> findAll(Pageable pageable) {
//        return companyDao.findAll(pageable);
//    }
//
//    @Override
//    public void save(Company customer) {
//        companyDao.save(customer);
//    }
//
//    @Override
//    public Company find(Long id) {
//        return companyDao.findById(id).get();
//    }
//
//    @Override
//    public void delete(Long id) {
//        companyDao.deleteById(id);
//    }

}
