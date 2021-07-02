package com.nsv.config;


import com.nsv.service.IUserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    
    private static Log log = LogFactory.getLog(SecurityConfig.class);
	@Autowired
	private LoginSuccessHandler successHandler;

//	@Qualifier("UserService")
	@Autowired
	private IUserService userService;


    @Autowired
    private PasswordEncoder passwordEncoder;

    //
	@Override
	protected void configure(HttpSecurity http) throws Exception {
            

		http.authorizeRequests().antMatchers("/", "/css/**", "/js/**", "/images/**","/assets/**").permitAll()
		.anyRequest().authenticated()
		.and()
		    .formLogin()
		        .successHandler(successHandler)
		        .loginPage("/login")
		    .permitAll()
		.and()
		.logout().permitAll()
		.and()
		.exceptionHandling().accessDeniedPage("/error_403");
                
	}

//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.httpFirewall(allowUrlEncodedSlashHttpFirewall());
//        super.configure(web);
//    }

    @Autowired
    public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception {
        /*
		 * Deprecated
		 * UserBuilder users = User.withDefaultPasswordEncoder();
		 * */

        log.info("#############configurerGlobal");

//        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//        UserBuilder users = User.builder().passwordEncoder(encoder::encode);

//        build.inMemoryAuthentication()
//                .withUser(users.username("admin").password("12345").roles("ADMIN"))
//                .withUser(users.username("andres").password("12345").roles("USER"));

		build.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }

    @Bean
    public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowUrlEncodedSlash(true);
        return firewall;
    }


}
