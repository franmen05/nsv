package com.nsv.controller;

import com.nsv.domain.AccountingClosing;
import com.nsv.domain.Currency;
import com.nsv.exception.NSVException;
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
        model.addAttribute("ac", closingService.getAccountOpen().orElse(new AccountingClosing()));

        
        return "accounting-closing/maint-accountingclosing";
    }

    @PostMapping("/doOpen")
    public String doOpen(@Valid Currency c, BindingResult result, Model model,
            RedirectAttributes flash, SessionStatus status) {
        
        if (ControllerUtil.hasErrros(result, flash)) return REDIRECT_;

        try {
            closingService.doOpen();
            flash.addFlashAttribute("success", "La caja esta abierta!");
        } catch (NSVException e) {
            e.printStackTrace();
            flash.addFlashAttribute("error", e.getMessage());
        }

        return REDIRECT_;
    }
    
    
    @PostMapping("/doClose")
    public String doClose( AccountingClosing ac, BindingResult result, Model model,
                          RedirectAttributes flash, SessionStatus status) {

        if (ControllerUtil.hasErrros(result, flash)) return REDIRECT_;

        try {
            closingService.doClose(ac);
            flash.addFlashAttribute("success", "Cierre realizado con éxito!");
        } catch (NSVException e) {
            e.printStackTrace();
            flash.addFlashAttribute("error", e.getMessage());
        }


        return REDIRECT_;
    }

}
