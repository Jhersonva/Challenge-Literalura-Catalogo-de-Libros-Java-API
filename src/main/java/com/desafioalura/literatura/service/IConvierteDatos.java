package com.desafioalura.literatura.service;

public interface IConvierteDatos {

    //Creando un metodo de tipo Generico con parametros para la conversion de Json a Objetos
    <T> T convertirDatos(String json, Class<T> clase);
}
