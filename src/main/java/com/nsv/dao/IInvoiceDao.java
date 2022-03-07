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
import java.util.List;

/**
 *
 * @author you_k
 */
@Repository
public interface IInvoiceDao extends CrudRepository<Invoice, Long> {


    List<Invoice> findAllByClosedIsTrue();
//    @Query("select t from Invoice t where t.closed= true and DATE_FORMAT(t.closedDate,'%Y-%m-%d') =?1 ")
    List<Invoice> findAllByClosedIsTrueAndClosedDateBetween(Instant closedDate,Instant closedDate1);
    List<Invoice> findAllByClosedIsTrueAndClosedDate(Instant closedDate);
//    List<Invoice> findAllById(Long closedDate);

    
}
