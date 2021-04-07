package com.TuReserva2020.Reserva.Service;

import com.TuReserva2020.Reserva.Model.Cancellation;

import java.util.List;

public interface ICancellationService {
    List <Cancellation> findCancellationsByBooking(Long idBooking);
}
