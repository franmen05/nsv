package com.nsv.dao;

import com.nsv.domain.NCFType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author you_k
 */
@Repository
public interface INCFTypeDao extends CrudRepository<NCFType, String> {
    
//    List<NCF> findByIdContainingIgnoreCase(String term);
    
    
}
