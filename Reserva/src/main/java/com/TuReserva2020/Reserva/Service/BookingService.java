package com.TuReserva2020.Reserva.Service;

import com.TuReserva2020.Reserva.Model.Booking;
import com.TuReserva2020.Reserva.Model.Cancellation;
import com.TuReserva2020.Reserva.Model.Room;
import com.TuReserva2020.Reserva.Repository.BookingRepo;
import com.TuReserva2020.Reserva.Repository.CancellationRepo;
import com.TuReserva2020.Reserva.Repository.PaymentRepo;
import com.TuReserva2020.Reserva.Repository.RoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
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

    //esta es una clase, se utiliza cada 24hs


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

        /*if(booking.getCheckIn().before(new Date())){
            throw new Exception ("La fecha de check in es anterior a la fecha actual");
        }*/
        if (booking.getCheckIn().after(booking.getCheckOut())) {
            throw new Exception ("the check in date is after the check out date");
        }

        Room r =roomRepo.isRoomAvailable(booking.getCheckIn(), booking.getCheckOut(), booking.getRoom().getId());

        if(r!=null){
            return bookingRepo.save(booking);
        }
        else{
            throw new Exception ("The room is already reserved or is not available");
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
    @Override
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
                    throw new Exception ("It is impossible to cancel the reservation with less than two days in advance.");
                }

            }
            else{
                throw new Exception ("Unable to cancel an old reservation.");
            }
        }
        catch (Exception e) {
            throw new Exception (e.getMessage());
        }

    }

    @Override
    @Scheduled(fixedDelay = 86400000L)
    public void cancelBookingTemp() throws InterruptedException {
        Calendar calCheckIn = Calendar.getInstance();
        Calendar fechaActual = Calendar.getInstance();
        Date fechaDateActual = new Date();
        int milisecondsByDay = 86400000;

        try {
            //recorro todas las reservas que no estan canceladas
            bookingRepo.findBookingNotInCancellation().stream().forEach(b -> {
                calCheckIn.setTime(b.getCheckIn());
                //si la fecha de reserva es del mismo año o mayor, del mismo mes o mayor y el dia de la check_in es menor al dia actual
                /**
                 * if (fechaActual.get(Calendar.YEAR) <= calCheckIn.get(Calendar.YEAR) &&
                 * fechaActual.get(Calendar.MONTH) <= calCheckIn.get(Calendar.MONTH) && fechaActual.get(Calendar.DAY_OF_MONTH) > calCheckIn.get(Calendar.DAY_OF_MONTH)) {
                 **/
                //si la diferencia entra la fecha actual y la fecha de check_in es mayor o igual a 2 se cancela la reserva
                if (Math.floor((fechaDateActual.getTime() - b.getCheckIn().getTime()) / milisecondsByDay) >= 2) {
                    Booking booking = bookingRepo.findById(b.getId()).get();
                    //tambien elimino los medios de pago de la reserva, si los hubiera, si no los hubiera no hay problema.
                    paymentRepo.deleteByIdBooking(booking.getId());
                    Cancellation cancellation = new Cancellation();
                    cancellation.setBooking(booking);
                    cancellation.setCreatedAt(new Date());
                    cancellationRepo.save(cancellation);
                }
                else {
                    System.out.println("It was not eliminated");
                }
                //}
            });


        } catch (Exception e) {

        }
    }

}
