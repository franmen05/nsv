package com.nsv.controller;

import com.nsv.domain.Currency;
import com.nsv.domain.GenericStatus;
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
 * @author you_k
 */
@Controller
@Secured( "ROLE_ADMIN")
@RequestMapping("/currency")
public class CurrencyController {
    
    private static final String REDIRECT_COMPANY = "redirect:/currency/";
    
    @Autowired
    private ICurrencyService currencyService;
    
    
    @RequestMapping(value={"/",""})
    public String home(Model model) {
        
        model.addAttribute("currency", new Currency());
        model.addAttribute("currencies", currencyService.findAll());
        
        return "currency/maint-currency";
    }

//    public String getCompany(@PathVariable(name = "id") Long id,Model model) {
//        model.addAttribute("company", inventoryService.find(id));
//        model.addAttribute("companys",inventoryService.findAll());
//        return "company/maint-company";
//    }
    
    @PostMapping("/add")
    public String save(@Valid Currency c, BindingResult result, Model model,
            RedirectAttributes flash, SessionStatus status) {
        
        if (ControllerUtil.hasErrros(result, flash)) return REDIRECT_COMPANY;
        
        if(c.getStatus()==null)
            c.setStatus(GenericStatus.ACTIVE);
        
        currencyService.save(c);
        status.setComplete();

        flash.addFlashAttribute("success", "Guardado con éxito!");
        return REDIRECT_COMPANY;
    }
    
    
    @PostMapping("/inactive")
    public String inactive(@Valid Currency c, BindingResult result, Model model,
            RedirectAttributes flash, SessionStatus status) {

        if (ControllerUtil.hasErrros(result, flash)) return REDIRECT_COMPANY;
        
        c.setStatus(GenericStatus.INACTIVE);
        currencyService.save(c);
//        status.setComplete();

        flash.addFlashAttribute("success", "El item fue inactivado con éxito!");

        return REDIRECT_COMPANY ;
    }
    
    @PostMapping("/reactive")
    public String reactive(@Valid Currency c, BindingResult result, Model model,
            RedirectAttributes flash, SessionStatus status) {

        if (ControllerUtil.hasErrros(result, flash)) return REDIRECT_COMPANY;
        
        c.setStatus(GenericStatus.ACTIVE);
        currencyService.save(c);
//        status.setComplete();

        flash.addFlashAttribute("success", "El item fue activado con éxito!");

        return REDIRECT_COMPANY ;
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
