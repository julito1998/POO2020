package com.TuReserva2020.Reserva.Service;

import com.TuReserva2020.Reserva.Model.Room;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IRoomService {
    List<Room> getRoomsAvailable(Date checkIn, Date checkOut, int occupancy);
    Optional<Room> findById(long id);
}
