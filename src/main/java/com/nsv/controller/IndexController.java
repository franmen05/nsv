/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nsv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.nsv.domain.Invoice;
import com.nsv.service.ICompanyService;
import com.nsv.service.IInvoiceService;

/**
 * 
 * @author you_k
 */
@Controller
@SessionAttributes({"customer","company"})
public class IndexController {
    
    @Autowired
    private IInvoiceService invoiceService;
    
    @Autowired
    private ICompanyService companyService;
    
    @RequestMapping(value={"/",""})
    public String page(Model model) {
//        Customer c=customerDao.findById(1L).get();
//        model.addAttribute("customer", c);
        model.addAttribute("attribute", "value");
//        model.addAttribute("companies", companyService.findAll());
        model.addAttribute("invoice", new Invoice());
//        model.addAttribute("currencies", invoiceService.findAllCurrencies());
//        model.addAttribute("paymentsTypes", invoiceService.findAllPaymentType());
//        model.addAttribute("product", new Product());
        
//        return "index";
        return "redirect:/home";
//        return "/login/login";
//        return "redirect:/customer/ver/1/";
//        return "redirect:/additionalexpense/";
//        return "redirect:/product/";
//        return "invoice/payment-invoice";
//        return "customer/index-customer";
    }
    
}
