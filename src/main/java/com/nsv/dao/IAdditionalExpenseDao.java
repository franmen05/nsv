package com.nsv.dao;

import com.nsv.domain.AdditionalExpense;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author you_k
 */
@Repository
public interface IAdditionalExpenseDao extends PagingAndSortingRepository<AdditionalExpense, Long> {
    
    List<AdditionalExpense> findByNameContainingIgnoreCase(String term);
    
    
}
