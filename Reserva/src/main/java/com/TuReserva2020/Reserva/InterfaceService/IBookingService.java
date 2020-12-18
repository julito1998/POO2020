package com.TuReserva2020.Reserva.InterfaceService;

import com.TuReserva2020.Reserva.Model.Booking;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public interface IBookingService {

    Booking newBooking(Booking booking) throws Exception ;
    ArrayList<Booking> listBooking() throws Exception;
    List<Booking> findBookingById(Long id) throws Exception;
    void deleteBooking(Long id_book, Long id_user) throws Exception;

}
