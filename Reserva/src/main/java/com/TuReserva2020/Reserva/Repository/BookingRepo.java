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

    @Query("select b from Booking b WHERE b.id = " +
            "(select max(b.id) from Booking b where b.user.id = :user_id)")
            Booking findLastBookingByUserId(@Param("user_id") Long user_id);


    @Query("select b from Booking b where b.user.id = :user_id order by b.checkIn")
            List<Booking> findBookingByUser(@Param("user_id") Long user_id);

    /*@Query("delete from Booking b where (b.user.id = :user_id) and (b.id = :booking_id) ")
            void deleteBooking(@Param("user_id") Long user_id, @Param("booking_id") Long id);*/





}
