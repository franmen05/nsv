package com.nsv.service;

import com.nsv.dao.AccountingClosingDao;
import com.nsv.domain.AccountingClosing;
import org.springframework.stereotype.Service;

/**
 * @author franm
 */
@Service
public class AccountingClosingService implements IAccountingClosingService {

    private final AccountingClosingDao closingDao;

    public AccountingClosingService(AccountingClosingDao closingDao) {
        this.closingDao = closingDao;
    }

    @Override
    public void doClose() {
        closingDao.save(AccountingClosing.doClose());

    }

    @Override
    public void doOpen() {
        closingDao.save(AccountingClosing.doOpen());

    }
}
