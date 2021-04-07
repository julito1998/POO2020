package com.TuReserva2020.Reserva.Service;

import com.TuReserva2020.Reserva.Model.Payment;

public interface IPaymentService {
    void deleteByIdBooking(Long idBooking) throws Exception;
    Payment NewPayment(Payment payment) throws Exception;
}
