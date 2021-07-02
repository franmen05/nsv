package com.nsv.dao;

import com.nsv.domain.Product;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author you_k
 */
@Repository
public interface IProductDao extends PagingAndSortingRepository<Product, Long> {
    
    List<Product> findByNameContainingIgnoreCase(String term);
    
    
}
