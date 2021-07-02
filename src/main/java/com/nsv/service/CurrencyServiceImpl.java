package com.nsv.service;

import com.nsv.dao.ICurrencyDao;
import com.nsv.dao.IExchangeRateDao;
import com.nsv.domain.Currency;
import com.nsv.domain.ExchangeRate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CurrencyServiceImpl implements ICurrencyService {

    @Autowired
    private ICurrencyDao currencyDao;

    @Autowired
    private IExchangeRateDao exchangeRateDao;

    @Override
    public List<Currency> findAll() {
        return (List<Currency>) currencyDao.findAll();
    }

    @Override
    public Page<Currency> findAll(Pageable pageable) {
        return currencyDao.findAll(pageable);
    }

    @Override
    public void save(Currency entity) {
        currencyDao.save(entity);
    }

    @Override
    public Optional<Currency> find(Long id) {
        return currencyDao.findById(id);
    }

    @Override
    public void delete(Long id) {
        currencyDao.deleteById(id);
    }

    @Override
    public Iterable<ExchangeRate> findAllExchangeRate() {
        return exchangeRateDao.findAll();
    }

    @Override
    public Page<ExchangeRate> findAllExchangeRate(Pageable pageable) {
        return exchangeRateDao.findAll(pageable);
    }

    @Override
    public void saveExchangeRate(ExchangeRate entity) {
        exchangeRateDao.save(entity);
    }

    @Override
    public Optional<ExchangeRate> findExchangeRate(Long id) {
        return exchangeRateDao.findById(id);
    }

    @Override
    public void deleteExchangeRate(Long id) {
        exchangeRateDao.deleteById(id);
    }

}
