package com.nsv.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author you_k
 */
public class ControllerUtil {    
    
    private static final Log log = LogFactory.getLog(ControllerUtil.class);
    
    public static boolean hasErrors(BindingResult result, RedirectAttributes flash) {
        if (result.hasErrors()) {
            
            flash.addFlashAttribute("error", "Se encontraron errores, favor verificar !");
            result.getFieldErrors().forEach((t) ->
                log.info(t.getObjectName()+" :: "+t.getField()+" :: "+t.getDefaultMessage())
            );
            
            return true;
        }
        return false;
    }

    public static void displayMsg(RedirectAttributes flash,  String text) {
        flash.addFlashAttribute("success", text);
    }
    public static void displayError(RedirectAttributes flash, String text) {
        flash.addFlashAttribute("error", text);
    }
}
