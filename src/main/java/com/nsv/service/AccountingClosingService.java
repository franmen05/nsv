package com.nsv.service;

import com.nsv.dao.AccountingClosingDao;
import com.nsv.dao.IPaymentDao;
import com.nsv.domain.AccountingClosing;
import com.nsv.exception.NSVException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author franm
 */
@Service
public class AccountingClosingService implements IAccountingClosingService {

    public static final String MSJ_ERROR = "No se ha realizado cierre de caja";
    public static final String MSJ_ERROR_IS_OPENED = "Caja ya esta abierta";
    public static final String MSJ_ERROR_IS_NOT_OPEN = "Se no ha abierto la caja ";

    private final AccountingClosingDao closingDao;
    private final IPaymentDao paymentDao;

    public AccountingClosingService(AccountingClosingDao closingDao, IPaymentDao paymentDao) {
        this.closingDao = closingDao;
        this.paymentDao = paymentDao;
    }

    @Override
    public void doClose(AccountingClosing ac) throws NSVException {

        if(ac.getId() ==null)
            throw new NSVException(MSJ_ERROR);

        ac = closingDao.findById(ac.getId()).orElseThrow(() -> new NSVException(MSJ_ERROR));
        var totalSales = paymentDao.totalPaymentByAccountingClosing(ac.getId());
        closingDao.save(AccountingClosing.doClose(ac,totalSales));

    }

    @Override
    public void doOpen() throws NSVException {
        if(getAccountOpen().isPresent())
            throw new NSVException(MSJ_ERROR_IS_OPENED);

        closingDao.save(AccountingClosing.doOpen());

    }

    public Optional<AccountingClosing> getAccountOpen(){
        return closingDao.findByOpen(true);
    }

    @Override
    public List<AccountingClosing> findAll() {
        return (List<AccountingClosing>) closingDao.findAll();
    }
}
