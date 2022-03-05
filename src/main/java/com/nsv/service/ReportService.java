package com.nsv.service;

import com.nsv.dao.AccountingClosingDao;
import com.nsv.dao.IInvoiceDao;
import com.nsv.dao.IPaymentDao;
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
public class ReportService implements IReportService {



    private final AccountingClosingDao closingDao;
    private final IInvoiceDao invoiceDao;
    private final IPaymentDao paymentDao;


    public ReportService(AccountingClosingDao closingDao, IInvoiceDao invoiceDao, IPaymentDao paymentDao) {
        this.closingDao = closingDao;
        this.invoiceDao = invoiceDao;
        this.paymentDao = paymentDao;
    }

    @Override
    public List<DaySales> findAllDaySales() {
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

    public List<DaySales> findAllDaySalesByDate(Instant date) {
        var invoice = invoiceDao.findAllByClosedIsTrueAndClosedDateBetween(date,date.plus(86_399 , ChronoUnit.SECONDS));
        return invoice.stream()
                .map(i -> new DaySales(i.getId(),i.getClosedDate(),i.getTotal(),i.calculeTotalWithoutTaxes(),i.getTotalRefund()))
                .collect(Collectors.toList());

    }
    public List<Payment> findAllPaymentByAccountClosing(Long id) {

        return paymentDao.findAllByInvoiceId(id);

    }
}
