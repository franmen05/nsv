/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nsv.service;

import com.nsv.controller.dto.ReportSaleTypes;
import com.nsv.domain.Invoice;
import com.nsv.domain.Payment;
import com.nsv.domain.report.DaySales;

import java.time.Instant;
import java.util.List;

/**
 *
 * @author franm
 */
public interface IReportService {

    List<DaySales> findAllDaySales();
    List<DaySales> findAllPartialSales();
    List<DaySales> findAllDaySalesByDate(Instant date, ReportSaleTypes type);
    List<Payment> findAllPaymentByAccountClosing(Long id);
    Invoice findInvoiceById(Long id);
    List<DaySales> findAllByAccountingClosingId(Long accountingClosingId,ReportSaleTypes type);
}
