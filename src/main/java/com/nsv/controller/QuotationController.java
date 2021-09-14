package com.nsv.controller;

import com.nsv.domain.*;
import com.nsv.service.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.util.ArrayUtils;

import javax.validation.Valid;
import java.util.List;

/**
 *
 * @author you_k
 */
@Controller
@Secured( {"ROLE_ADMIN","ROLE_SALER","ROLE_OPERATIONS"})
@RequestMapping("/quotation")
@SessionAttributes({"quotation", "titulo", "currencies","customerId","companies"})
public class QuotationController {

    private static final Log log = LogFactory.getLog(QuotationController.class);

    private final ICustomerService customerService;
    private final IInvoiceService invoiceService;
    private final IInventoryService inventoryService;
    private final IQuotationService quotationService;
    private final ICurrencyService currencyService;

//    private Invoice invoice;

    public QuotationController(ICustomerService customerService, IInvoiceService invoiceService,
                               IInventoryService inventoryService, IQuotationService quotationService,
                               ICurrencyService currencyService) {

        this.customerService = customerService;
        this.invoiceService = invoiceService;
        this.inventoryService = inventoryService;
        this.quotationService = quotationService;
        this.currencyService = currencyService;
    }

    @GetMapping({"/",""})
    public String home(@SessionAttribute( name = "company", required = false) Company c, Model model) {
        if (c==null) {
            model.addAttribute("error", "Error: Debe seleccionar una compañia!");
            return "home";
        }
        var o = initQuotation(c);
        genericInit(model, o, true);
        model.addAttribute("quotations", quotationService.findByCompany(c));
        model.addAttribute("isCustomer", false);
        model.addAttribute("customerId", null);


        return "quotation/home-quotation";
    }
     
    @GetMapping("/{customerId}")
    public String home(@SessionAttribute(name = "company",required = false) Company c,
            @PathVariable(name = "customerId") Long customerId,
            Model model) {

        Quotation o = initQuotation(c);
        genericInit(model, o, true);
//        model.addAttribute("quotations", quotationService.findByCustomerId(customerId));
        model.addAttribute("quotations", quotationService.findAll(customerId,c));
        model.addAttribute("isCustomer", true);
        model.addAttribute("customerId", customerId);

        return "quotation/home-quotation";
    }
    
    @GetMapping("/new/{idCustomer}")
    public String quotation(
            @PathVariable(name = "idCustomer") Long idCustomer,
            @SessionAttribute(name = "company",required = false) Company c,
            @SessionAttribute("topProduct") Page<Product> pp,
            @SessionAttribute("topExpensive") Page<AdditionalExpense> pae,
            Model model) {

//        Customer c=customerService.find(id);
        Quotation q = initQuotation(c);
        model.addAttribute("topProduct", pp);
        model.addAttribute("topExpensive", pae);
        final Customer customer = customerService.find(idCustomer);
        q.setCustomer(customer);
        q.setContact(customer.getName()+" "+customer.getLastName());
        q.setEmail(customer.getEmail());
        
        model.addAttribute("customerId", idCustomer);
        genericInit(model, q, true);

        return "quotation/new-quotation";
    }


    @GetMapping("/new")
    public String quotationUnRegisterClient(
            @SessionAttribute(name = "company",required = false) Company c,
            @SessionAttribute("topProduct") Page<Product> pp,
            @SessionAttribute("topExpensive") Page<AdditionalExpense> pae,
            Model model) {

        Quotation o = initQuotation(c);
        genericInit(model, o, true);
        model.addAttribute("topProduct", pp);
        model.addAttribute("topExpensive", pae);

        return "quotation/new-quotation";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") Long id, Model model) {
        quotationService.delete(id);
        model.addAttribute("quotation", id);
        return "redirect:/quotation";
    }

    
    private void genericInit(Model model, Quotation o, boolean onInit) {

        model.addAttribute("isQuotation", true);
        model.addAttribute("topProduct", true);

        if (onInit) {
            model.addAttribute("currencies", currencyService.findAll());
            model.addAttribute("titulo", "Cotizacion");
        }
        model.addAttribute("quotation", o);
    }

