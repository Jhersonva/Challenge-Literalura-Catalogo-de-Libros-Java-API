package com.desafioalura.literatura.repository;

import com.desafioalura.literatura.model.EntidadLibros;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibroRepository extends JpaRepository<EntidadLibros, Long> {

    //Consulta para buscar detalle del libro y autor segun el idioma
    List<EntidadLibros> findByIdiomasContainingIgnoreCase(String idioma);
}
