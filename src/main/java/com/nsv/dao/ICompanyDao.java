package com.nsv.dao;

import com.nsv.domain.Company;
import com.nsv.domain.GenericStatus;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author you_k
 */
@Repository
public interface ICompanyDao extends PagingAndSortingRepository<Company, Long> {
    
    List<Company> findByNameContainingIgnoreCase(String term);
    
    Iterable<Company> findAllByStatus(GenericStatus status);
    
}
