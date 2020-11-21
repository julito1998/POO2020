package com.TuReserva2020.Reserva.Config;

import org.modelmapper.ModelMapper;
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
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ModelMapper modelMapper(){
        return  new ModelMapper();
    }

     
    @Override
     protected void configure(AuthenticationManagerBuilder auth) throws Exception{
         auth.userDetailsService(userDetailsService);
     } 
    @Override
     protected void configure(HttpSecurity http) throws Exception {

                            //se permite toda operacion en esta url
                        //el resto de las consultas deben ser de rol USER
                 /*   .and()
                        .formLogin()
                            .loginPage("/users/principal") // la pagina de inicio.
                                .permitAll()
                    .and()
                        .logout()
                            .permitAll();*/ //la pagina de logout es la misma que la de inicio.
            //http  .authorizeRequests().antMatchers("/**").hasRole("USER");

        http
				.authorizeRequests().antMatchers("/","/js/**","/css/**","/users","/users/principal","/users/login","/users/new").permitAll() //se permite toda operacion en esta url
                .and().formLogin().loginPage("/users/login");
        http
                .authorizeRequests()
                .antMatchers("/**").hasRole("USER");


    }
}