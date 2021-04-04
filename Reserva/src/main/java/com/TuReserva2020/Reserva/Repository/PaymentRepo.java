package com.TuReserva2020.Reserva.Repository;

import com.TuReserva2020.Reserva.Model.Booking;
import com.TuReserva2020.Reserva.Model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepo extends JpaRepository<Payment,Long> {
    //deleteByIdBooking
    @Query("DELETE from Payment p where p.booking.id = :booking_id")
    void deleteByIdBooking(@Param("booking_id") Long booking_id);
}
