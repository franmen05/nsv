/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nsv.dao;

import com.nsv.domain.Invoice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author you_k
 */
@Repository
public interface IInvoiceDao extends CrudRepository<Invoice, Long> {


    List<Invoice> findAllByClosedIsTrue();
    List<Invoice> findAllByPaymentsNotNullAndClosedIsNull();
//    @Query("select t from Invoice t where t.closed= true and DATE_FORMAT(t.closedDate,'%Y-%m-%d') =?1 ")
    List<Invoice> findAllByClosedIsTrueAndClosedDateBetween(Instant closedDate,Instant closedDate1);
    List<Invoice> findAllByClosedIsNullAndCreateDateBetween(Instant closedDate,Instant closedDate1);
    List<Invoice> findAllByClosedIsTrueAndClosedDate(Instant closedDate);

    /**
     * Note:  when the id has more than 1000 SQL return error.
     * @param id ID Invovice
     */
    List<Invoice> findAllByIdInAndClosedIsTrue(Collection<Long> id);

    /**
     * Note:  when the id has more than 1000 SQL return error.
     * @param id ID Invovice
     */
    List<Invoice> findAllByIdInAndClosedIsNull(Collection<Long> id);
//    List<Invoice> findAllById(Long closedDate);

    
}
