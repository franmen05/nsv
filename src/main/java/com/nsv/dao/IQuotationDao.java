package com.nsv.dao;

import com.nsv.domain.Company;
import com.nsv.domain.Quotation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author you_k
 */
@Repository
public interface IQuotationDao extends CrudRepository<Quotation, Long> {

    @Query("select u from Quotation u where u.customer.id =?1")
    List<Quotation> findByCustomer(Long term);
    
    @Query("select u from Quotation u where u.customer.id =?1 and u.company.id=?2")
    List<Quotation> findAllByCustomerAndCompany(Long customerID,Long companyId);

//    @Query("select u from Quotation u where u.customer.accountingClosingId =?1 and u.company.accountingClosingId=?2")
    List<Quotation> findAllByCompany(Company company);

}
