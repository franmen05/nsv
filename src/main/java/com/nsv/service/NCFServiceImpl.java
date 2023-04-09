/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nsv.service;

import com.nsv.dao.INCFDao;
import com.nsv.domain.Invoice;
import com.nsv.domain.NCF;
import com.nsv.domain.NCFSerie;
import com.nsv.domain.NCFType;
import com.nsv.exception.NSVException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NCFServiceImpl implements INCFService {
    
    final Log log= LogFactory.getLog(this.getClass());
    
    @Autowired
    private INCFDao ncfDao;
    @Autowired
    private CrudRepository<NCFType, String> typeDao;

    @Autowired
    private CrudRepository<NCFSerie, String> seriesDao2;

    @Override
    public List<NCF> findAll() {
        return (List<NCF>) ncfDao.findAll();
    }
    @Override
    public List<NCF> findAllUnused() {
        return ncfDao.findByInvoiceIsNull();
    }

    @Override
    public Page<NCF> findAll(Pageable pageable) {
        return ncfDao.findAll(pageable);
    }

    @Override
    public void save(NCF e) {
        ncfDao.save(e);
    }
    @Override
    public void save(List<NCF> e) {
        ncfDao.saveAll(e);
    }

    @Override
    public Optional<NCF> find(Long id) {
        return ncfDao.findById(id);
    }

    @Override
    public Optional<NCF> findByInvoice(Invoice invoice) {
        return ncfDao.findById(invoice.getId());
    }
    
    @Override
    public List<NCF> findByName(String term) {
        return ncfDao.findBySequenceContainingIgnoreCase(term);
    }

//    @Override
//    public void delete(Long id) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    @Override
    public List<NCF> generateSequence(NCF ncf) {
        log.info("####generateSequence()::" + ncf.toString());

        var l= new  ArrayList<NCF>();
        for(long i=ncf.getFrom();i<=ncf.getTo();i++){
            String sequence = String.format("%08d", i);
            try {
                log.debug(""+i);
                ncf.setSequence(sequence);
                l.add(ncf.clone());
//            l.add(ncf.getType().getId()+i);
            } catch (CloneNotSupportedException ex) {
                log.debug(" error  on NCF: "+ncf);
                log.error("can't create NCF: "+sequence,ex);
                
            }
        }
        return l;
    }

    @Override
    public List<NCFType> findAllTypes() {
        return (List<NCFType>) typeDao.findAll();
    }

    @Override
    public List<NCFSerie> findAllSeries() {
        return (List<NCFSerie>) seriesDao2.findAll();
    }

    @Override
    public void delete(Long id) {
        ncfDao.deleteById(id);
    }

    @Override
    public void addInvoice(Invoice invoice) throws NSVException {
        var ncf = ncfDao.findFirstByInvoiceIsNull();
        
        if(ncf.isPresent()){
            var n=ncf.get();
            n.setInvoice(invoice);
            save(n);
        }else{
            throw new NSVException("No hay NCF disponibles ");
        }
    }
    
    

    
    
}
