/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nsv.service;

import com.nsv.domain.Refund;

import java.util.List;

/**
 *
 * @author franm
 */
public interface IRefundService {

    List<Refund> findAll();
//    Page<Company> findAll(Pageable pageable);
//    List<Company> findAllByStatus(GenericStatus status);
//
//    void save(Company customer);
//
//    Company find(Long id);
//
//    void delete(Long id);
}
