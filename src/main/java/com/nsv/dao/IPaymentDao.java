package com.nsv.dao;

import com.nsv.domain.Payment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author you_k
 */
@Repository
public interface IPaymentDao extends PagingAndSortingRepository<Payment, Long> {
    
//    @Query("select t from Tax t where t.taxGroup.id=?1 ")
//    List<Tax> findByTaxGroup(Long term);

    List<Payment> findAllByInvoiceId(Long id);
    List<Payment> findAllByAccountingClosing_Id(Long id);


    @Query("select sum(i.value) from Payment i where i.accountingClosing.id = :id")
    Double totalPaymentByAccountingClosing(Long id);

    
}
