package com.nsv.controller.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 *
 * @author you_k
 */
@Component
public class MainInterceptor implements HandlerInterceptor {

    private static final Log log = LogFactory.getLog(MainInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        
//        log.info("================ Before Method");
//        log.info("======== URL ::"+ request.getRequestURI());
//        log.info("================ URL is home ::"+ request.getRequestURI().contains("home"));
//
//        if(request.getRequestURI().contains("css") || request.getRequestURI().contains("js") || 
//                request.getRequestURI().contains("assets") || request.getRequestURI().contains("company") )
//           return true;
//        
//        if(!request.getRequestURI().contains("home")){
//            Object company = request.getSession().getAttribute("company");
////            log.info("================ Company? ::"+ company);
//            if(company==null){
//                response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
//                response.setHeader("Location", "/home");
//            }
//        }
//        
        return true;
    }

//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
//        log.info("================ Method Executed");
//        response.getHeaders("interceptor").add("interceptor");
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
//        log.info("================ Method Completed");
//    }

}
