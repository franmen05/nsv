package com.nsv.dao;

import com.nsv.domain.Tax;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author you_k
 */
@Repository
public interface ITaxDao extends PagingAndSortingRepository<Tax, Long> {
    
    @Query("select t from Tax t where t.taxGroup.id=?1 ")
    List<Tax> findByTaxGroup(Long term);
    
    
}
