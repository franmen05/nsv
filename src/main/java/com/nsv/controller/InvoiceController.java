package com.nsv.controller;

import com.nsv.domain.*;
import com.nsv.exception.NSVException;
import com.nsv.service.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/invoice")
@SessionAttributes({"invoice", "titulo", "currencies","paymentsTypes"})
public class InvoiceController {

    private static final Log log = LogFactory.getLog(InvoiceController.class);
    
    private static final String REDIRECT_CUSTOMER= "redirect:/customer/ver/";

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IInvoiceService invoiceService;

    @Autowired
    private IInventoryService inventoryService;
    
    @Autowired
    private INCFService ncfService;

    @Autowired
    private ICurrencyService currencyService;
    
    
    private Invoice invoice;
    

    @RequestMapping("/ver/{id}")
    public String home(@PathVariable(name = "id") Long id,
            @SessionAttribute(name = "titulo",required = false) String title,
            Model model) {

        model.addAttribute("invoice", invoiceService.findInvoiceById(id));
        model.addAttribute("titulo", title);
        
        return "invoice/see-invoice";
    }

    @ResponseBody
    @GetMapping(value = "/loadProduct/{term}", produces = {"application/json"})
    public List<Product> loadProduct(@PathVariable String term) {
        return inventoryService.findProductByName(term);
    }

    //TODO mover esto a controlador aparte junto con el con las llamadas del servicio al dao
    @ResponseBody
    @GetMapping(value = "/loadTaxes", produces = {"application/json"})
    public Double loadTaxes() {
        return invoiceService.totalTaxesByTaxGroup(1L);
    }

    @ResponseBody
    @GetMapping(value = "/loadAdditionalExpense/{term}", produces = {"application/json"})
    public List<AdditionalExpense> loadAdditionalExpense(@PathVariable String term) {
        return invoiceService.findAdditionalExpenseByName(term);
    }

    @ResponseBody
    @GetMapping(value = "/loadInvoice/{id}", produces = {"application/json"})
    public Object loadInvoice(@PathVariable Long id) {
//        List<AdditionalExpense> r = new ArrayList<>();
//        Invoice i=(Invoice) model.asMap().get("invoice");
//        if(i.getId()==id)
//            return i;
        
        return invoiceService.findInvoiceById(id);
    }
    
    @GetMapping("/form/edit/{idInvoice}")
    public String edit(@PathVariable(name = "idInvoice") Long id, 
            @SessionAttribute("company") Company c,
            @SessionAttribute("topProduct") Page<Product> pp,
            @SessionAttribute("topExpensive") Page<AdditionalExpense> pae,
            Model model) {
        
        model.addAttribute("company", c);
        model.addAttribute("topProduct", pp);
        model.addAttribute("topExpensive", pae);
        genericLoad(model);
        Invoice  i=invoiceService.findInvoiceById(id);
        

        model.addAttribute("titulo", "Modificar Factura");
        model.addAttribute("invoice", i);
        
        return "invoice/new-invoice";
    }
    

    @GetMapping("/form/new/{idCustomer}")
    public String create(@PathVariable(name = "idCustomer") Long idCustomer, 
                @SessionAttribute("company") Company c,
                @SessionAttribute("topProduct") Page<Product> pp,
                @SessionAttribute("topExpensive") Page<AdditionalExpense> pae,
                Model model) {
//        Customer c=customerService.find(id);
        model.addAttribute("isNewInvoice", true);
        model.addAttribute("isInvoice", true);
        model.addAttribute("company", c);
        model.addAttribute("topProduct", pp);
        model.addAttribute("topExpensive", pae);
        genericLoad(model);
        Invoice i = new Invoice();
        i.setCustomer(customerService.find(idCustomer));
        i.setDescription("Facturacion Carga");
        i.setCurrency(c.getCurrency());
        i.setCompany(c);
        
        model.addAttribute("titulo", "Crear Factura");
        model.addAttribute("invoice", i);
        
        return "invoice/new-invoice";
    }

    private void genericLoad(Model model) {
        
        model.addAttribute("currencies", currencyService.findAll());
        
        //TODO: se  debe poner que seleccione si se debea aplicar los impuestos.
        List<Tax> taxes = invoiceService.findTaxesByTaxGroup(1l);
        model.addAttribute("taxes", taxes);
//        invoiceService.findTaxesByTaxGroup(1l).forEach((t) -> {
//
//            i.addTaxItems(new TaxItem(t));
//        });
    }

