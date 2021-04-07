package com.TuReserva2020.Reserva.Controller;

import com.TuReserva2020.Reserva.DTO.*;
import com.TuReserva2020.Reserva.Model.Payment;
import com.TuReserva2020.Reserva.Service.IBookingService;
import com.TuReserva2020.Reserva.Service.IPaymentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private IPaymentService paymentService;

    @Autowired
    private IBookingService bookingService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/payment")
    private String verVistaPago(@RequestParam (value="id_booking") Long id_booking, Model model) {
        DetailPaymentBookingRequestDTO detailPaymentBookingRequestDTO = new DetailPaymentBookingRequestDTO();
        detailPaymentBookingRequestDTO.setIdBooking(id_booking);
        model.addAttribute("payment", detailPaymentBookingRequestDTO);
        return   ("payments/payment");
    }

   @PostMapping("/payment")
    private String createPayment(@ModelAttribute DetailPaymentBookingRequestDTO payment, Model model) {
        try
        {
            BookingDTO bookingDTO = modelMapper.map(bookingService.findById(payment.getIdBooking()).get(),BookingDTO.class);
            PaymentDTO paymentDTO = new PaymentDTO();
            paymentDTO.setBooking(bookingDTO);
            paymentDTO.setCard(payment.getCard());
            paymentDTO.setCardNumber(payment.getCardNumber());
            paymentService.NewPayment(modelMapper.map(paymentDTO, Payment.class));
            model.addAttribute("checkIn", paymentDTO.getBooking().getCheckIn());
            model.addAttribute("error", null);
        }
        catch (Exception e)
        {
            model.addAttribute("id_booking", payment.getIdBooking());
            model.addAttribute("error", e.getMessage());
        }
       return ("payments/confirm_payment");
    }

   /*@GetMapping("/confirmPayment")
    private String confirmPayment(Model model){

        return ("payments/confirm_payment");
    }*/

}