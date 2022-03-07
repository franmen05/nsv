package com.nsv.controller;

import com.nsv.domain.Rol;
import com.nsv.domain.User;
import com.nsv.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @author you_k
 */
@Controller
@Secured( "ROLE_ADMIN")
@RequestMapping("/user")
@SessionAttributes({"companies","roles"})
public class UserController {

    private static final String REDIRECT_USER = "redirect:/user/";

    @Autowired
    private IUserService userService;


    @RequestMapping(value = {"/",""})
    public String home(Model model) {
        User user = new User();
//        user.setCreateDate(new Date());
        model.addAttribute("user", user);
        model.addAttribute("users",userService.findAll());
        model.addAttribute("roles",userService.findAllRoles());
        return "user/maint-user";
    }
    
    public String getUser(@PathVariable(name = "accountingClosingId") Long id,Model model) {
        model.addAttribute("user", userService.find(id));
        model.addAttribute("users",userService.findAll());
        return "user/maint-user";
    }
    
    @PostMapping("/add")
    public String save(@Valid User user, BindingResult result, Model model,
                       @SessionAttribute( name = "roles", required = false) List<Rol> roles,
                       @RequestParam(name = "rol", required = false) String rol,
                       RedirectAttributes flash, SessionStatus status) {
        
        if (ControllerUtil.hasErrros(result, flash))  return REDIRECT_USER;
        
        if(user.getEnabled()==null)
            user.setEnabled(true);

        user.clearRol();

        roles.stream().filter(ro->ro.getAuthority().equalsIgnoreCase(rol)).findFirst().ifPresent((r )->{

            user.addRol(r);
        });

        
        userService.save(user);
        status.setComplete();

        flash.addFlashAttribute("success", "Usuario fue guardado con éxito!");
        return REDIRECT_USER ;
    }
    
    @PostMapping("/inactive")
    public String inactive(@Valid User user, BindingResult result, Model model,
            RedirectAttributes flash, SessionStatus status) {

        if (ControllerUtil.hasErrros(result, flash)) return REDIRECT_USER;
        
        user.setEnabled(false);
        userService.save(user);
//        status.setComplete();

        flash.addFlashAttribute("success", "Usero fue inactivado con éxito!");

        return REDIRECT_USER ;
    }

    
    @PostMapping("/reactive")
    public String reactive(@Valid User user, BindingResult result, Model model,
            RedirectAttributes flash, SessionStatus status) {

        if (ControllerUtil.hasErrros(result, flash))
            return REDIRECT_USER;
        
        user.setEnabled(true);
        userService.save(user);
//        status.setComplete();

        flash.addFlashAttribute("success", "Usero fue activado con éxito!");

        return REDIRECT_USER ;
    }

}
