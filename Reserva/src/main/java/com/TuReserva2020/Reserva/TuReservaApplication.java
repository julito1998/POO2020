package com.TuReserva2020.Reserva;
import com.TuReserva2020.Reserva.Model.Booking;
import com.TuReserva2020.Reserva.Model.Cancellation;
import com.TuReserva2020.Reserva.Others.Temporizador;
import com.TuReserva2020.Reserva.Repository.BookingRepo;
import com.TuReserva2020.Reserva.Repository.CancellationRepo;
import com.TuReserva2020.Reserva.Repository.PaymentRepo;
import com.TuReserva2020.Reserva.Service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

@SpringBootApplication
public class TuReservaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TuReservaApplication.class, args);
	}

	/**@Scheduled(fixedDelay = 2000L)
	void someJob() throws InterruptedException{
		BookingService bookingService=new BookingService();
		bookingService.cancelBookingTemp();
	}**/

	@Configuration
	@EnableScheduling
	@ConditionalOnProperty(name = "scheduling.enabled", matchIfMissing = true)
	class SchedulingConfiguration{

	}

}
