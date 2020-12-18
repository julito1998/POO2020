package com.TuReserva2020.Reserva.Repository;

import com.TuReserva2020.Reserva.Model.Booking;
import com.TuReserva2020.Reserva.Model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.Date;
import java.util.List;
@Repository
public interface BookingRepo extends JpaRepository<Booking,Long> {
    List<Booking> findAll();


    @Query("select b from Booking b where b.user.id = :user_id ")
            List<Booking> findBookingByUser(@Param("user_id") Long user_id);



}
