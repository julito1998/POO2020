package com.TuReserva2020.Reserva.InterfaceService;

import com.TuReserva2020.Reserva.Model.Room;

import java.util.Date;
import java.util.List;

public interface IRoomService {
    List<Room> getRoomsAvailable(Date checkIn, Date checkOut, int occupancy);
}
