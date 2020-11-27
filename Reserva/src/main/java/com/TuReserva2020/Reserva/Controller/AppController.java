package com.TuReserva2020.Reserva.Controller;

import com.TuReserva2020.Reserva.DTO.UserLoginDTO;
import com.TuReserva2020.Reserva.InterfaceService.IUserService;
import com.TuReserva2020.Reserva.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/App")
public class AppController {

    @GetMapping("/home")
    public String home(){
        return "App/home";
    }

    @GetMapping("/principal")
    public String principal(){
        return "App/principal";
    }


}
