/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nsv.service;

import com.nsv.dao.IRolDao;
import com.nsv.dao.IUserDao;
import com.nsv.domain.Rol;
import com.nsv.domain.User;
import com.nsv.domain.UserRol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author you_k
 */
@Service
//@Service("UserService")
public class UserServiceImpl implements IUserService {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IUserDao userDao;

    @Autowired
    private IRolDao rolDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

//    @Autowired
//    private IProductDao productDao;
//
//    @Autowired
//    private IInvoiceDao invoiceDao;

    @Override
    public List<User> findAll() {
        return (List<User> )userDao.findAll();
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userDao.findAll(pageable);
    }

    @Override
    public void save(User user) {

        String encode = passwordEncoder.encode(user.getPassword());
//        if(passwordEncoder.matches())

        user.setPassword(encode);
        userDao.save(user);
    }

    @Override
    public User find(Long id) {
        return userDao.findById(id).get();
    }

    @Override
    public void delete(Long id) {
        userDao.deleteById(id);
    }

    @Override
    public List<Rol> findAllRoles() {
        return (List<Rol>) rolDao.findAll();
    }

    //    @Override
//    public boolean authenticate(String userName, String pass) {
//        User user = userDao.findByUsername(userName.trim());
//        if(user!=null){
//            return user.getPassword().equals(pass);
//        }
//
//        return false;
//    }

    @Override
    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userDao.findByUsername(username);

        if(user == null) {
            log.error("Error en el Login: no existe el usuario '" + username + "' en el sistema!");
            throw new UsernameNotFoundException("Username: " + username + " no existe en el sistema!");
        }

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        for(UserRol role: user.getRoles()) {
            log.info("Role: ".concat(role.getRol().getAuthority()));
            authorities.add(new SimpleGrantedAuthority(role.getRol().getAuthority()));
        }

        if(authorities.isEmpty()) {
            log.error("Error en el Login: Usuario '" + username + "' no tiene roles asignados!");
            throw new UsernameNotFoundException("Error en el Login: usuario '" + username + "' no tiene roles asignados!");
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getEnabled(), true, true, true, authorities);
    }

}
