package com.TuReserva2020.Reserva.Controller;

import com.TuReserva2020.Reserva.DTO.*;
import com.TuReserva2020.Reserva.InterfaceService.IBookingService;
import com.TuReserva2020.Reserva.InterfaceService.IRoomService;
import com.TuReserva2020.Reserva.InterfaceService.IUserService;
import com.TuReserva2020.Reserva.Model.Booking;
import com.TuReserva2020.Reserva.Model.Room;
import com.TuReserva2020.Reserva.Model.User;
import com.TuReserva2020.Reserva.Service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
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

  /*  @GetMapping("/reserves")
    private String roomsBookings(Model model){
        try {
            ArrayList<Booking> bookings = serviceBooking.listBooking();

            List<ConfirmBookingRequestDTO> reservesDTO=bookings.stream()
                    .map(booking -> modelMapper.map(booking, ConfirmBookingRequestDTO.class))
                    .collect(Collectors.toList());

            model.addAttribute("reserves", reservesDTO);
            //model.addAttribute("reserves", reserves);
            return ("bookings/reserves");
        }catch(Exception e){
            return ("App/home");
        }
    }*/


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
        return "bookings/availability_public";
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
        return "bookings/availability_public";
    }

    @PostMapping("/availability")
    private String getRoomsAvailable(@ModelAttribute RoomAvailabilityDTO roomsAvailabilityDTO, Model model){
        loadModelRooms(roomsAvailabilityDTO,model);
        return "bookings/availability";
    }


    @PostMapping("/new")
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
            model.addAttribute("checkIn", confirmBookingRequestDTO.getCheckIn());
            return ("bookings/confirm");
        }
        catch (Exception e){
            model.addAttribute("checkIn", null);
            model.addAttribute("error", e.getMessage());
            return ("bookings/confirm");
        }
    }

    @GetMapping("/reserves")
    private String roomsBookings(Model model, Authentication authentication){
        User sessionUser = (User)authentication.getPrincipal();
        try {
            List<Booking> booking = serviceBooking.findBookingById(sessionUser.getId());
            model.addAttribute("reserves", booking);
            return ("bookings/reserves");
        }catch(Exception e){
            return ("/home");
        }
    }


    @GetMapping("/cancel_reserves")
    private String bookingsToCancel(Model model, Authentication authentication){
        roomsBookings(model,authentication);
        return("bookings/cancel_reserves");
    }

    @PostMapping("/cancel_reserves")
    private String deleteBook(@ModelAttribute Booking reserves){
        try {
            serviceBooking.deleteBooking(reserves.getId());
            return ("redirect:/bookings/cancel_reserves");
        }catch(Exception e){
            return ("redirect:/bookings/reserves");
        }
    }













}
