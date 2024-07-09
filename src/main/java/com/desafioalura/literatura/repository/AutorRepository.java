package com.desafioalura.literatura.repository;

import com.desafioalura.literatura.model.EntidadAutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AutorRepository extends JpaRepository<EntidadAutor, Long> {

    //Query para buscar autores vivos segun el año
    @Query(value = "SELECT * FROM Tabla_Autor " +
            "WHERE fecha_de_nacimiento <= :year " +
            "AND (fecha_de_fallecimiento > :year OR fecha_de_fallecimiento IS NULL)",
            nativeQuery = true)
    List<EntidadAutor> findAliveAuthorsByYear(int year);


}
