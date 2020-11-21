package com.TuReserva2020.Reserva.Repository;

import com.TuReserva2020.Reserva.Model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public interface BookingRepo extends JpaRepository<Booking,Long> {
    List<Booking> findAll();
    @Query("select b.cost from Booking b inner join Room r ON b.room=r.room  where b.checkIn between ?1 and ?2 and  r.occupancy<=?3")
    List<Booking> findAllNombresEmpleado(String checkIn, String checkOut, int occupancytRoom);
}
