package com.TuReserva2020.Reserva.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.ModelMap;

/**
 *
 * @author Julito
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Qualifier("userService")
    @Autowired
    private UserDetailsService userDetailsService;


    @Bean 
     public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(4);
    }
     
    @Override
     protected void configure(AuthenticationManagerBuilder auth) throws Exception{
         auth.userDetailsService(userDetailsService);
           
     } 
    @Override
     protected void configure(HttpSecurity http) throws Exception {


        /*http.authorizeRequests()
                .antMatchers("/","/js/**","/css/**","users/login","users/new").permitAll();
        http.authorizeRequests()
                .antMatchers("/**")
                .hasRole("USER");*/



                //.antMatchers(HttpMethod.POST,"/users").permitAll()
                //.antMatchers("users/home").hasRole("USER");
                //.and().formLogin().loginPage("/users/login");


        http
				.authorizeRequests().antMatchers("/","/js/**","/css/**","/users","/users/principal","/users/logout","/users/login","/users/new").permitAll() //se permite toda operacion en esta url
                .and()
                .formLogin().loginPage("/users/principal");
        http
                .authorizeRequests()
                .antMatchers("/**") .hasRole("USER");

    }
}
