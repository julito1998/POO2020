package com.TuReserva2020.Reserva.Controller;

import com.TuReserva2020.Reserva.DTO.UserLoginDTO;
import com.TuReserva2020.Reserva.InterfaceService.IUserService;
import com.TuReserva2020.Reserva.Model.User;
import com.TuReserva2020.Reserva.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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




    @GetMapping("/new")
    public String userNew(Model model){
        model.addAttribute("user", new User());
        return "users/new";
    }

    @PostMapping("/new")
    public String regist(@ModelAttribute User user){
         try{
            userService.create(user);
            return "redirect:/login";
        }catch(UsernameNotFoundException errorU){
            return "/new?error=true";
        }
    }

    @GetMapping("/login")
    public String userLogin(Model model){
        model.addAttribute("user", new UserLoginDTO());
        return "/users/login";
    }

    @PostMapping("/login")
    private String login(@ModelAttribute UserLoginDTO user) {
        try {
            userService.loadUserByUsername(user.getEmail());
            return "/App/home";
            //return "/mostramealgo";
        } catch (UsernameNotFoundException ex) {
            return  "/login?error=true";
        }
    }

}
