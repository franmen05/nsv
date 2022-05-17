package com.nsv.dao;

import com.nsv.domain.NCF;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author you_k
 */
@Repository
public interface INCFDao extends PagingAndSortingRepository<NCF, Long> {
    
    List<NCF> findBySequenceContainingIgnoreCase(String term);
    
    Optional<NCF> findFirstByInvoiceIsNull();
    
//    @Query("select u from NCF u where u.invoice.id =?1")
//    Optional<NCF> findByInvoice(Long id);
    
    
    
}
