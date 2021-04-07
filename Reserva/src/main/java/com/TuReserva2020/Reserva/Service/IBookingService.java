package com.TuReserva2020.Reserva.Service;

import com.TuReserva2020.Reserva.Model.Booking;
import com.TuReserva2020.Reserva.Model.Payment;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IBookingService {

    Booking newBooking(Booking booking) throws Exception ;
    ArrayList<Booking> listBooking() throws Exception;
    List<Booking> findBookingByIdUser(Long id) throws Exception;
    Optional <Booking> findById(Long id);
    void cancelBooking(Long id_book, Date checkIn) throws Exception;

    //Booking findLastBookingByUserId(Long id);
    //Payment newPayment(Booking booking, Date createdAt, String card, String cardNumber);

}
