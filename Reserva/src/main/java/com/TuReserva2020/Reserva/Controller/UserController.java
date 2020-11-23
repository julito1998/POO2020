package com.TuReserva2020.Reserva.Controller;

import com.TuReserva2020.Reserva.InterfaceService.IUserService;
import com.TuReserva2020.Reserva.Model.User;
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

    //GET (obtener), POST (guardar), PUT (actualizar), DELETE  --> estado 200 la peticion se realizo correctamente
    //estado= 500 error de logica 
    //estado=404 error como por ejemplo rutas
    // la ruta quedaria:
    // http://localhost:8080/user/guardar
//    @RequestMapping(value = "/new", method = { RequestMethod.GET, RequestMethod.POST })

    @GetMapping("/home")
    public String home(){
        return "users/home";
    }


    @GetMapping("/new")
    public String userNew(Model model){
        model.addAttribute("user", new User());
        return "users/new";
    }



    @GetMapping("/login")
    public String userLogin(Model model){
        model.addAttribute("user", new User());
        return "users/login";
    }


    @PostMapping("/new")
    public String regist(@ModelAttribute User user){
         try{
            userService.create(user);
            return "redirect:/users/login";
        }catch(UsernameNotFoundException errorU){
            return "redirect:/users/new";
        }
    }


   @GetMapping("/principal")
    public String principal(){
        return "users/principal";
    }



    @PostMapping("/login")
    private String login(@ModelAttribute User user){
        try{
            userDetailService.loadUserByUsername(user.getEmail());
            return "redirect:/users/home";
        }catch(UsernameNotFoundException ex){
            return "redirect:/users/login";
        }catch (Exception e){
            return "redirect:/users/login";
        }


    }

    
    
}
