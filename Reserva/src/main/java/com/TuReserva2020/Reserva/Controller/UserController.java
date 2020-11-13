package com.TuReserva2020.Reserva.Controller;
import com.TuReserva2020.Reserva.InterfaceService.IUserService;
import com.TuReserva2020.Reserva.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Julito
 */
@RestController
@RequestMapping({"/users"})
public class UserController {
    
    @Autowired
    private IUserService userService;

    //GET (obtener), POST (guardar), PUT (actualizar), DELETE  --> estado 200 la peticion se realizo correctamente
    //estado= 500 error de logica 
    //estado=404 error como por ejemplo rutas
    // la ruta quedaria:
    // http://localhost:8080/user/guardar
    
    @PostMapping({"/new"})
    public String regist(@ModelAttribute User user){
         try{
            userService.create(user);
            return "redirect:/users";
        }catch(Exception ex){
            return ex.getMessage()+" Este mail ya existe: "+user.getEmail();
        }
    }
    @GetMapping
    public String home(Model model){
        return "users/home";
    }
}
