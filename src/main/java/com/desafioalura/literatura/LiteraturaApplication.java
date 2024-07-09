package com.desafioalura.literatura;

import com.desafioalura.literatura.principal.Principal;
import com.desafioalura.literatura.repository.AutorRepository;
import com.desafioalura.literatura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class LiteraturaApplication implements CommandLineRunner {

	@Autowired
	private LibroRepository repositorioLibro;
	@Autowired
	private AutorRepository repositorioAutor;


	public static void main(String[] args) {
		SpringApplication.run(LiteraturaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		//Creando una instancia de mi clase Principal
		Principal mostrarMenu = new Principal(repositorioLibro, repositorioAutor);
		mostrarMenu.muestraElMenu();
	}
}
