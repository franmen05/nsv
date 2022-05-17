package com.nsv.dao;

import com.nsv.domain.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author you_k
 */
@Repository
public interface IUserDao extends PagingAndSortingRepository<User, Long> {
    
//    List<User> findByNameContainingIgnoreCase(String term);
    User findByUsername(String term);
    
}
