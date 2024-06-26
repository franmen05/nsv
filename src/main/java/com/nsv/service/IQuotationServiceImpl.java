package com.nsv.service;

import com.nsv.dao.IQuotationDao;
import com.nsv.domain.Company;
import com.nsv.domain.Quotation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class IQuotationServiceImpl implements IQuotationService {

    
    private final IQuotationDao quotationDao;

    public IQuotationServiceImpl(IQuotationDao quotationDao) {
        this.quotationDao = quotationDao;
    }

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
       return quotationDao.findAllByCompanyAndCustomerIsNull( co);
    }
    
    @Override
    public List<Quotation> findAll() {
     return (List<Quotation>) quotationDao.findAll();
    }
    

    @Override
    public void delete(Long id) {
       quotationDao.deleteById(id);
    }
}
