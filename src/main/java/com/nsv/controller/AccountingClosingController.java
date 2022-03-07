package com.nsv.controller;

import com.nsv.config.SecurityConfig;
import com.nsv.controller.dto.SalesTodayDTO;
import com.nsv.domain.AccountingClosing;
import com.nsv.exception.NSVException;
import com.nsv.service.IAccountingClosingService;
import com.nsv.service.IReportService;
import com.nsv.utils.DateUtil;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@Secured( {SecurityConfig.ROLE_ADMIN})
@RequestMapping("/accountingclosing")
public class AccountingClosingController {

    private static final String REDIRECT_ = "redirect:/accountingclosing/";
    public static final String RD = "accounting-closing/rep-salesToday";
    public static final String RP = "accounting-closing/rep-payment-details";
    public static final String REDIRECT_RD = "redirect:/accountingclosing/rep-salesToday";


    private final IAccountingClosingService closingService;
    private final IReportService reportService;

    public AccountingClosingController(IAccountingClosingService closingService, IReportService reportService) {
        this.closingService = closingService;
        this.reportService = reportService;
    }


    @GetMapping(value={"/rep-salesToday"})
    public String homeReport(Model model) {

        model.addAttribute("daySales", reportService.findAllDaySales());
        model.addAttribute("rd", new SalesTodayDTO("", 0L));
        
        return RD;
    }


//    @Secured( {SecurityConfig.ROLE_ACCOUNTING_OPENER})
    @PostMapping("/rep-salesToday")
    public String selectedDate(@Valid SalesTodayDTO salesToday, BindingResult result, Model model,
                               RedirectAttributes flash) {


        if (salesToday==null || (!StringUtils.hasText(salesToday.date())  &&  null ==salesToday.id()) ){
            flash.addFlashAttribute("error", "Debe seleccionar una fecha !");
            return REDIRECT_RD;
        }

        if (ControllerUtil.hasErrros(result, flash) ) return REDIRECT_RD;

        if(StringUtils.hasText(salesToday.date()))
            model.addAttribute("daySales", reportService.findAllDaySalesByDate( DateUtil.getInstant(salesToday.date())));
        else
            model.addAttribute("daySales", reportService.findAllByAccountingClosingId( salesToday.id()));

        model.addAttribute("rd",  new SalesTodayDTO("", 0L));

//        reportService.findAllDaySales()

        return RD;
    }

//    @Secured( {SecurityConfig.ROLE_ACCOUNTING_OPENER})
    @GetMapping("/rep-paymentDetails/{idInvoice}")
    public String details(@PathVariable Long idInvoice, Model model) {


        model.addAttribute("paymentDetails", reportService.findAllPaymentByAccountClosing(idInvoice));
        model.addAttribute("invoice", reportService.findInvoiceById(idInvoice));
        model.addAttribute("rd", new SalesTodayDTO("", 0L));

//        reportService.findAllDaySales()

        return RP;
    }


    @RequestMapping(value={"/",""})
    public String home(Model model) {

        model.addAttribute("acs", closingService.findAll());
        model.addAttribute("ac", closingService.getAccountOpen().orElse(new AccountingClosing()));


        return "accounting-closing/maint-accountingclosing";
    }


    @Secured( {SecurityConfig.ROLE_ACCOUNTING_OPENER})
    @PostMapping("/doOpen")
    public String doOpen(@Valid AccountingClosing ac, BindingResult result, Model model,
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


    @Secured( SecurityConfig.ROLE_ACCOUNTING_CLOSER)
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
