package com.TuReserva2020.Reserva.Service;

import com.TuReserva2020.Reserva.InterfaceService.IRoomService;
import com.TuReserva2020.Reserva.Model.Room;
import com.TuReserva2020.Reserva.Repository.RoomRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class RoomService implements IRoomService {

    @Autowired
    private RoomRepo repo;

    @Override
    public List<Room> getRoomsAvailable(Date checkIn, Date checkOut, int occupancy) {
        return repo.findRoomOccupancyInDate(checkIn,checkOut,occupancy);
    }
}
