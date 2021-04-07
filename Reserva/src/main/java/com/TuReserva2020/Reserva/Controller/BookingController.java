package com.TuReserva2020.Reserva.Controller;

import com.TuReserva2020.Reserva.DTO.*;
import com.TuReserva2020.Reserva.Service.IBookingService;
import com.TuReserva2020.Reserva.Service.IRoomService;
import com.TuReserva2020.Reserva.Model.Booking;
import com.TuReserva2020.Reserva.Model.Room;
import com.TuReserva2020.Reserva.Model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private IRoomService serviceRoom;

    @Autowired
    private IBookingService serviceBooking;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/availability")
    public String roomsAvailability(Model model){
        model.addAttribute("roomsAvailability", new RoomAvailabilityDTO());
        model.addAttribute("rooms", new ArrayList<RoomDTO>());
        return "bookings/availability";
    }

    @GetMapping("/availabilityPublic")
    public String roomsAvailabilityPublic(Model model){
        model.addAttribute("roomsAvailability", new RoomAvailabilityDTO());
        model.addAttribute("rooms", new ArrayList<RoomDTO>());
        return "bookings/availability_public";
    }

    public void loadModelRooms(@ModelAttribute RoomAvailabilityDTO roomsAvailabilityDTO, Model model){
        List<Room> rooms = new ArrayList<>();
        try{
            rooms= serviceRoom.getRoomsAvailable(
                    roomsAvailabilityDTO.getCheckInDateConverted(),
                    roomsAvailabilityDTO.getCheckOutDateConverted(),
                    roomsAvailabilityDTO.getOccupancy());
        }catch (Exception e){}

        List<RoomDTO> roomDTO=rooms.stream()
                .map(room -> modelMapper.map(room, RoomDTO.class))
                .collect(Collectors.toList());
        model.addAttribute("rooms", roomDTO);
        model.addAttribute("roomsAvailability", roomsAvailabilityDTO);
    }

    @PostMapping("/availabilityPublic")
    public String getRoomsAvailablePublic(@ModelAttribute RoomAvailabilityDTO roomsAvailabilityDTO, Model model){
        loadModelRooms(roomsAvailabilityDTO,model);
        return "bookings/availability_public";
    }

    @PostMapping("/availability")
    public String getRoomsAvailable(@ModelAttribute RoomAvailabilityDTO roomsAvailabilityDTO, Model model){
        loadModelRooms(roomsAvailabilityDTO,model);
        return "bookings/availability";
    }


    @PostMapping("/new_reserve")
    public String newBooking(@ModelAttribute NewBookingRequestDTO newBookingRequestDTO,
                             Model model){
        try{
        NewBookingResponseDTO booking = new NewBookingResponseDTO();
        RoomDTO roomDTO = modelMapper.map(serviceRoom.findById(newBookingRequestDTO.getRoomId()).get(), RoomDTO.class);
        booking.setRoom(roomDTO);
        booking.setCheckIn(newBookingRequestDTO.getCheckIn());
        booking.setCheckOut(newBookingRequestDTO.getCheckOut());
        booking.setOccupancy(newBookingRequestDTO.getOccupancy());
        model.addAttribute("booking", booking);
        }catch (Exception e){
                e.getMessage();
        }
        return("/bookings/new_reserve");
    }

    @PostMapping("/confirm_reserve")
    public String createBooking(@ModelAttribute ConfirmBookingRequestDTO confirmBookingRequestDTO,
                                Authentication authentication, Model model){

        Booking booking = modelMapper.map(confirmBookingRequestDTO, Booking.class);
        booking.setId(null);
        booking.setUser((User)authentication.getPrincipal());

        try {
            booking=serviceBooking.newBooking(booking);
            model.addAttribute("id_booking", booking.getId());
            model.addAttribute("error", null);
        }
        catch (Exception e){
            model.addAttribute("error", e.getMessage());
        }
        return ("bookings/confirm_reserve");
    }

    @GetMapping("/reserves")
    public String roomsBookings(Model model, Authentication authentication){
        User sessionUser = (User)authentication.getPrincipal();
        try {
            List<Booking> booking = serviceBooking.findBookingByIdUser(sessionUser.getId());
            List<BookingDTO> bookingDTO=booking.stream()
                    .map(book -> modelMapper.map(book, BookingDTO.class))
                    .collect(Collectors.toList());


            bookingDTO.stream().forEach(b ->{
                b.setCheckInDate((serviceBooking.findById(b.getId())).get().getCheckIn());
                b.setCheckOutInDate((serviceBooking.findById(b.getId())).get().getCheckOut());
                b.setCreatedAtInDate((serviceBooking.findById(b.getId())).get().getCreatedAt());
                b.setCost(Math.round(b.getCost() * 100) / 100d);
            });


            model.addAttribute("reserves", bookingDTO);
            return ("bookings/reserves");
        }catch(Exception e){
            return ("redirect:/home");
        }
    }

    @GetMapping("/cancel_reserves")
    public String bookingsToCancel(@RequestParam (value = "id") Long id,Model model){
        Booking reserve=serviceBooking.findById(id).get();
        BookingDTO bookingDTO = modelMapper.map(reserve, BookingDTO.class);
        model.addAttribute("reserve", bookingDTO);
        return("bookings/cancel_reserves");
    }

    /*PostMapping("/cancel_reserves")
    public String cancelBooking(@ModelAttribute Booking reserve, Model model) {
        try {
            Booking booking = serviceBooking.findById(reserve.getId()).get();
            model.addAttribute("reserve", booking);
            return ("bookings/cancel_reserves");
        } catch (Exception e) {
            return ("redirect:/bookings/reserves");
        }
    }*/


    @PostMapping("/cancel_reserves")
    public String cancelBooking(@ModelAttribute DeleteBookingDTO deleteBookingRequestDTO, Model model){
        try {
            Booking booking = modelMapper.map(deleteBookingRequestDTO, Booking.class);
            booking.setCheckIn(deleteBookingRequestDTO.getCheckInDateConverted());
            serviceBooking.cancelBooking(booking.getId(), booking.getCheckIn());
            model.addAttribute("error", null);
        }catch(Exception e){
            model.addAttribute("error", e.getMessage());
        }
        return ("bookings/confirm_cancel_reserves");
    }

    @GetMapping("/detail_reserves")
    public String detailBooking(@RequestParam (value = "id") Long id,Model model){
        try{
            Booking reserve=serviceBooking.findById(id).get();
            BookingDTO bookingDTO = modelMapper.map(reserve, BookingDTO.class);
            bookingDTO.setCheckOutInDate(reserve.getCheckOut());
            bookingDTO.setCreatedAtInDate(reserve.getCreatedAt());
            bookingDTO.setCheckInDate(reserve.getCheckIn());
            bookingDTO.setCost(Math.round(bookingDTO.getCost() * 100) / 100d);
            model.addAttribute("reserve", bookingDTO);
        }catch (Exception e){
            model.addAttribute("error",e.getMessage());
        }
        return ("bookings/detail_reserves");
    }

    /*@PostMapping("/cancel_reserves")
    public String deleteBook(@ModelAttribute Booking reserve){
        try {
            serviceBooking.deleteBooking(reserve.getId(), reserve.getCheckIn());
            return ("bookings/cancel_reserves");
        }catch(Exception e){
            return ("redirect:/bookings/reserves");
        }
    }*/













}
