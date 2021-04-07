package com.TuReserva2020.Reserva.Service;

import com.TuReserva2020.Reserva.Model.Booking;
import com.TuReserva2020.Reserva.Model.Cancellation;
import com.TuReserva2020.Reserva.Model.Payment;
import com.TuReserva2020.Reserva.Model.Room;
import com.TuReserva2020.Reserva.Repository.BookingRepo;
import com.TuReserva2020.Reserva.Repository.CancellationRepo;
import com.TuReserva2020.Reserva.Repository.PaymentRepo;
import com.TuReserva2020.Reserva.Repository.RoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookingService implements IBookingService {

    @Autowired
    private BookingRepo bookingRepo;

    @Autowired
    private PaymentRepo paymentRepo;

    @Autowired
    private CancellationRepo cancellationRepo;

    @Autowired
    private RoomRepo roomRepo;

    @Override
    public Booking newBooking(Booking booking) throws Exception {
        booking.setRoom(roomRepo.findById(booking.getRoom().getId()).get());

        booking.setCost(booking.getRoom().getPrice());

        if (booking.isBreakfastIncluded()){
            booking.setCost(booking.getCost()+400);
        }
        if (booking.isFreeCancelation()){
            booking.setCost(booking.getCost()+100);
        }
        if (booking.isParking()){
            booking.setCost(booking.getCost()+500);
        }

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
    @Override
    public List<Booking> findBookingByIdUser(Long id) throws Exception{
        List bookings = bookingRepo.findBookingByUser(id);
        return bookings;
    }


    @Override
    public Optional<Booking> findById(Long id) {
        return bookingRepo.findById(id);
    }

    //Metodo para eliminar reserva
    public void cancelBooking(Long id, Date check_in) throws Exception {
        Calendar cal_check_in = null;
        Date fechaDateActual = new Date();
        int milisecondsByDay = 86400000;
        try {
            cal_check_in=Calendar.getInstance();
            cal_check_in.setTime(check_in);
            Calendar fecha_actual= Calendar.getInstance();
            //new GregorianCalendar();
            if (fecha_actual.get(Calendar.YEAR) <= cal_check_in.get(Calendar.YEAR) &&
                    fecha_actual.get(Calendar.MONTH) <= cal_check_in.get(Calendar.MONTH) && fecha_actual.get(Calendar.DAY_OF_MONTH) < cal_check_in.get(Calendar.DAY_OF_MONTH))
            {
                if (Math.floor ((check_in.getTime()- fechaDateActual.getTime()) / milisecondsByDay) >= 2){
                    Booking booking = bookingRepo.findById(id)
                            .orElseThrow(() -> new Exception("Booking not Found in cancel Booking"));
                    //tambien elimino los medios de pago de la reserva
                    paymentRepo.deleteByIdBooking(booking.getId());
                    Cancellation cancellation = new Cancellation();
                    cancellation.setBooking(booking);
                    cancellation.setCreatedAt(new Date());
                    cancellationRepo.save(cancellation);
                    //bookingRepo.delete(booking);
                }
                else{
                    throw new Exception ("Imposible cancelar la reserva con menos de dos dias de anticipación.");
                }

            }
            else{
                throw new Exception ("Imposible cancelar una reserva antigua.");
            }
        }
        catch (Exception e) {
            throw new Exception (e.getMessage());
        }

    }

}
