/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TuReserva2020.Reserva.Repository;

import com.TuReserva2020.Reserva.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Julito
 */
public interface UserRepo extends JpaRepository<User,Long> {
    
}
