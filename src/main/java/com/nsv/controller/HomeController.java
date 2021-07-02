package com.nsv.controller;

import com.nsv.domain.Company;
import com.nsv.domain.Customer;
import com.nsv.domain.GenericStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.nsv.service.ICompanyService;
import com.nsv.service.IInventoryService;
import com.nsv.service.IInvoiceService;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

/** 
 * 
 * @author you_k
 */
@Controller
@RequestMapping(value = {"/home"})
@SessionAttributes({"customer","company","companies","topProduct","topExpensive"})
//@SessionScope
public class HomeController {
    
    private static Log log = LogFactory.getLog(HomeController.class);    
    
    @Autowired
    private IInvoiceService invoiceService;
    
    
    @Autowired
    private IInventoryService inventoryService;
    
    
    @Autowired
    private ICompanyService companyService;
    
    private Company company;
    
    @GetMapping(value={"/",""})
    public String page(Model model) {
        
//        if(model.asMap().get("companies")==null)
//            model.addAttribute("company", new Company());

        model.addAttribute("companies", companyService.findAllByStatus(GenericStatus.ACTIVE));
        model.addAttribute("customer", new Customer());
        model.addAttribute("topProduct", inventoryService.findAllProduct( PageRequest.of(0, 4)));
        model.addAttribute("topExpensive", invoiceService.findAllAdditionalExpenses(PageRequest.of(0, 4)));
        
        return "home";
    }
    
    @GetMapping("/selectCompany")
    public String selectCompany(@RequestParam("company") String companyName, 
            @ModelAttribute("companies") List<Company> c,
            Model model) {

        c.stream().filter(t->t.getName().equals(companyName))
                .findFirst().ifPresent((c2) -> {
                    company= c2;
                    model.addAttribute("company", company);            
                });
        
        return "home";
    }       
}
