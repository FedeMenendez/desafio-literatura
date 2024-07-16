package com.aluracursos.desafio.literatura;

import com.aluracursos.desafio.literatura.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DesafioLiteraturaApplication implements CommandLineRunner {
	public static void main(String[] args) {SpringApplication.run(DesafioLiteraturaApplication.class, args);}

		public void run(String... args) throws Exception {
			Principal principal= new Principal();
			principal.muestraMenu();
	}

}
