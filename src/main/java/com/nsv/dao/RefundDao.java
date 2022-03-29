package com.nsv.dao;

import com.nsv.domain.Refund;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author franm
 */
@Repository
public interface RefundDao extends PagingAndSortingRepository<Refund, Long> {
    
//    List<Company> findByNameContainingIgnoreCase(String term);
//    Iterable<Company> findAllByStatus(GenericStatus status);
    
}
