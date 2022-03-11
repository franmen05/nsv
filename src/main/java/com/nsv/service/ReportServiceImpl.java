package com.nsv.service;

import com.nsv.controller.dto.ReportSaleTypes;
import com.nsv.dao.AccountingClosingDao;
import com.nsv.dao.IInvoiceDao;
import com.nsv.dao.IPaymentDao;
import com.nsv.domain.Invoice;
import com.nsv.domain.Payment;
import com.nsv.domain.report.DaySales;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author franm
 */
@Service
public class ReportServiceImpl implements IReportService {


    public static final int SECONDS_OF_DAY = 86_399;

    private final AccountingClosingDao closingDao;
    private final IInvoiceDao invoiceDao;
    private final IPaymentDao paymentDao;
    private final IInvoiceService invoiceService;


    public ReportServiceImpl(AccountingClosingDao closingDao, IInvoiceDao invoiceDao, IPaymentDao paymentDao,
                             IInvoiceService invoiceService) {
        this.closingDao = closingDao;
        this.invoiceDao = invoiceDao;
        this.paymentDao = paymentDao;
        this.invoiceService = invoiceService;
    }

    @Override
    public List<DaySales> findAllSales(ReportSaleTypes type) {
        return switch (type) {
            case TODAY_SALES -> findAllDaySales();
            case PARTIAL_SALES -> findAllPartialSales();
        };
    }

    private List<DaySales> findAllPartialSales() {
         var invoice = invoiceDao.findAllByPaymentsNotNullAndClosedIsNull();
         return invoice.stream()
                 .map(i -> new DaySales(i.getId(),i.getClosedDate(),i.getTotal(),i.calculeTotalWithoutTaxes(),i.getTotalRefund()    ))
                 .collect(Collectors.toList());
    }

    private List<DaySales> findAllDaySales() {
         var invoice = invoiceDao.findAllByClosedIsTrue();
         return invoice.stream()
                 .map(i -> new DaySales(i.getId(),i.getClosedDate(),i.getTotal(),i.calculeTotalWithoutTaxes(),i.getTotalRefund()    ))
                 .collect(Collectors.toList());
    }


//    @Override
    public List<DaySales> findAllDaySales1() {

         var invoice = invoiceDao.findAllByClosedIsTrueAndClosedDateBetween(Instant.now().minus(20, ChronoUnit.DAYS),Instant.now());
         return invoice.stream()
                 .map(i -> new DaySales(i.getId(),i.getClosedDate(),i.getTotal(),i.calculeTotalWithoutTaxes(),i.getTotalRefund()))
                 .collect(Collectors.toList());
    }

    public List<DaySales> findAllDaySalesByDate(Instant date, ReportSaleTypes type) {
        List<Invoice> invoices = null;
        switch (type){
            case TODAY_SALES -> invoices= invoiceDao.findAllByClosedIsTrueAndClosedDateBetween(date,date.plus(SECONDS_OF_DAY , ChronoUnit.SECONDS));
            case PARTIAL_SALES -> invoices=  invoiceDao.findAllByClosedIsNullAndCreateDateBetween(date,date.plus(SECONDS_OF_DAY, ChronoUnit.SECONDS));
        }

        return invoices.stream()
                .map(i -> new DaySales(i.getId(),i.getClosedDate(),i.getTotal(),i.calculeTotalWithoutTaxes(),i.getTotalRefund()))
                .collect(Collectors.toList());

    }
    public List<Payment> findAllPaymentByAccountClosing(Long id) {

        return paymentDao.findAllByInvoiceId(id);
    }

    @Override
    public Invoice findInvoiceById(Long id) {
        return invoiceService.findInvoiceById(id);
    }

    @Override
    public List<DaySales> findAllByAccountingClosingId(Long accountingClosingId,ReportSaleTypes type) {

//        final Set<Long> invoiceIds;
        List<Invoice> invoices = null;

        var payments=paymentDao.findAllByAccountingClosing_Id(accountingClosingId);
        var invoiceIds=payments.stream().map(Payment::getInvoiceId).collect(Collectors.toSet());
        switch (type){
            case TODAY_SALES -> invoices= invoiceDao.findAllByIdInAndClosedIsTrue(invoiceIds);
            case PARTIAL_SALES -> invoices=  invoiceDao.findAllByIdInAndClosedIsNull(invoiceIds);
        }




//        var account=i.getPayments().stream().map(p -> p.getAccountingClosing().getId()).collect(Collectors.toSet());
////        List<AccountingClosing> c= (List<AccountingClosing>) closingDao.findAllById(account);
//        c.stream().map(ac -> new DaySales(ac.getId(),ac.getClosedDate(),ac.))
        return invoices.stream().map( i -> new DaySales(i.getId(),i.getClosedDate(),i.getTotal(),i.calculeTotalWithoutTaxes(),i.getTotalRefund()))
                .collect(Collectors.toList());
    }
}
