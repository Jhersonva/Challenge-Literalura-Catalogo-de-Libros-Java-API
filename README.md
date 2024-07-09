# Catálogo de Libros - Challenge Literalura

## Descripción

Este proyecto es una aplicación de gestión de literatura que permite a los usuarios buscar, registrar y visualizar información sobre libros y autores. Utiliza Spring Boot para la configuración y la integración con una base de datos PostgreSQL. La aplicación consume datos de una API pública y ofrece varias funcionalidades, incluyendo la búsqueda de libros por título, la visualización de libros y autores registrados, y la búsqueda de autores vivos en un año específico.

## Tecnologías Utilizadas

- **Java 17**
- **Spring Boot**
- **Hibernate/JPA**
- **PostgreSQL**
- **Jackson para JSON**
- **HTTP Client de Java**

## Funcionalidades

- **Buscar libro por título:** Permite buscar y registrar libros a partir de un título especificado.
- **Mostrar libros registrados:** Muestra todos los libros registrados en la base de datos.
- **Mostrar autores registrados:** Lista todos los autores registrados.
- **Mostrar autores vivos en un año:** Permite buscar autores que aún viven en un año específico.
- **Mostrar libros por idioma:** Filtra los libros según el idioma especificado.

## Estructura del Proyecto

- **`com.desafioalura.literatura.model`**: Contiene las entidades `EntidadAutor` y `EntidadLibros`.
- **`com.desafioalura.literatura.repository`**: Define los repositorios `AutorRepository` y `LibroRepository`.
- **`com.desafioalura.literatura.service`**: Incluye las clases `ConsumoAPI`, `ConvierteDatos`, y la interfaz `IConvierteDatos`.
- **`com.desafioalura.literatura.principal`**: Implementa la lógica principal y el menú interactivo para la aplicación.
- **`com.desafioalura.literatura`**: Clase principal de inicio de la aplicación `LiteraturaApplication`.

## Configuración de la Base de Datos

Configura la conexión a la base de datos en el archivo `application.properties`:

