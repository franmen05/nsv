package com.nsv.dao;

import com.nsv.domain.Currency;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author you_k
 */
@Repository
public interface ICurrencyDao extends PagingAndSortingRepository<Currency, Long> {
    
//    @Query("select t from Tax t where t.taxGroup.id=?1 ")
//    List<Tax> findByTaxGroup(Long term);
    
    
}
