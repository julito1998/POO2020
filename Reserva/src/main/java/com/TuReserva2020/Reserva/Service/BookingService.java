package com.TuReserva2020.Reserva.Service;


import com.TuReserva2020.Reserva.Model.Booking;
import com.TuReserva2020.Reserva.Model.Payment;
import com.TuReserva2020.Reserva.Model.Room;
import com.TuReserva2020.Reserva.Repository.BookingRepo;
import com.TuReserva2020.Reserva.Repository.PaymentRepo;
import com.TuReserva2020.Reserva.Repository.RoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BookingService implements IBookingService {

    @Autowired
    private BookingRepo bookingRepo;

    @Autowired
    private PaymentRepo paymentRepo;


    @Autowired
    private RoomRepo roomRepo;


    @Override
    public Booking newBooking(Booking booking) throws Exception {
        booking.setRoom(roomRepo.findById(booking.getRoom().getId()).get());
        booking.setCost(booking.getRoom().getPrice());
        booking.setCreatedAt(new Date());

        if(booking.getCheckIn().before(new Date())){
            throw new Exception ("La fecha de check in es anterior a la fecha actual");
        }
        if (booking.getCheckIn().after(booking.getCheckOut())) {
            throw new Exception ("la fecha de check in es posterior a la de check out");
        }

        Room r =roomRepo.isRoomAvailable(booking.getCheckIn(), booking.getCheckOut(), booking.getRoom().getId());

        if(r!=null){
            return bookingRepo.save(booking);
        }
        else{
            throw new Exception ("La habitación ya esta reservada o no se encuentra disponible");
        }
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

    public Booking findBookingByUserId(Long id){
        return bookingRepo.findBookingByUserId(id);
    }


    public Payment newPayment(Booking booking, Date createdAt, String card, String cardNumber){
        Payment payment = new Payment();
        payment.setBooking(booking);
        payment.setCreatedAt(createdAt);
        payment.setCard(card);
        payment.setCardNumber(cardNumber);
        return paymentRepo.save(payment);

    }


    //Metodo para eliminar usuario
    public void deleteBooking(Long id) throws Exception {
        Booking booking = bookingRepo.findById(id)
                .orElseThrow(() -> new Exception("Booking not Found in delete Booking"));

        Date fecha = new Date();

        bookingRepo.delete(booking);

        /*if((booking.getCheckIn().getDay() - fecha.getDay() <= 2)) {
            throw new Exception ("No se puede cancelar la reserva, por que " +
                    "no cumple con la condicion establecida : 'Sólo podrá cancelar aquellas reservas " +
                    "con 48 hs o más de antelación respecto de la fecha de ingreso (check-in)'");}
        else{
            bookingRepo.delete(booking);}*/

    }




}
