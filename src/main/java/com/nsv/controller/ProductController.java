package com.nsv.controller;

import com.nsv.domain.GenericStatus;
import com.nsv.domain.Product;
import com.nsv.service.IInventoryService;
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
@RequestMapping("/product")
@SessionAttributes({"companies"})
public class ProductController {
    
    @Autowired
    private IInventoryService inventoryService;
    
    @RequestMapping(value={"/",""})
    public String home(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("products",inventoryService.findAllProduct());
        return "product/maint-product";
    }
    
//    public String getProduct(@PathVariable(name = "id") Long id,Model model) {
//        model.addAttribute("product", inventoryService.findProduct(id));
//        model.addAttribute("products",inventoryService.findAllProduct());
//        return "product/maint-product";
//    }
    
    @PostMapping("/add")
    public String save(@Valid Product product, BindingResult result, Model model,
            RedirectAttributes flash, SessionStatus status) {
        
        if (hasErrors(result, flash)) return "redirect:/product/";

        if(product.getStatus()==null)
            product.setStatus(GenericStatus.ACTIVE);

        inventoryService.saveProduct(product);
        status.setComplete();
        ControllerUtil.displayMsg(flash, "Producto fue guardado con éxito!");

        return "redirect:/product/" ;
    }
    
    @PostMapping("/inactive")
    public String inactive(@Valid Product product, BindingResult result, Model model,
            RedirectAttributes flash, SessionStatus status) {

        if (hasErrors(result, flash)) return "redirect:/product/";
        
        product.setStatus(GenericStatus.INACTIVE);
        inventoryService.saveProduct(product);
//        status.setComplete();

        ControllerUtil.displayMsg(flash, "Producto fue inactivado con éxito!");

        return "redirect:/product/" ;
    }
    
    @PostMapping("/reactive")
    public String reactive(@Valid Product product, BindingResult result, Model model,
            RedirectAttributes flash, SessionStatus status) {

        if (hasErrors(result, flash)) return "redirect:/product/";
        
        product.setStatus(GenericStatus.ACTIVE);
        inventoryService.saveProduct(product);
//        status.setComplete();

        ControllerUtil.displayMsg(flash, "Producto fue activado con éxito!");

        return "redirect:/product/" ;
    }

    private boolean hasErrors(BindingResult result, RedirectAttributes flash) {
        if (result.hasErrors()) {
            ControllerUtil.displayError(flash,"Se encontraron errores, favor verificar !");
            return true;
        }
        return false;
    }


}
