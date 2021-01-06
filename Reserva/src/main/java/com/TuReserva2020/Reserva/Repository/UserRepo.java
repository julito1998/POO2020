package com.TuReserva2020.Reserva.Repository;

<<<<<<< HEAD
import com.TuReserva2020.Reserva.Model.Booking;
import com.TuReserva2020.Reserva.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
=======
import com.TuReserva2020.Reserva.Model.Room;
import com.TuReserva2020.Reserva.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.jpa.repository.Modifying;
>>>>>>> origin/master
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

<<<<<<< HEAD
=======
import java.util.Date;
>>>>>>> origin/master
import java.util.List;

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

<<<<<<< HEAD


=======
>>>>>>> origin/master
}
