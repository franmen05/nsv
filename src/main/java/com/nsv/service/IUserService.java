/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nsv.service;

import com.nsv.domain.Rol;
import com.nsv.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 *
 * @author you_k
 */
public interface IUserService extends UserDetailsService {

    List<User> findAll();

    Page<User> findAll(Pageable pageable);
    
    void save(User customer);

    User find(Long id);

    void delete(Long id);   
//    boolean authenticate(String userName,String pass);

    List<Rol> findAllRoles();
}
