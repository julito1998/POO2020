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
public class RaizController {

    @Autowired
    private UserService userService;

    @GetMapping("/home")
    public String home(){
        return "users/home";
    }

    //este HTML es STATIC
    @GetMapping
    public String principal(){
        return "users/principal";
    }

    //INVESTIGAR COMO SOBREESCRIBIR EL LOGIN DE SPRING SECURITY PARA LA ENTREGA FINAL.
    @GetMapping("/login")
    public String userLogin(Model model){
        model.addAttribute("user", new UserLoginDTO());
        return "index";
    }


    @PostMapping("/login")
    private String login(@ModelAttribute UserLoginDTO user) {
        try {
            userService.loadUserByUsername(user.getEmail());
            return "redirect:/home";
        } catch (UsernameNotFoundException ex) {
            return  "/login?error=true";
        }
    }
}
