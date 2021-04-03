package com.TuReserva2020.Reserva.Repository;

import com.TuReserva2020.Reserva.Model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PaymentRepo extends JpaRepository<Payment,Long> {


    @Transactional
    @Modifying
    @Query("DELETE FROM Payment WHERE booking.id=:book_id")
    void deleteByBookId(@Param("book_id") Long book_id);

}
