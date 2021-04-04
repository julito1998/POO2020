package com.TuReserva2020.Reserva.Repository;

import com.TuReserva2020.Reserva.Model.Booking;
import com.TuReserva2020.Reserva.Model.Cancellation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CancellationRepo extends JpaRepository<Cancellation,Long> {

}
