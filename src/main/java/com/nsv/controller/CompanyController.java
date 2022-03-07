package com.nsv.controller;

import com.nsv.domain.Company;
import com.nsv.domain.GenericStatus;
import com.nsv.service.ICompanyService;
import com.nsv.service.ICurrencyService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 *
 * @author you_k
 */
@Controller
@Secured( "ROLE_ADMIN")
@RequestMapping("/company")
@SessionAttributes({"companies"})
public class CompanyController {
    
    private static Log log = LogFactory.getLog(CompanyController.class);
    private static final String REDIRECT_COMPANY = "redirect:/company/";
    
    @Autowired
    private ICompanyService companyService;
    
    @Autowired
    private ICurrencyService currencyService;

    
    @RequestMapping(value={"/",""})
    public String home(Model model) {
        model.addAttribute("company", new Company());
        model.addAttribute("companies",companyService.findAllByStatus(GenericStatus.ACTIVE));
        model.addAttribute("allCompanies",companyService.findAll());
        model.addAttribute("currencies", currencyService.findAll());
        return "company/maint-company";
    }
    
//    public String getCompany(@PathVariable(name = "accountingClosingId") Long accountingClosingId,Model model) {
//        model.addAttribute("company", inventoryService.find(accountingClosingId));
//        model.addAttribute("companies",inventoryService.findAll());
//        return "company/maint-company";
//    }
//    
    @PostMapping("/add")
    public String save(@Valid Company company, BindingResult result, Model model,
            RedirectAttributes flash, SessionStatus status) {
        
        if (ControllerUtil.hasErrros(result, flash)) return REDIRECT_COMPANY;
        
        
        if(company.getStatus()==null)
            company.setStatus(GenericStatus.ACTIVE);
        
        companyService.save(company);
        status.setComplete();

        flash.addFlashAttribute("success", "Guardado con éxito!");
        return REDIRECT_COMPANY;
    }
    
    
    @PostMapping("/inactive")
    public String inactive(@Valid Company company, BindingResult result, Model model,
            RedirectAttributes flash, SessionStatus status) {

        if (ControllerUtil.hasErrros(result, flash)) return REDIRECT_COMPANY;
        
        company.setStatus(GenericStatus.INACTIVE);
        companyService.save(company);
//        status.setComplete();

        flash.addFlashAttribute("success", "El item fue inactivado con éxito!");

        return REDIRECT_COMPANY ;
    }
    
    @PostMapping("/reactive")
    public String reactive(@Valid Company company, BindingResult result, Model model,
            RedirectAttributes flash, SessionStatus status) {

        if (ControllerUtil.hasErrros(result, flash)) return REDIRECT_COMPANY;
        
        company.setStatus(GenericStatus.ACTIVE);
        companyService.save(company);
//        status.setComplete();

        flash.addFlashAttribute("success", "El item fue activado con éxito!");

        return REDIRECT_COMPANY ;
    }
    
//    private boolean hasErrros(BindingResult result, RedirectAttributes flash) {
//        if (result.hasErrors()) {
//            flash.addFlashAttribute("success", "Se encontraron errores, favor verificar !");
//            return true;
//        }
//        return false;
//    }
}
