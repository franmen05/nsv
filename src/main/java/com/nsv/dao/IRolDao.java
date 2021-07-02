package com.nsv.dao;

import com.nsv.domain.Rol;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author you_k
 */
@Repository
public interface IRolDao extends PagingAndSortingRepository<Rol, String> {
    
    List<Rol> findByAuthority(String term);
    
}
