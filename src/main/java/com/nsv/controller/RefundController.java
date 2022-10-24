package com.nsv.controller;

import com.nsv.domain.Company;
import com.nsv.domain.GenericStatus;
import com.nsv.domain.Refund;
import com.nsv.service.ICompanyService;
import com.nsv.service.ICustomerService;
import com.nsv.service.IRefundService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;

/**
 *
 * @author you_k
 */
@Controller
@Secured( "ROLE_ADMIN")
@RequestMapping("/refund")
@SessionAttributes({"companies"})
public class RefundController {
    
    private static final Log log = LogFactory.getLog(RefundController.class);
    private static final String REDIRECT = "redirect:/refund/";
    
    private final ICompanyService companyService;
    private final IRefundService refundService;
    private final ICustomerService customerService;

    public RefundController(ICompanyService companyService, IRefundService refundService, ICustomerService customerService) {
        this.companyService = companyService;
        this.refundService = refundService;
        this.customerService = customerService;
    }


    @RequestMapping(value={"/{customerId}",""})
    public String home(@PathVariable(name = "customerId") Long cId, Model model) {
//        model.addAttribute("company", new Company());
        model.addAttribute("refund", new Refund());
        model.addAttribute("customer",customerService.find(cId));
        var items =new ArrayList<Refund>();

        model.addAttribute("partsAtribute", items);
//        model.addAttribute("companies",companyService.findAllByStatus(GenericStatus.ACTIVE));
//        model.addAttribute("allCompanies",companyService.findAll());
//        model.addAttribute("currencies", currencyService.findAll());
        return "refund/refund";
    }

//    @ResponseBody
//    @GetMapping(value={"/id",""})
//    public List<Refund> home(Model model) {
////        model.addAttribute("company", new Company());
////        model.addAttribute("companies",companyService.findAllByStatus(GenericStatus.ACTIVE));
////        model.addAttribute("allCompanies",companyService.findAll());
////        model.addAttribute("currencies", currencyService.findAll());
//        return refundService.findAll();
//    }

//    public String getCompany(@PathVariable(name = "id") Long id,Model model) {
//        model.addAttribute("company", inventoryService.find(id));
//        model.addAttribute("companies",inventoryService.findAll());
//        return "company/maint-company";
//    }
//    
    @PostMapping("/add")
    public String save(@Valid Company company, BindingResult result, Model model,
            RedirectAttributes flash, SessionStatus status) {
        
        if (ControllerUtil.hasErrors(result, flash)) return REDIRECT;
        
        
        if(company.getStatus()==null)
            company.setStatus(GenericStatus.ACTIVE);
        
        companyService.save(company);
        status.setComplete();

        flash.addFlashAttribute("success", "Guardado con éxito!");
        return REDIRECT;
    }
    
//
//    @PostMapping("/inactive")
//    public String inactive(@Valid Company company, BindingResult result, Model model,
//            RedirectAttributes flash, SessionStatus status) {
//
//        if (ControllerUtil.hasErrros(result, flash)) return REDIRECT_COMPANY;
//
//        company.setStatus(GenericStatus.INACTIVE);
//        companyService.save(company);
////        status.setComplete();
//
//        flash.addFlashAttribute("success", "El item fue inactivado con éxito!");
//
//        return REDIRECT_COMPANY ;
//    }
//
//    @PostMapping("/reactive")
//    public String reactive(@Valid Company company, BindingResult result, Model model,
//            RedirectAttributes flash, SessionStatus status) {
//
//        if (ControllerUtil.hasErrros(result, flash)) return REDIRECT_COMPANY;
//
//        company.setStatus(GenericStatus.ACTIVE);
//        companyService.save(company);
////        status.setComplete();
//
//        flash.addFlashAttribute("success", "El item fue activado con éxito!");
//
//        return REDIRECT_COMPANY ;
//    }
}
