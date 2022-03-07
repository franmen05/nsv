package com.nsv.dao;

import com.nsv.domain.ExchangeRate;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author you_k
 */
@Repository
public interface IExchangeRateDao extends PagingAndSortingRepository<ExchangeRate, Long> {
    
//    @Query("select t from Tax t where t.taxGroup.accountingClosingId=?1 ")
//    List<Tax> findByTaxGroup(Long term);
    
    
}
