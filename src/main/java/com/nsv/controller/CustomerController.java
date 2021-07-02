package com.nsv.controller;

import com.nsv.domain.Company;
import com.nsv.domain.Customer;
import com.nsv.domain.GenericStatus;
import com.nsv.domain.Invoice;
import com.nsv.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author you_k
 */
@Controller
@Secured( {"ROLE_ADMIN","ROLE_SALER","ROLE_OPERATIONS"})
@RequestMapping("/customer")
@SessionAttributes({"customers","customer","invoice","companies"})
public class CustomerController {
    
    private static final String REDIRECT_CUSTOMER = "redirect:/customer/";
        
    @Autowired
    private ICustomerService customerService;
    
    @RequestMapping(value={"/",""})
    public String page(Model model) {
        //Customer c=customerService.find(1L);
        model.addAttribute("customer", new Customer());
        model.addAttribute("customers", customerService.findAll());
        model.addAttribute("attribute", "value");
        
        return "customer/maint-customer";
    }
    
    @RequestMapping("/ver/{id}")
    public String page(@PathVariable(name = "id") Long id,
            @SessionAttribute(name = "company",required =false ) Company c,
            Model model) {
        
        //TODO: mejorar estos
        Customer customer=customerService.find(id);
        List<Invoice> invoices = customer.getInvoices().stream().filter( (t) ->  c.equals(t.getCompany()))
                                        .collect(Collectors.toList());
        
        customer.setInvoices(invoices);
        model.addAttribute("customer", customer);
        model.addAttribute("attribute", "value");
        
        return "customer/home-customer";
    }
    @ResponseBody
    @GetMapping("/find/{term}")
    public List<Customer> find(@PathVariable(name = "term") String id,
            @SessionAttribute(name = "customers",required = false)  List<Customer> customers,
            Model model) {
     
//        TODO: hacer que los customer se busquen en session primero 
//        if(customers!=null){
//            customers= customers.stream().filter(l-> l.getName().contains(id)).collect(Collectors.toList());
//        
//            if(!CollectionUtils.isEmpty(customers))
//                return customers;
//        }
        
        List<Customer> c=customerService.find(id);
        
        return c;
    }
    
    @PostMapping("/add")
    public String save(@Valid Customer c, BindingResult result, Model model,
            RedirectAttributes flash, SessionStatus status) {
        
        if (ControllerUtil.hasErrros(result, flash)) 
            return REDIRECT_CUSTOMER;
        
        if(c.getStatus()==null)
            c.setStatus(GenericStatus.ACTIVE);
        
        customerService.save(c);
        status.setComplete();

        flash.addFlashAttribute("success", "Guardado con éxito!");
        return REDIRECT_CUSTOMER;
    }
    
        
    @PostMapping("/inactive")
    public String inactive(@Valid Customer c, BindingResult result, Model model,
            RedirectAttributes flash, SessionStatus status) {

        if (ControllerUtil.hasErrros(result, flash)) return REDIRECT_CUSTOMER;
        
        c.setStatus(GenericStatus.INACTIVE);
        customerService.save(c);
//        status.setComplete();

        flash.addFlashAttribute("success", "El item fue inactivado con éxito!");

        return REDIRECT_CUSTOMER ;
    }
    
    @PostMapping("/reactive")
    public String reactive(@Valid Customer c, BindingResult result, Model model,
            RedirectAttributes flash, SessionStatus status) {

        if (ControllerUtil.hasErrros(result, flash)) return REDIRECT_CUSTOMER;
        
        c.setStatus(GenericStatus.ACTIVE);
        customerService.save(c);
//        status.setComplete();

        flash.addFlashAttribute("success", "El item fue activado con éxito!");

        return REDIRECT_CUSTOMER ;
    }
    
}
