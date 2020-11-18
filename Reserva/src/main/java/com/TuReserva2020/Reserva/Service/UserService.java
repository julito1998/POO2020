/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TuReserva2020.Reserva.Service;

import com.TuReserva2020.Reserva.InterfaceService.IUserService;
import com.TuReserva2020.Reserva.Model.User;
import com.TuReserva2020.Reserva.Repository.UserRepo;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Julito
 */
@Service
public class UserService implements UserDetailsService, IUserService{

    @Autowired
    private UserRepo repo;
    
     
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException,NullPointerException {
        User user=repo.findByEmail(email);
        //si el mail devuelve null
        if(!repo.existsByEmail(email)){
            throw new UsernameNotFoundException("El mail no existe");
        }
        else{
            //si existe el mail debo comprobar que coincida con el password, en caso de que no exista el mail tira una exception de null pointer
            if(repo.findByEmailAndPassword(email,new BCryptPasswordEncoder().encode(user.getPassword()))==null){
                    throw new NullPointerException("El password no coincide");
            }
        }
    return user;
    }

    @Override
    public User create(User user) {
       //si no existe el email ese en la bd lo guardo
        if(!repo.existsByEmail(user.getEmail())){
           user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
           return repo.save(user);
       }
       return user;
    }

}
