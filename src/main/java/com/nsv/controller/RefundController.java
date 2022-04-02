package com.nsv.controller;

import com.nsv.domain.Company;
import com.nsv.domain.GenericStatus;
import com.nsv.domain.Refund;
import com.nsv.service.ICompanyService;
import com.nsv.service.IRefundService;
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
    private static final String REDIRECT_COMPANY = "redirect:/company/";
    
    @Autowired
    private ICompanyService companyService;
    
    @Autowired
    private IRefundService refundService;




    @RequestMapping(value={"/",""})
    public String home(Model model) {
//        model.addAttribute("company", new Company());
        model.addAttribute("refund", new Refund());
        var items =new ArrayList<Refund>();

        items.add(new Refund(1L));
        items.add(new Refund(2L));

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
        
        if (ControllerUtil.hasErrros(result, flash)) return REDIRECT_COMPANY;
        
        
        if(company.getStatus()==null)
            company.setStatus(GenericStatus.ACTIVE);
        
        companyService.save(company);
        status.setComplete();

        flash.addFlashAttribute("success", "Guardado con éxito!");
        return REDIRECT_COMPANY;
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
    
//    private boolean hasErrros(BindingResult result, RedirectAttributes flash) {
//        if (result.hasErrors()) {
//            flash.addFlashAttribute("success", "Se encontraron errores, favor verificar !");
//            return true;
//        }
//        return false;
//    }
}
