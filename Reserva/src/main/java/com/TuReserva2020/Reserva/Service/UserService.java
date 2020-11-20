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
            throw new UsernameNotFoundException("El mail: "+ email +" no existe");
        }
        //si existe el mail debo comprobar que coincida con el password, en caso de que no exista el mail tira una exception de null pointer
        if(repo.findByPassword(new BCryptPasswordEncoder().encode(user.getPassword()))==null){
                throw new NullPointerException("El password: "+user.getPassword()+" no coincide con el mail: " +user.getEmail());
        }
        return user;
    }

    @Override
    public User create(User user) throws UsernameNotFoundException {
       //si no existe el email ese en la bd lo guardo

        if(!repo.existsByEmail(user.getEmail())){
           user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
           user=repo.save(user);
       }
        else{
            throw new UsernameNotFoundException("El mail : "+ user.getEmail() +" ya esta asociado a un usuario");
        }
       return user;
    }

}
