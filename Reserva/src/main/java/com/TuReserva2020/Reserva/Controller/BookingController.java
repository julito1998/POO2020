package com.TuReserva2020.Reserva.Controller;

import com.TuReserva2020.Reserva.DTO.RoomAvailabilityDTO;
import com.TuReserva2020.Reserva.DTO.RoomDTO;
import com.TuReserva2020.Reserva.InterfaceService.IBookingService;
import com.TuReserva2020.Reserva.InterfaceService.IRoomService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    private String roomsAvailabily(Model model){
        model.addAttribute("roomAvailability", new RoomAvailabilityDTO());
        model.addAttribute("rooms",new RoomDTO());
        return "bookings/availability";
    }

    /*@PostMapping("/availability")
    private String getRoomsAvailable(@ModelAttribute)*/




}
