package com.TuReserva2020.Reserva.Service;

import com.TuReserva2020.Reserva.Model.Cancellation;
import com.TuReserva2020.Reserva.Repository.CancellationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CancellationService implements ICancellationService{

    @Autowired
    private CancellationRepo cancellationRepo;

    @Override
    public List<Cancellation> findCancellationsByBooking(Long idBooking) {
        return cancellationRepo.findCancellationByBooking(idBooking);
    }
}
