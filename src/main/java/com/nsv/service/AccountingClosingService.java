package com.nsv.service;

import com.nsv.dao.AccountingClosingDao;
import com.nsv.domain.AccountingClosing;
import com.nsv.exception.NSVException;
import org.springframework.stereotype.Service;

/**
 * @author franm
 */
@Service
public class AccountingClosingService implements IAccountingClosingService {

    public static final String MSJ_ERROR = "No ser ha realizado apertura de caja";
    private final AccountingClosingDao closingDao;

    public AccountingClosingService(AccountingClosingDao closingDao) {
        this.closingDao = closingDao;
    }

    @Override
    public void doClose(AccountingClosing ac) throws NSVException {

        if(ac.getId() ==null)
            throw new NSVException(MSJ_ERROR);

        ac = closingDao.findById(ac.getId()).orElseThrow(() ->new NSVException(MSJ_ERROR));
        closingDao.save(AccountingClosing.doClose(ac));

    }

    @Override
    public void doOpen() {
        closingDao.save(AccountingClosing.doOpen());

    }
}
