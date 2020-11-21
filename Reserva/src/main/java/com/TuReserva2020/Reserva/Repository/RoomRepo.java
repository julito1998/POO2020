package com.TuReserva2020.Reserva.Repository;

import com.TuReserva2020.Reserva.Model.Room;
import com.TuReserva2020.Reserva.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepo extends JpaRepository<Room,Long> {

}
