package com.TuReserva2020.Reserva.Service;

import com.TuReserva2020.Reserva.Model.Payment;
import com.TuReserva2020.Reserva.Repository.PaymentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PaymentService implements  IPaymentService{

    @Autowired
    private PaymentRepo paymentRepo;


    @Override
    public void deleteByIdBooking(Long idBooking) throws Exception {
        try {
            paymentRepo.deleteByIdBooking(idBooking);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Payment NewPayment(Payment payment) throws Exception {
        try {
            payment.setCreatedAt(new Date());
            payment=paymentRepo.save(payment);
            if (payment == null){
                throw new Exception("Error saving payment.");
            }
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return payment;
    }


}
