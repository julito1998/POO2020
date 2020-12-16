package com.TuReserva2020.Reserva.Controller;

import com.TuReserva2020.Reserva.DTO.*;
import com.TuReserva2020.Reserva.InterfaceService.IBookingService;
import com.TuReserva2020.Reserva.InterfaceService.IRoomService;

import com.TuReserva2020.Reserva.Model.Booking;
import com.TuReserva2020.Reserva.Model.Room;
import com.TuReserva2020.Reserva.Model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    private String roomsAvailability(Model model){
        model.addAttribute("roomsAvailability", new RoomAvailabilityDTO());
        model.addAttribute("rooms", new ArrayList<RoomDTO>());
        return "bookings/availability";
    }

    @GetMapping("/availabilityPublic")
    private String roomsAvailabilityPublic(Model model){
        model.addAttribute("roomsAvailability", new RoomAvailabilityDTO());
        model.addAttribute("rooms", new ArrayList<RoomDTO>());
        return "bookings/availabilityPublic";
    }

    private void loadModelRooms(@ModelAttribute RoomAvailabilityDTO roomsAvailabilityDTO, Model model){
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
    private String getRoomsAvailablePublic(@ModelAttribute RoomAvailabilityDTO roomsAvailabilityDTO, Model model){
        loadModelRooms(roomsAvailabilityDTO,model);
        return "bookings/availabilityPublic";
    }

    @PostMapping("/availability")
    private String getRoomsAvailable(@ModelAttribute RoomAvailabilityDTO roomsAvailabilityDTO, Model model){
        loadModelRooms(roomsAvailabilityDTO,model);
        return "bookings/availability";
    }


    @PostMapping("/new")
    public String newBooking(@ModelAttribute NewBookingRequestDTO newBookingRequestDTO,
                             Model model){
        NewBookingResponseDTO booking = new NewBookingResponseDTO();
        RoomDTO roomDTO = modelMapper.map(serviceRoom.findById(newBookingRequestDTO.getRoomId()).get(), RoomDTO.class);
        booking.setRoom(roomDTO);
        booking.setCheckIn(newBookingRequestDTO.getCheckIn());
        booking.setCheckOut(newBookingRequestDTO.getCheckOut());
        booking.setOccupancy(newBookingRequestDTO.getOccupancy());
        model.addAttribute("booking", booking);
        return("bookings/new");
    }

    @PostMapping("/confirm")
    public String createBooking(@ModelAttribute ConfirmBookingRequestDTO confirmBookingRequestDTO,
                                Authentication authentication, Model model){
        Booking booking = modelMapper.map(confirmBookingRequestDTO, Booking.class);
        booking.setId(null);
        booking.setUser((User)authentication.getPrincipal());
        try {
            serviceBooking.newBooking(booking);
            return ("bookings/confirm");
        }
        catch (Exception e){

            return ("bookings/availability");
        }
    }

    @GetMapping("/reserves")
    private String roomsBookings(Model model){
        try {
            ArrayList<Booking> booking = serviceBooking.listBooking();
            model.addAttribute("reserves", booking);
            return ("bookings/reserves");
        }catch(Exception e){
            return ("App/home");
        }
    }











}