    @GetMapping("/form/refund")
    public String refund(Model model) {
//        Customer c=customerService.find(id);
        Invoice i = new Invoice();
        i.setDescription("Facturacion Carga");
        model.addAttribute("invoice", i);
        return "invoice/new-invoice";
    }

    @PostMapping("/form")
    public String save(@Valid Invoice _invoice, BindingResult result, Model model,
            @RequestParam(name = "item_id[]", required = false) Long[] itemId,
            @RequestParam(name = "cantidad[]", required = false) Long[] cantidad,
            @RequestParam(name = "dicount[]", required = false) Long[] dicounts,
            @RequestParam(name = "ae_item_id[]", required = false) Long[] aeItemId,
            @RequestParam(name = "ae_cost[]", required = false) Double[] aeCost,
            @SessionAttribute("topProduct") Page<Product> pp,
            @SessionAttribute("topExpensive") Page<AdditionalExpense> pae,
            RedirectAttributes flash, SessionStatus status) {

        model.addAttribute("topProduct", pp);
        model.addAttribute("topExpensive", pae);

        if (result.hasErrors()) {
            model.addAttribute("titulo", "Crear Factura (tiene errores)");
            return "invoice/new-invoice";
        }

        if (ArrayUtils.isEmpty(itemId)) {
            model.addAttribute("titulo", "Crear Factura");
            model.addAttribute("error", "Error: La factura esta vacia!");
            return "invoice/new-invoice";
        }

        _invoice.clear();
        saveInvoice(itemId, cantidad, _invoice, aeItemId, aeCost);
//        model.addAttribute("invoice", invoice);
//        status.setComplete();

        flash.addFlashAttribute("success", "Factura creada con éxito!");

//        return "redirect:/invoice/ver/" + invoice.getCustomer().getId();
        return home(_invoice.getId(), "Factura",model);
    }

    private Invoice saveInvoice(Long[] itemId, Long[] cantidad, Invoice _invoice, Long[] aeItemId, Double[] aeCost) {
        
        for (int i = 0; i < itemId.length; i++) {
            Product product = inventoryService.findProduct(itemId[i]);

            InvoiceItem linea = new InvoiceItem();
            linea.setQuantity(cantidad[i]);
            linea.addProduct(product);
            _invoice.addItem(linea);

            log.info("ID: " + itemId[i].toString() + ", cantidad: " + cantidad[i].toString());
        }

        if (!ArrayUtils.isEmpty(aeItemId)) {
            for (int i = 0; i < aeItemId.length; i++) {

                var ae = invoiceService.findAdditionalExpense(aeItemId[i]);
                var item = new AdditionalExpenseItem();
                item.setAmount(aeCost[i]);
                item.setAdditionalExpense(ae);
                _invoice.addAddtionalExpenseItem(item);

                log.info("ID: " + aeItemId[i].toString() + ", costo: " + aeCost[i]);
            }
        }
        if(_invoice.getHasTax()){
            List<Tax> taxes = invoiceService.findTaxesByTaxGroup(1L);    //TODO cambiar este valor fijo
            taxes.forEach((t) -> _invoice.addTaxItems(new TaxItem(t)));
        }
        
        _invoice.calculeTotalPayment();
        Invoice r= invoiceService.saveInvoice(_invoice);
        return  r;
    }

    @PostMapping("/payment/doPayment")
    public String doPayment(@Valid Invoice _invoice, BindingResult result, Model model,
            @RequestParam(name = "item_id[]", required = false) Long[] itemId,
            @RequestParam(name = "cantidad[]", required = false) Long[] cantidad,
            @RequestParam(name = "ae_item_id[]", required = false) Long[] aeItemId,
            @RequestParam(name = "ae_cost[]", required = false) Double[] aeCost,
            RedirectAttributes flash, SessionStatus status) {
        
        _invoice.clear();
        Invoice i= saveInvoice(itemId, cantidad, _invoice, aeItemId, aeCost);
        
        if(i==null){
            model.addAttribute("error", "Error: La factura no puedo ser guardada!");
                return "invoice/payment-invoice";
        }

        model.addAttribute("invoice", invoiceService.findInvoiceById(_invoice.getId()));
//        model.addAttribute("invoice", _invoice);
        model.addAttribute("paymentsTypes", invoiceService.findAllPaymentType());

        return "invoice/payment-invoice";
    }

