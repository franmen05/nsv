package com.nsv.controller;

import com.nsv.domain.NCF;
import com.nsv.service.INCFService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

/**
 *
 * @author you_k
 */
@Secured( "ROLE_ADMIN")
@Controller
@RequestMapping("/ncf")
@SessionAttributes({"sequences", "series", "types", "ncf","companies"})
public class NCFController {

    private static final String MAIN_REDIRECT = "redirect:/ncf/";

    private final INCFService ncfService;

    public NCFController(INCFService ncfService) {
        this.ncfService = ncfService;
    }

    @RequestMapping("")
    public String index(Model model) {
        return home(model);
    }

    @RequestMapping("/")
    public String home(Model model) {

        model.addAttribute("ncf", new NCF());
//        model.addAttribute("series", ncfService.findAllSeries());
        model.addAttribute("types", ncfService.findAllTypes());
        //        model.addAttribute("ncfs",ncfService.findAll());
        return common(model);
    }

    @RequestMapping("/unused")
    public String unused(Model model) {

        model.addAttribute("ncf", new NCF());
        model.addAttribute("types", ncfService.findAllTypes());
        model.addAttribute("ncfs",ncfService.findAllUnused());
        if(model.asMap().get("series")==null)
            model.addAttribute("series", ncfService.findAllSeries());

        return "ncf/maint-ncf";
    }

    private String common(Model model) {
        model.addAttribute("ncfs",ncfService.findAll());
        if(model.asMap().get("series")==null)
            model.addAttribute("series", ncfService.findAllSeries());
        
//        model.addAttribute("types", ncfService.findAllTypes());
        return "ncf/maint-ncf";
    }

    @PostMapping("/generate")
    public String generate(@Valid NCF ncf, BindingResult result, Model model,
            RedirectAttributes flash, SessionStatus status) {

//        System.out.println("####generate()::"+ncf.toString());        
        if (ncf.getTo() == null || ncf.getFrom() == null || (ncf.getTo() - ncf.getFrom()) <= 0) {
//
//            if ((ncf.getTo() != null && ncf.getFrom() != null) && (ncf.getTo() - ncf.getFrom()) <= 0) {
//            if (&& (ncf.getTo() - ncf.getFrom()) <= 0) 
//                model.addAttribute("error", "La secuencia debe ser mayor que 1");
//            
                model.addAttribute("error", "Se encontraron errores, favor verificar !");
//            }
            model.addAttribute("ncf", ncf);
            return common(model);
        }
//        if (ControllerUtil.hasErrors(result, flash)) return MAIN_REDIRECT;

        model.addAttribute("sequences", ncfService.generateSequence(ncf));

        return common(model);
    }

    @PostMapping("/clear")
    public String clear( NCF ncf, BindingResult result, Model model,
            RedirectAttributes flash, SessionStatus status) {

        model.addAttribute("sequences", null);

        return common(model);
    }

    @PostMapping("/save")
    public String save(NCF ncf, BindingResult result, Model model,
            RedirectAttributes flash, SessionStatus status
    ) {

//        HttpSession session = request.getSession();
        List<NCF> sequences = (List<NCF>) model.asMap().get("sequences");
            
        ncfService.save(sequences);
        model.addAttribute("sequences",null);
        status.setComplete();

        flash.addFlashAttribute("success", "Guardados con éxito!");
        return MAIN_REDIRECT;
    }

//    @ResponseBody
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") Long id,
            Model model,RedirectAttributes flash, SessionStatus status) {
        
        ncfService.delete(id);
        flash.addFlashAttribute("success", "NCF fue eliminadoo con éxito!");
        return MAIN_REDIRECT;
    }
}