```properties
spring.application.name=literatura

# Conexión a la base de datos
spring.datasource.url=jdbc:postgresql://${DB_HOST}/literatura
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=org.postgresql.Driver
hibernate.dialect=org.hibernate.dialect.HSQLDialect

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true```
```
## Uso
- **Menú Principal:**:

<pre>
*** Catálogo de Libros - Literatura ***

1- Buscar libro por título.
2- Mostrar libros registrados.
3- Mostrar autores registrados.
4- Mostrar autores vivos en un determinado año.
5- Mostrar libros por idioma.

6 - Salir
</pre>

**************************************
### Ejecución del Programa

Al ejecutar el programa, se presenta el siguiente menú al usuario:


<div style="background-color: #000000; padding: 10px; border-radius: 5px; font-family: Consolas, 'Courier New', Courier, monospace;">

<pre>
*** Catálogo de Libros - Literatura ***

1- Buscar libro por título.
2- Mostrar libros registrados.
3- Mostrar autores registrados.
4- Mostrar autores vivos en un determinado año.
5- Mostrar libros por idioma.

6 - Salir

1

Ingrese el nombre del libro que desea buscar: 
Metamorphosis
=================================
Libro guardado correctamente en la base de datos.
Libro:
	Título: Metamorphosis
	Autor: Kafka, Franz
	Idiomas: ["en"]
	Número de Descargas: 20365
=================================
*** Catálogo de Libros - Literatura ***

1- Buscar libro por título.
2- Mostrar libros registrados.
3- Mostrar autores registrados.
4- Mostrar autores vivos en un determinado año.
5- Mostrar libros por idioma.

6 - Salir

2
=================================
Lista de libros registrados:
Libro:
	Título: Romeo and Juliet
	Autor: Shakespeare, William
	Idiomas: ["en"]
	Número de Descargas: 77782
=================================
Libro:
	Título: Middlemarch
	Autor: Eliot, George
	Idiomas: ["en"]
	Número de Descargas: 54135
=================================
Libro:
	Título: The Yellow Wallpaper
	Autor: Gilman, Charlotte Perkins
	Idiomas: ["en"]
	Número de Descargas: 12990
=================================
Libro:
	Título: Adventures of Huckleberry Finn
	Autor: Twain, Mark
	Idiomas: ["en"]
	Número de Descargas: 13945
=================================
Libro:
	Título: Metamorphosis
	Autor: Kafka, Franz
	Idiomas: ["en"]
	Número de Descargas: 20365
=================================
*** Catálogo de Libros - Literatura ***

1- Buscar libro por título.
2- Mostrar libros registrados.
3- Mostrar autores registrados.
4- Mostrar autores vivos en un determinado año.
5- Mostrar libros por idioma.

6 - Salir

3
=================================
Lista de autores registrados:
Autor:
	Nombre: Shakespeare, William
	Fecha de Nacimiento: 1564
	Fecha de Fallecimiento: 1616
	Libros:
		Título: Romeo and Juliet
		Idiomas: ["en"]
		Número de Descargas: 77782
=================================
Autor:
	Nombre: Eliot, George
	Fecha de Nacimiento: 1819
	Fecha de Fallecimiento: 1880
	Libros:
		Título: Middlemarch
		Idiomas: ["en"]
		Número de Descargas: 54135
==================================================
Autor:
	Nombre: Gilman, Charlotte Perkins
	Fecha de Nacimiento: 1860
	Fecha de Fallecimiento: 1935
	Libros:
		Título: The Yellow Wallpaper
		Idiomas: ["en"]
		Número de Descargas: 12990
==================================================
Autor:
	Nombre: Twain, Mark
	Fecha de Nacimiento: 1835
	Fecha de Fallecimiento: 1910
	Libros:
		Título: Adventures of Huckleberry Finn
		Idiomas: ["en"]
		Número de Descargas: 13945
==================================================
Autor:
	Nombre: Kafka, Franz
	Fecha de Nacimiento: 1883
	Fecha de Fallecimiento: 1924
	Libros:
		Título: Metamorphosis
		Idiomas: ["en"]
		Número de Descargas: 20365
==================================================
*** Catálogo de Libros - Literatura ***

1- Buscar libro por título.
2- Mostrar libros registrados.
3- Mostrar autores registrados.
4- Mostrar autores vivos en un determinado año.
5- Mostrar libros por idioma.

6 - Salir

4
Ingrese el año para buscar autores vivos: 
1850
=================================
Autores vivos en el año 1850:
Autor:
	Nombre: Eliot, George
	Fecha de Nacimiento: 1819
	Fecha de Fallecimiento: 1880
	Libros:
		Título: Middlemarch
		Idiomas: ["en"]
		Número de Descargas: 54135
=================================
Autor:
	Nombre: Twain, Mark
	Fecha de Nacimiento: 1835
	Fecha de Fallecimiento: 1910
	Libros:
		Título: Adventures of Huckleberry Finn
		Idiomas: ["en"]
		Número de Descargas: 13945
  =================================
*** Catálogo de Libros - Literatura ***

1- Buscar libro por título.
2- Mostrar libros registrados.
3- Mostrar autores registrados.
4- Mostrar autores vivos en un determinado año.
5- Mostrar libros por idioma.

6 - Salir

5
=================================
Ingrese el idioma para buscar los libros:

es- español
en- inglés
fr- francés
pt- portugués

en
=================================
Libro:
	Título: Romeo and Juliet
	Autor: Shakespeare, William
	Idiomas: ["en"]
	Número de Descargas: 77782
=================================
Libro:
	Título: Middlemarch
	Autor: Eliot, George
	Idiomas: ["en"]
	Número de Descargas: 54135
=================================
Libro:
	Título: The Yellow Wallpaper
	Autor: Gilman, Charlotte Perkins
	Idiomas: ["en"]
	Número de Descargas: 12990
=================================
Libro:
	Título: Adventures of Huckleberry Finn
	Autor: Twain, Mark
	Idiomas: ["en"]
	Número de Descargas: 13945
=================================
Libro:
	Título: Metamorphosis
	Autor: Kafka, Franz
	Idiomas: ["en"]
	Número de Descargas: 20365
=================================
*** Catálogo de Libros - Literatura ***

1- Buscar libro por título.
2- Mostrar libros registrados.
3- Mostrar autores registrados.
4- Mostrar autores vivos en un determinado año.
5- Mostrar libros por idioma.

6 - Salir

6
Cerrando la aplicación...

</pre>
</div>

**************************************

### Salida del Programa
Para salir del programa, el usuario debe seleccionar la opción 6.


**************************
### Contribuciones
Las contribuciones son bienvenidas. Por favor, abre un issue para discutir cualquier cambio importante antes de realizarlo.

************************** 

### Equipo de Trabajo
Nombre del Desarrollador: Jherson Villa

Contacto: jherson2408va@gmail.com

**************************

**¡Gracias por usar el el proyecto Literalura - Catalogo de Libros!. ¡Esperamos que te sea útil!**