    @RequestMapping("/payment/{idInvoice}")
    public String paymentHome(@PathVariable(name = "idInvoice") Long id, Model model) {
        
//        Customer c=customerDao.findById(1L).get();
//        model.addAttribute("customer", c);
//        model.addAttribute("attribute", "value");

        genericLoad(model);
        model.addAttribute("titulo", "Factura");
        model.addAttribute("invoice", invoiceService.findInvoiceById(id));
        model.addAttribute("paymentsTypes", invoiceService.findAllPaymentType());   

        return "invoice/payment-invoice";
    }


    @PostMapping("/formSavePayment")
    public String savePayment(Model model,
            @RequestParam(name = "payment_type_[]", required = false) Long[] itemTypePayment,
            @RequestParam(name = "payment_value_[]", required = false) Double[] itemValuePayment,
            @RequestParam(name = "b_partial_payment", required = false) String bPartialPayment,
            RedirectAttributes flash, SessionStatus status
    ) {
        this.invoice = (Invoice) model.asMap().get("invoice");
        invoice.getPayments().clear();

//        if (result.hasErrors()) {
////            model.addAttribute("titulo", "Crear Factura (tiene errores)");
//            return "invoice/new-invoice";
//        }

        
        if (!ArrayUtils.isEmpty(itemTypePayment) && !ArrayUtils.isEmpty(itemValuePayment)) {
            for (int i = 0; i < itemTypePayment.length; i++) {

                Payment pay = new Payment();
                Double value = itemValuePayment[i];
                if(value == null ) continue;
                
                invoiceService.findPaymentType(itemTypePayment[i]).ifPresent((t) -> {
                    pay.setPaymentType(t);
                    pay.setValue( value);
                    invoice.addPayment(pay);
                });
                
//                log.info("ID: " + aeItemId[i].toString() + ", costo: " + aeCost[i]);
            }
            invoice.calculeTotalPayment();
        }
        
        Invoice i= invoiceService.saveInvoice(invoice);
        String title="Factura";
        
        if(i==null){
            model.addAttribute("error", "Error: La factura NO puedo ser guardada!");
            return "invoice/payment-invoice";
            
        }else{
        
            if (StringUtils.isEmpty(bPartialPayment)) {
                
                log.info(bPartialPayment); 
                if (i.getHasNCF()) {
                    try {
                        ncfService.addInvoice(i);
                    } catch (NSVException ex) {

                        model.addAttribute("error", "Error: La factura, no se puedo agregar NCF");
                        return "invoice/payment-invoice";
                    }
                }
            }else{
                title+="(Abono)";
            }

            flash.addFlashAttribute("success", "Factura creada con éxito!");
        }
        
        return home(invoice.getId(), title,model);
    }    
    
    @RequestMapping("/print/{idInvoice}")
    public String print(@PathVariable(name = "idInvoice") Long id, 
            @SessionAttribute(name = "titulo",required = false) String title,
            Model model) {
        
//        Customer c=customerDao.findById(1L).get();
//        model.addAttribute("customer", c);
//        model.addAttribute("attribute", "value");
//        HttpSession session = request.getSession();
//        Company c = (Company) session.getAttribute("company");
        
        Invoice i= invoiceService.findInvoiceById(id);
        model.addAttribute("invoice", i);
        model.addAttribute("titulo", title);
        model.addAttribute("paymentsTypes", invoiceService.findAllPaymentType());   
        ncfService.findByInovice(i).ifPresent((t) -> {
            model.addAttribute("ncf", t);   
        });

        return "invoice/print-invoice";
    }
    
    @RequestMapping("/delete/{idInvoice}")
    public String remove(@PathVariable(name = "idInvoice") Long id, Model model) {
        
        invoiceService.deleteInvoice(id);
//        model.addAttribute("invoice", i);
        
        return REDIRECT_CUSTOMER+id;
    }

}
