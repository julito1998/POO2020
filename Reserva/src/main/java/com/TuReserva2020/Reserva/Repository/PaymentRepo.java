package com.TuReserva2020.Reserva.Repository;

import com.TuReserva2020.Reserva.Model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepo extends JpaRepository<Payment,Long> {
}
