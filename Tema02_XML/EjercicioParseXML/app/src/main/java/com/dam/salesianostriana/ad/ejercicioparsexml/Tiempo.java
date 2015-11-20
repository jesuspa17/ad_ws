package com.dam.salesianostriana.ad.ejercicioparsexml;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by Jesús Pallares on 20/11/2015.
 */
@Root(strict = false) //se pone esto para cojer la información que queramos del xml.
public class Tiempo {

    @Element
    private String nombre_provincia;

    @Element
    private Prediccion predicciones;

    public Tiempo(){}

    public Tiempo(String nombre_provincia, Prediccion predicciones) {
        this.nombre_provincia = nombre_provincia;
        this.predicciones = predicciones;
    }

    public String getNombre_provincia() {
        return nombre_provincia;
    }

    public void setNombre_provincia(String nombre_provincia) {
        this.nombre_provincia = nombre_provincia;
    }

    public Prediccion getPredicciones() {
        return predicciones;
    }

    public void setPredicciones(Prediccion predicciones) {
        this.predicciones = predicciones;
    }

    @Override
    public String toString() {
        return "Tiempo{" +
                "nombre_provincia='" + nombre_provincia + '\'' +
                ", predicciones=" + predicciones +
                '}';
    }
}
