package com.desafioalura.literatura.principal;

import com.desafioalura.literatura.model.EntidadAutor;
import com.desafioalura.literatura.model.EntidadLibros;
import com.desafioalura.literatura.repository.AutorRepository;
import com.desafioalura.literatura.repository.LibroRepository;
import com.desafioalura.literatura.service.ConsumoAPI;
import com.desafioalura.literatura.service.ConvierteDatos;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Scanner;

@Component
public class Principal {

    private final Scanner teclado = new Scanner(System.in);
    private static final String URL_BASE = "https://gutendex.com/books/";
    private static final String URL_BUSQUEDA = "?search=";
    private final ConsumoAPI consumoAPI = new ConsumoAPI();
    private final ConvierteDatos conversor = new ConvierteDatos();

    @Autowired
    private LibroRepository repositorioLibro;

    @Autowired
    private AutorRepository repositorioAutor;

    public Principal(LibroRepository repositorioLibro, AutorRepository repositorioAutor) {
        this.repositorioLibro = repositorioLibro;
        this.repositorioAutor = repositorioAutor;
    }

    //Metodo del menu que se imprimira por consola
    public void muestraElMenu() {
        int opcion = -1;

        while (opcion != 6) {
            String menu = """
                    *** Catálogo de Libros - Literatura ***

                    1- Buscar libro por título.
                    2- Mostrar libros registrados.
                    3- Mostrar autores registrados.
                    4- Mostrar autores vivos en un determinado año.
                    5- Mostrar libros por idioma.

                    6 - Salir
                    """;

            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarYMostrarLibro();
                    break;
                case 2:
                    mostrarLibrosRegistrados();
                    break;
                case 3:
                    mostrarAutoresRegistrados();
                    break;
                case 4:
                    mostrarAutoresSegunElAnio();
                    break;
                case 5:
                    mostrarLibrosSegunElIdioma();
                    break;
                case 6:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    //Metodo para buscar y guardar los libros e informacion del autor segun el titulo
    private void buscarYMostrarLibro() {
        System.out.println("Ingrese el nombre del libro que desea buscar: ");
        String tituloBusqueda = teclado.nextLine().trim(); // Obtener el título del libro

        String urlBusqueda = URL_BASE + URL_BUSQUEDA + tituloBusqueda.replace(" ", "+");
        String jsonResponse = consumoAPI.obtenerDatos(urlBusqueda);

        try {
            JsonNode responseNode = conversor.convertirDatos(jsonResponse, JsonNode.class);
            JsonNode resultsNode = responseNode.get("results");

            if (resultsNode != null && resultsNode.isArray() && resultsNode.size() > 0) {
                for (JsonNode result : resultsNode) {
                    String title = result.get("title").asText();
                    JsonNode authorsNode = result.get("authors");
                    JsonNode languagesNode = result.get("languages");
                    int downloadCount = result.get("download_count").asInt();

                    // Crear instancia de libro
                    EntidadLibros libro = new EntidadLibros();
                    libro.setTitulo(title);
                    libro.setIdiomas(languagesNode.toString());
                    libro.setNumeroDeDescargas(downloadCount);

                    for (JsonNode authorNode : authorsNode) {
                        String authorName = authorNode.get("name").asText();
                        EntidadAutor autor = new EntidadAutor();
                        autor.setNombre(authorName);

                        // Setear fecha de nacimiento y fallecimiento si están disponibles en el JSON
                        if (authorNode.has("birth_year")) {
                            try {
                                Integer birthYear = authorNode.get("birth_year").asInt();
                                autor.setFechaDeNacimiento(birthYear);
                            } catch (Exception e) {
                                System.err.println("Error parsing birth year: " + e.getMessage());
                            }
                        }

                        if (authorNode.has("death_year")) {
                            try {
                                Integer deathYear = authorNode.get("death_year").asInt();
                                autor.setFechaDeFallecimiento(deathYear);
                            } catch (Exception e) {
                                System.err.println("Error parsing death year: " + e.getMessage());
                            }
                        }

                        libro.setAutor(autor);
                        repositorioAutor.save(autor); // Guardar autor primero
                    }

                    repositorioLibro.save(libro); // Guardar libro después de guardar autor
                    System.out.println("Libro guardado correctamente en la base de datos.");

                    // Mostrar la información del libro utilizando el método formateado
                    imprimirDetalleLibro(libro);
                    return;
                }
            } else {
                System.out.println("No se encontró un libro con el título ingresado.");
            }
        } catch (Exception e) {
            System.err.println("Error al procesar los datos de la API: " + e.getMessage());
            e.printStackTrace(); // Opcional: imprimir el rastreo completo de la excepción
        }
    }


    //Metodo para mostrar los libros que hallan sido registradps
    private void mostrarLibrosRegistrados() {
        List<EntidadLibros> libros = repositorioLibro.findAll();
        System.out.println("Lista de libros registrados:");
        for (EntidadLibros libro : libros) {
            imprimirDetalleLibro(libro);
        }
    }


    //Metodo para mostrar autores registrados
    private void mostrarAutoresRegistrados() {
        List<EntidadAutor> autores = repositorioAutor.findAll();
        System.out.println("Lista de autores registrados:");
        for (EntidadAutor autor : autores) {
            imprimirDetalleAutor(autor);
            System.out.println("==================================================");
        }
    }

    //Metodo para mostrar los autores vivos segun el año
    public void mostrarAutoresSegunElAnio() {
        System.out.println("Ingrese el año para buscar autores vivos: ");
        var year = teclado.nextInt();
        teclado.nextLine(); // Consumir la nueva línea después del entero

        List<EntidadAutor> autores = repositorioAutor.findAliveAuthorsByYear(year);

        if (autores.isEmpty()) {
            System.out.println("No se encontraron autores vivos en el año " + year);
        } else {
            System.out.println("Autores vivos en el año " + year + ":");
            for (EntidadAutor autor : autores) {
                imprimirDetalleAutor(autor);
            }
        }
    }


    //Metodo para mostrar autor y libro por el idioma
    private void mostrarLibrosSegunElIdioma() {
        String menuIdiomas = """
                Ingrese el idioma para buscar los libros:

                es- español
                en- inglés
                fr- francés
                pt- portugués
                """;
        System.out.println(menuIdiomas);
        String idioma = teclado.nextLine();
        List<EntidadLibros> librosPorIdioma = repositorioLibro.findByIdiomasContainingIgnoreCase(idioma);
        for (EntidadLibros libros : librosPorIdioma) {
            imprimirDetalleLibro(libros);
        }
    }



    // Método auxiliar para imprimir detalles de los libros
    public void imprimirDetalleLibro(EntidadLibros libro) {
        System.out.println("Libro:");
        System.out.println("\tTítulo: " + libro.getTitulo());
        System.out.println("\tAutor: " + (libro.getAutor() != null ? libro.getAutor().getNombre() : "Desconocido"));
        System.out.println("\tIdiomas: " + libro.getIdiomas());
        System.out.println("\tNúmero de Descargas: " + libro.getNumeroDeDescargas());
        System.out.println("--------------------------------------------------");
    }

    // Método auxiliar para imprimir detalles de un autor
    private void imprimirDetalleAutor(EntidadAutor autor) {
        System.out.println("Autor:");
        System.out.println("\tNombre: " + autor.getNombre());
        System.out.println("\tFecha de Nacimiento: " + (autor.getFechaDeNacimiento() != null ? autor.getFechaDeNacimiento() : "Desconocida"));
        System.out.println("\tFecha de Fallecimiento: " + (autor.getFechaDeFallecimiento() != null ? autor.getFechaDeFallecimiento() : "Desconocida"));
        System.out.println("\tLibros:");
        for (EntidadLibros libro : autor.getLibros()) {
            System.out.println("\t\tTítulo: " + libro.getTitulo());
            System.out.println("\t\tIdiomas: " + libro.getIdiomas());
            System.out.println("\t\tNúmero de Descargas: " + libro.getNumeroDeDescargas());
        }
    }





}
