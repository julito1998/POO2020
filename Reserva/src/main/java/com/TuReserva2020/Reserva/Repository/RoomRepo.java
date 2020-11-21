package com.TuReserva2020.Reserva.Repository;


import com.TuReserva2020.Reserva.Model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface RoomRepo extends JpaRepository<Room,Long> {
    @Query("select r from Room r where r.occupancy>= :occupancy and" +
            "r.availability >(Select count(b) From Booking b " +
            "                   where b.room=r and b.checkIn between :checkIn ad :checkOut")
    List<Room> findRoomOccupancyInDate(Date checkIn, Date checkOut, int occupancy);
}
