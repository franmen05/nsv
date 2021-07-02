/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nsv.service;

import com.nsv.dao.INCFDao;
import com.nsv.dao.INCFSerieDao;
import com.nsv.dao.INCFTypeDao;
import com.nsv.domain.Invoice;
import com.nsv.domain.NCF;
import com.nsv.domain.NCFSerie;
import com.nsv.domain.NCFType;
import com.nsv.exception.NSVException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class NCFServiceImpl implements INCFService {
    
    Log log= LogFactory.getLog(this.getClass());
    
    @Autowired
    private INCFDao ncfDao;
    @Autowired
    private INCFTypeDao typeDao;
    @Autowired
    private INCFSerieDao serieDao;

    @Override
    public List<NCF> findAll() {
        return (List<NCF>) ncfDao.findAll();
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
    public Optional<NCF> findByInovice(Invoice invoice) {
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
        
        System.out.println("####generateSequence()::"+ncf.toString());
        List<NCF> l= new  ArrayList<>();
        for(long i=ncf.getFrom();i<=ncf.getTo();i++){
            String sequence = String.format("%08d", i);
            try {
                System.out.println(""+i);
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
        return (List<NCFSerie>) serieDao.findAll();
    }

    @Override
    public void delete(Long id) {
        ncfDao.deleteById(id);
    }

    @Override
    public void addInvoice(Invoice invoice) throws NSVException {
        Optional<NCF> ncf = ncfDao.findFirstByInvoiceIsNull();
        
        if(ncf.isPresent()){
            NCF n=ncf.get();
            n.setInvoice(invoice);
            save(n);
        }else{
            throw new NSVException("No hay NCF disponibles ");
        }
    }
    
    

    
    
}
