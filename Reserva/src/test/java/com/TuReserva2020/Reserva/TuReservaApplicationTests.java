package com.TuReserva2020.Reserva;

import com.TuReserva2020.Reserva.Model.User;
import com.TuReserva2020.Reserva.Repository.UserRepo;
import com.TuReserva2020.Reserva.Service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class TuReservaApplicationTests {


	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private UserService service;


	@Autowired
	private UserRepo repo;

	@Test
	void contextLoads() {



		User user = repo.findByEmail("pepe@hotmail.com");
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

		repo.save(user);

	}

}
