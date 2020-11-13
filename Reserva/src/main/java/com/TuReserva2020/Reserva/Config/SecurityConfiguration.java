package com.TuReserva2020.Reserva.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


/**
 *
 * @author Julito
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    @Bean 
     public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(4);
    }
     
    @Override
     protected void configure(AuthenticationManagerBuilder auth) throws Exception{
         auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
           
     } 
    @Override
     protected void configure(HttpSecurity httpSecurity) throws Exception {
			httpSecurity
				.authorizeRequests().antMatchers("/","/login","/logout","users/new").permitAll() //se permite toda operacion en esta url
                                .antMatchers(HttpMethod.POST,"/users").permitAll();
                        httpSecurity
                              .authorizeRequests()
                              .antMatchers("/**").access("hasRole(ROLE_USER)");                       
    }
}
