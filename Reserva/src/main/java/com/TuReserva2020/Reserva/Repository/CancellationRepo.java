package com.TuReserva2020.Reserva.Repository;

import com.TuReserva2020.Reserva.Model.Cancellation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CancellationRepo extends JpaRepository<Cancellation,Long> {
}
