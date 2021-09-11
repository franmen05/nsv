package com.nsv.service;

import com.nsv.dao.IQuotationDao;
import com.nsv.domain.Company;
import com.nsv.domain.Quotation;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class IQuotationServiceImpl implements IQuotationService {

    
    @Autowired
    private IQuotationDao quotationDao;
    
    @Override
    public Quotation save(Quotation q) {
        return quotationDao.save(q);
    }

    @Override
    public Optional<Quotation> findById(Long id) {
        return quotationDao.findById(id);
    }

    @Override
    public List<Quotation> findByCustomerId(Long id) {
       return quotationDao.findByCustomer(id);
    }
    
    @Override
    public List<Quotation> findAll(Long customerID,Company co) {
       return quotationDao.findAllByCustomerAndCompany(customerID, co.getId());
    }
    @Override
    public List<Quotation> findByCompany(Company co) {
       return quotationDao.findAllByCompany( co);
    }
    
    @Override
    public List<Quotation> findAll() {
     return (List<Quotation>) quotationDao.findAll();
    }
    

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
}
