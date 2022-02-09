package com.nsv.controller;

import com.nsv.domain.Currency;
import com.nsv.domain.GenericStatus;
import com.nsv.service.IAccountingClosingService;
import com.nsv.service.ICurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 *
 * @author franm
 */
@Controller
@Secured( "ROLE_ADMIN")
@RequestMapping("/accountingclosing")
public class AccountingClosingController {
    
    private static final String REDIRECT_ = "redirect:/accountingclosing/";
    
    @Autowired
    private ICurrencyService currencyService;

    private final IAccountingClosingService closingService;

    public AccountingClosingController(IAccountingClosingService closingService) {
        this.closingService = closingService;
    }


    @RequestMapping(value={"/",""})
    public String home(Model model) {
        
        model.addAttribute("currency", new Currency());
        model.addAttribute("currencies", currencyService.findAll());
        closingService.doClose();
        
        return "accounting-closing/maint-accountingclosing";
    }

//    public String getCompany(@PathVariable(name = "id") Long id,Model model) {
//        model.addAttribute("company", inventoryService.find(id));
//        model.addAttribute("companys",inventoryService.findAll());
//        return "company/maint-company";
//    }
    
    @PostMapping("/open")
    public String doOpen(@Valid Currency c, BindingResult result, Model model,
            RedirectAttributes flash, SessionStatus status) {
        
        if (ControllerUtil.hasErrros(result, flash)) return REDIRECT_;
        
        if(c.getStatus()==null)
            c.setStatus(GenericStatus.ACTIVE);
        
        currencyService.save(c);
        status.setComplete();

        flash.addFlashAttribute("success", "Guardado con éxito!");
        return REDIRECT_;
    }
    
    
    @PostMapping("/inactive")
    public String doClose(@Valid Currency c, BindingResult result, Model model,
            RedirectAttributes flash, SessionStatus status) {

        if (ControllerUtil.hasErrros(result, flash)) return REDIRECT_;
        
        c.setStatus(GenericStatus.INACTIVE);
        currencyService.save(c);
//        status.setComplete();

        flash.addFlashAttribute("success", "El item fue inactivado con éxito!");

        return REDIRECT_;
    }

//
//    private boolean hasErrros(BindingResult result, RedirectAttributes flash) {
//        if (result.hasErrors()) {
//            flash.addFlashAttribute("success", "Se encontraron errores, favor verificar !");
//            return true;
//        }
//        return false;
//    }
}
