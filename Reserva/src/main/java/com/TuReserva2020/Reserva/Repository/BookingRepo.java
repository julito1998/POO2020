package com.TuReserva2020.Reserva.Repository;

import com.TuReserva2020.Reserva.Model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
@Repository
public interface BookingRepo extends JpaRepository<Booking,Long> {
    List<Booking> findAll();


    @Query("SELECT b FROM Booking b WHERE b NOT IN (SELECT c.booking FROM Cancellation c)")
    List<Booking> findBookingNotInCancellation();


    @Query("select b from Booking b where b.user.id = :user_id and b not in " +
            "(SELECT c.booking FROM Cancellation c) order by b.checkIn desc")
            List<Booking> findBookingByUser(@Param("user_id") Long user_id);

    /*@Query("delete from Booking b where (b.user.id = :user_id) and (b.id = :booking_id) ")
            void deleteBooking(@Param("user_id") Long user_id, @Param("booking_id") Long id);*/





}
