package com.nsv.dao;

import com.nsv.domain.AccountingClosing;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author you_k
 */
@Repository
public interface AccountingClosingDao extends PagingAndSortingRepository<AccountingClosing, Long> {
    
//    @Query("select t from Tax t where t.taxGroup.id=?1 ")
//    List<Tax> findByTaxGroup(Long term);
    
    
}
