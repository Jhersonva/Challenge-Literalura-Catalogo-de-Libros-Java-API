package com.desafioalura.literatura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Tabla_Libros")
public class EntidadLibros {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String idiomas;
    private int numeroDeDescargas;
    @ManyToOne
    private EntidadAutor autor;

    public EntidadLibros(){

    }

    public EntidadLibros(String titulo) {
        this.titulo = titulo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public int getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(int numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    public EntidadAutor getAutor() {
        return autor;
    }

    public void setAutor(EntidadAutor autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "EntidadLibros{" +
                "titulo='" + titulo + '\'' +
                ", idiomas=" + idiomas +
                ", numeroDeDescargas=" + numeroDeDescargas +
                ", autor=" + (autor != null ? autor.getNombre() : "null") +
                '}';
    }
}
