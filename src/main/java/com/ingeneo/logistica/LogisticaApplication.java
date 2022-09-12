package com.ingeneo.logistica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class LogisticaApplication implements CommandLineRunner {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(LogisticaApplication.class, args);
	}

	/**
	 * Generea contraseñas encryptadas para guardar en la base de datos y hacer pruebas, intentar desacoplar esto y e
	 * en el la creacion de nuevo usuario generar y guardar en la BBDD
	 * @param args
	 * @throws Exception
	 */
	@Override
	public void run(String... args) throws Exception {
		String password1 = "2022";
		String password2 = "2012";
		System.out.println(passwordEncoder.encode(password1));
		System.out.println(passwordEncoder.encode(password2));
	}
}
