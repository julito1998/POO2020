package com.TuReserva2020.Reserva.Service;

import com.TuReserva2020.Reserva.Model.Booking;
import com.TuReserva2020.Reserva.Model.Payment;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface IBookingService {

    Booking newBooking(Booking booking) throws Exception ;
    ArrayList<Booking> listBooking() throws Exception;
    List<Booking> findBookingById(Long id) throws Exception;
    void deleteBooking(Long id_book) throws Exception;
    Booking findBookingByUserId(Long id);
    Payment newPayment(Booking booking, Date createdAt, String card, String cardNumber);
}
