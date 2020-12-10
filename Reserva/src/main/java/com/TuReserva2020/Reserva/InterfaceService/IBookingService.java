package com.TuReserva2020.Reserva.InterfaceService;

import com.TuReserva2020.Reserva.Model.Booking;

import java.lang.reflect.Array;
import java.util.ArrayList;

public interface IBookingService {

    Booking newBooking(Booking booking) throws Exception ;
    ArrayList<Booking> listBooking() throws Exception;

}
