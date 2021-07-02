package com.nsv.controller;

import com.nsv.domain.AdditionalExpense;
import com.nsv.domain.GenericStatus;
import com.nsv.service.IInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
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

/**
 *
 * @author you_k
 */
@Controller
@Secured( "ROLE_ADMIN")
@RequestMapping("/additionalexpense")
@SessionAttributes({"companies"})
public class AdditionalExprenseController {
    
    /**
     * redirect:/additional-expense
     */
    private static final String MAIN_REDIRECT = "redirect:/additionalexpense/";
    
    @Autowired
    private IInvoiceService invoiceService;
    
    @RequestMapping("")
    public String index(Model model) {
        return home(model);
    }
    
    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("additionalExpense", new AdditionalExpense());
        model.addAttribute("additionalExpenses",invoiceService.findAllAdditionalExpenses());
        return "additional-expense/maint-additionalexpense";
    }
    
    public String getAdditionalExpense(@PathVariable(name = "id") Long id,Model model) {
        model.addAttribute("additionalExpense", invoiceService.findAdditonalExpense(id));
        model.addAttribute("additionalExpenses",invoiceService.findAllAdditionalExpenses());
        return "additional-expense/maint-additionalexpense";
    }
    
    @PostMapping("/add")
    public String save(@Valid AdditionalExpense additionalExpense, BindingResult result, Model model,
            RedirectAttributes flash, SessionStatus status) {
        
        if (ControllerUtil.hasErrros(result, flash)) return MAIN_REDIRECT;
        
        if(additionalExpense.getStatus()==null)
            additionalExpense.setStatus(GenericStatus.ACTIVE);
        
        invoiceService.saveAdditionalExpense(additionalExpense);
        status.setComplete();

        flash.addFlashAttribute("success", "Guardado con éxito!");
        return MAIN_REDIRECT ;
    }
    
    @PostMapping("/delete")
    public String delete(@Valid AdditionalExpense additionalExpense, BindingResult result, Model model,
            RedirectAttributes flash, SessionStatus status) {

        if (ControllerUtil.hasErrros(result, flash)) return MAIN_REDIRECT;
        
        additionalExpense.setStatus(GenericStatus.INACTIVE);
        invoiceService.saveAdditionalExpense(additionalExpense);
//        status.setComplete();

        flash.addFlashAttribute("success", "El item fue inactivado con éxito!");

        return MAIN_REDIRECT ;
    }
    
    
    @PostMapping("/reactive")
    public String reactive(@Valid AdditionalExpense additionalExpense, BindingResult result, Model model,
            RedirectAttributes flash, SessionStatus status) {

        if (ControllerUtil.hasErrros(result, flash)) return MAIN_REDIRECT;
        
        additionalExpense.setStatus(GenericStatus.ACTIVE);
        invoiceService.saveAdditionalExpense(additionalExpense);
//        status.setComplete();

        flash.addFlashAttribute("success", "El item fue activado con éxito!");

        return MAIN_REDIRECT ;
    }
}
