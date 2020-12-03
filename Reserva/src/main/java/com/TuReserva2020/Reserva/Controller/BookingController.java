package com.TuReserva2020.Reserva.Controller;

import com.TuReserva2020.Reserva.DTO.RoomAvailabilityDTO;
import com.TuReserva2020.Reserva.DTO.RoomDTO;
import com.TuReserva2020.Reserva.InterfaceService.IBookingService;
import com.TuReserva2020.Reserva.InterfaceService.IRoomService;

import com.TuReserva2020.Reserva.Model.Room;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/availability")
    private String getRoomsAvailable(@ModelAttribute RoomAvailabilityDTO roomsAvailabilityDTO, Model model){
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
        return "bookings/availability";
    }




}
