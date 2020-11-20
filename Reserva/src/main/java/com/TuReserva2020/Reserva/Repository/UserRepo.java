package com.TuReserva2020.Reserva.Repository;

import com.TuReserva2020.Reserva.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Julito
 */
@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    User findByEmail(String email);
   //para chequear y no tener usuarios con el mismo nombre o email
    boolean existsByEmail(String email);
    User findByPassword(String password);
    User findByEmailAndPassword(String email, String password);
}
