package com.TuReserva2020.Reserva.Controller;

import com.TuReserva2020.Reserva.DTO.UserLoginDTO;
import com.TuReserva2020.Reserva.DTO.UserNewDTO;
import com.TuReserva2020.Reserva.Service.IUserService;
import com.TuReserva2020.Reserva.Model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;
import java.util.Map;

/**
 *
 * @author Julito
 */
@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @Qualifier("userService")
    @Autowired
    private UserDetailsService userDetailService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/new")
    public String userNew(Model model){
        model.addAttribute("user", new UserNewDTO());
        return "users/new";
    }

    @PostMapping("/new")
    public String regist(@ModelAttribute UserNewDTO userDTO, Model model){
         try{
             User user = modelMapper.map(userDTO, User.class);
             user.setBirthDate(userDTO.getBirthDateInDateConverted());
             userService.create(user);
             return "/users/login";
        }catch(UsernameNotFoundException errorU){
            model.addAttribute("error",errorU.getMessage());
            return "/error";
        }catch (Exception e){
             model.addAttribute("error",e.getMessage());
             return "/error";
         }
    }

    @GetMapping("/login")
    public String userLogin(Model model){
        model.addAttribute("user", new UserLoginDTO());
        return "/users/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserLoginDTO user, Model model) {
       try {
            userService.loadUserByUsername(user.getEmail());
            return "/home";
        } catch (UsernameNotFoundException ex) {
           model.addAttribute("error",ex.getMessage());
           return "/error";

        }
    }

}
