package com.TuReserva2020.Reserva.Service;

import com.TuReserva2020.Reserva.InterfaceService.IBookingService;
import com.TuReserva2020.Reserva.Model.Booking;
import com.TuReserva2020.Reserva.Model.Room;
import com.TuReserva2020.Reserva.Repository.BookingRepo;
import com.TuReserva2020.Reserva.Repository.RoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BookingService implements IBookingService {

    @Autowired
    private BookingRepo bookingRepo;

    @Autowired
    private RoomRepo roomRepo;


    @Override
    public Booking newBooking(Booking booking) throws Exception {
        booking.setRoom(roomRepo.findById(booking.getRoom().getId()).get());
        booking.setCost(booking.getRoom().getPrice());
        booking.setCreatedAt(new Date());

        if(booking.getCheckIn().before(new Date())
                ||booking.getCheckIn().after(booking.getCheckOut())){
            throw new Exception ("La fecha de check in es anterior a la fecha actual o " +
                    "la fecha de check in es posterior a la de check out");}

        Room r =roomRepo.isRoomAvailable(booking.getCheckIn(), booking.getCheckOut(), booking.getRoom().getId());


        if(r!=null){
            return booking = bookingRepo.save(booking);
        }
        else{throw new Exception ("La habitación ya esta reservada o no se encuentra disponible");}
    }

    @Override
    public ArrayList<Booking> listBooking() throws Exception{
        List bookings = bookingRepo.findAll();
      return (ArrayList<Booking>) bookings;
    }


    //Listar reservas que hizo un usuario determinado
    public List<Booking> findBookingById(Long id) throws Exception{
        List bookings = bookingRepo.findBookingByUser(id);
        return bookings;
    }


    //Metodo para elimanr usuario
    public void deleteBooking(Long id) throws Exception {
        Booking booking = bookingRepo.findById(id)
                .orElseThrow(() -> new Exception("BookingnotFound in deleteBooking"));

        bookingRepo.delete(booking);
    }




}
