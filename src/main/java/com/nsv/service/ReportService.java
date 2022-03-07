package com.nsv.service;

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
public class ReportService implements IReportService {



    private final AccountingClosingDao closingDao;
    private final IInvoiceDao invoiceDao;
    private final IPaymentDao paymentDao;
    private final IInvoiceService invoiceService;


    public ReportService(AccountingClosingDao closingDao, IInvoiceDao invoiceDao, IPaymentDao paymentDao,
                         IInvoiceService invoiceService) {
        this.closingDao = closingDao;
        this.invoiceDao = invoiceDao;
        this.paymentDao = paymentDao;
        this.invoiceService = invoiceService;
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

    @Override
    public Invoice findInvoiceById(Long id) {
        return invoiceService.findInvoiceById(id);
    }

    @Override
    public List<DaySales> findAllByAccountingClosingId(Long accountingClosingId) {
        var payments=paymentDao.findAllByAccountingClosing_Id(accountingClosingId);
        var invoiceIds=payments.stream().map(Payment::getInvoiceId).collect(Collectors.toSet());

        var invoices= (List<Invoice>)invoiceDao.findAllById(invoiceIds);


//        var account=i.getPayments().stream().map(p -> p.getAccountingClosing().getId()).collect(Collectors.toSet());
////        List<AccountingClosing> c= (List<AccountingClosing>) closingDao.findAllById(account);
//        c.stream().map(ac -> new DaySales(ac.getId(),ac.getClosedDate(),ac.))
        return invoices.stream().map( i -> new DaySales(i.getId(),i.getClosedDate(),i.getTotal(),i.calculeTotalWithoutTaxes(),i.getTotalRefund())).collect(Collectors.toList());
    }
}
