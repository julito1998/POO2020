package com.TuReserva2020.Reserva.Service;

import com.TuReserva2020.Reserva.Model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author Julito
 */
public interface IUserService extends UserDetailsService {
    User create(User user);


}
