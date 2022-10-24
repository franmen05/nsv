package com.nsv.dao;

import com.nsv.domain.NCFSerie;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author you_k
 */
//@Repository
public interface INCFSerieDao extends PagingAndSortingRepository<NCFSerie, String> {
    
    
}
