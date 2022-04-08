package com.nsv.controller;

import com.nsv.service.IUserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

/**
 * 
 * @author you_k
 */
@Controller
@RequestMapping("/login")
@SessionAttributes({"customer","company"})
public class LoginController {
    
    private static final Log log = LogFactory.getLog(LoginController.class);
    
    @Autowired
    private IUserService userService;
    

    
    @GetMapping(value={"/",""})
    public String login(@RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout,
            Model model, Principal principal, RedirectAttributes flash) {
        log.info("##########LOGIN");
        
        //para cargar el Authentication de  forma  estatica
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        
        if(auth!=null)
            log.info("El usuario es "+auth.getName());
        
//        auth.getAuthorities().forEach((t) -> {
//           t.getRol();
//        });

        if (principal != null) {
            flash.addFlashAttribute("info", "Ya ha inciado sesión anteriormente");
            
            return "redirect:/home";
        }

        if (error != null) {
            model.addAttribute("error", "Error en el login: Nombre de usuario o contraseña incorrecta, por favor vuelva a intentarlo!");
        }

        if (logout != null) {
            model.addAttribute("success", "Ha cerrado sesión con éxito!");
        }
        
        return "login/login";
    }

//    
//    @PostMapping(value = "/authenticate")
//    public String authenticate(@RequestParam(value = "userId", required = false) String id,
//                @RequestParam(value = "password", required = false) String pass,
//                Model model,RedirectAttributes flash){
//        
//
//        log.debug("ID :: "+ id);
//        log.debug("Pass :: "+ pass);
//        if(userService.authenticate(id, pass)){
//            return "redirect:/home";
//        }else{
////            ControllerUtil.hasErrros(result, flash);
//            flash.addFlashAttribute("error","Usuario o contraseña incorrectos!");
//            return "redirect:/login";
//        }
//            
//    }
//    
    
}
