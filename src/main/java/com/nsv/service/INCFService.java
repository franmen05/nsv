package com.nsv.service;

import com.nsv.domain.Invoice;
import com.nsv.domain.NCF;
import com.nsv.domain.NCFSerie;
import com.nsv.domain.NCFType;
import com.nsv.exception.NSVException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author you_k
 */
public interface INCFService {

    List<NCF> findAll();
    List<NCF> findAllUnused();

    Page<NCF> findAll(Pageable pageable);
    
    void save(NCF p) ;
    void save(List<NCF> p) ;
    

    Optional<NCF> find(Long id);
    Optional<NCF> findByInvoice(Invoice invoice);
    void delete(Long id);
    
    List<NCF> findByName(String term) ;

//    void delete(Long id);
    List<NCF> generateSequence(NCF ncf) ;

    List<NCFType> findAllTypes();
    List<NCFSerie> findAllSeries();
    
    void addInvoice(Invoice invoice) throws NSVException;
    
    
}