    private Quotation initQuotation(Company c) {
        var o = new Quotation();
        o.setDescription("Cotizacion");
//        o.setComment("Impuestos no incluidos");
        o.setCompany(c);
        
        return o;
    }

    @PostMapping("/save")
    public String save(@Valid Quotation _quotation, BindingResult result, Model model,
            @RequestParam(name = "item_id[]", required = false) Long[] itemId,
            @RequestParam(name = "cantidad[]", required = false) Long[] cantidad,
            @RequestParam(name = "dicount[]", required = false) Float[] dicounts,
            @RequestParam(name = "ae_item_id[]", required = false) Long[] aeItemId,
            @RequestParam(name = "ae_cost[]", required = false) Double[] aeCost,
            @RequestParam(name = "btn-save", required = false) String btnSave,
            @SessionAttribute(name = "customerId",required = false) Long customerId,
            @SessionAttribute("topProduct") Page<Product> pp,
            @SessionAttribute("topExpensive") Page<AdditionalExpense> pae,
            RedirectAttributes flash, SessionStatus status) {
        
        log.info("########save");
        model.addAttribute("topProduct", pp);
        model.addAttribute("topExpensive", pae);
        if (result.hasErrors()) {
            model.addAttribute("error", "Error: El formualario tiene errores!");
            return "quotation/new-quotation";
        }

        if (ArrayUtils.isEmpty(itemId)) {
            model.addAttribute("error", "Error: La Cotizacion esta vacia!");
            return "quotation/new-quotation";
        }

        _quotation.clear();

        saveQuotation(_quotation, itemId, cantidad,dicounts, aeItemId, aeCost);
//        status.setComplete();
        

        flash.addFlashAttribute("success", "Cotizacion creada con éxito!");
        
        if(!StringUtils.hasText(btnSave))
            return "redirect:/quotation/print/"+_quotation.getId();
        else
            if(customerId!=null)
                return "redirect:/quotation/"+customerId;
            else
                return "redirect:/quotation/";
    }

    private Quotation saveQuotation(Quotation _quotation, Long[] itemId, Long[] cantidad,
                                    Float[] discount, Long[] aeItemId, Double[] aeCost) {

        for (int i = 0; i < itemId.length; i++) {
            Product product = inventoryService.findProduct(itemId[i]);

            QuotationItem item = new QuotationItem();
            item.setQuantity(cantidad[i]);
            item.setDiscount(discount[i]/100);
            item.addProduct(product);
            _quotation.addItem(item);

            log.info("ID: " + itemId[i].toString() + ", cantidad: " + cantidad[i].toString());
        }

        if (!ArrayUtils.isEmpty(aeItemId)) {
            for (int i = 0; i < aeItemId.length; i++) {

                AdditionalExpense ae = invoiceService.findAdditionalExpense(aeItemId[i]);
                QuotationItem item = new QuotationItem();
                item.setQuantity(cantidad[i]);
                item.addAdditionalExpense(ae, aeCost[i]);
                _quotation.addItem(item);

                log.info("ID: " + aeItemId[i].toString() + ", costo: " + aeCost[i]);
            }
        }
        if(_quotation.getHasTax()){
            var taxes = invoiceService.totalTaxesByTaxGroup(1L);    //TODO cambiar este valor fijo
            _quotation.setTotalWithTaxes(_quotation.getTotal()+taxes);
        }

        return quotationService.save(_quotation);
    }

    @RequestMapping("/print/{idQuotation}")
    public String print(@PathVariable(name = "idQuotation") Long id, Model model) {

        quotationService.findById(id).ifPresent((t) -> {
            genericInit(model, t, false);
        });

        return "quotation/print-quotation";
    }

}
