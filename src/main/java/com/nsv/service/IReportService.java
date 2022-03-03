/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nsv.service;

import com.nsv.domain.report.DaySales;

import java.time.Instant;
import java.util.List;

/**
 *
 * @author franm
 */
public interface IReportService {

    List<DaySales> findAllDaySales();
    List<DaySales> findAllDaySalesByDate(Instant date);
}
