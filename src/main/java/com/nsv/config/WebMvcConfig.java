/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nsv.config;

import com.nsv.controller.interceptor.MainInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author you_k
 */

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    
    private final MainInterceptor mainInterceptor;

    public WebMvcConfig(MainInterceptor mainInterceptor) {
        this.mainInterceptor = mainInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        WebMvcConfigurer.super.addInterceptors(registry); //To change body of generated methods, choose Tools | Templates.
        registry.addInterceptor(mainInterceptor);
    }

	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/error_403").setViewName("error_403");
	}

//    private ISpringTemplateEngine templateEngine(ITemplateResolver templateResolver) {
//        var engine = new SpringTemplateEngine();
//        engine.addDialect(new Java8TimeDialect());
//        engine.setTemplateResolver(templateResolver);
//        return engine;
//    }
//    @Bean
//    public ViewResolver htmlViewResolver() {
//        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
//        resolver.setTemplateEngine(templateEngine(htmlTemplateResolver()));
//        resolver.setContentType("text/html");
//        resolver.setCharacterEncoding("UTF-8");
//        resolver.setViewNames((String[]) ArrayUtils.toStringArray("*.html"));
//        return resolver;
//    }
//
}
