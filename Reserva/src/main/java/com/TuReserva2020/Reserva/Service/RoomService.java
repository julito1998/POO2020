package com.TuReserva2020.Reserva.Service;

import com.TuReserva2020.Reserva.Model.Room;
import com.TuReserva2020.Reserva.Repository.RoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public class RoomService implements IRoomService {

    @Autowired
    private RoomRepo repo;

    @Override
    public List<Room> getRoomsAvailable(Date checkIn, Date checkOut, int occupancy) {
        return repo.findRoomAvailable(checkIn,checkOut,occupancy);
    }

    @Override
    public Optional<Room> findById(long id) {
        return repo.findById(id);
    }
}
