/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TuReserva2020.Reserva.DTO;

import java.io.Serializable;

/**
 *
 * @author Julito
 */
public class UserLoginDTO implements Serializable {

    private String password;
    private String email;


    public UserLoginDTO(String password, String email) {
        super();
        this.password = password;
        this.email = email;
    }

    public UserLoginDTO() {
        super();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}

