package com.nsv.dao;

import com.nsv.domain.NCF;
import com.nsv.domain.NCFSerie;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author you_k
 */
@Repository
public interface INCFSerieDao extends PagingAndSortingRepository<NCFSerie, String> {
    
//    List<NCF> findByIdContainingIgnoreCase(String term);
    
    
}
