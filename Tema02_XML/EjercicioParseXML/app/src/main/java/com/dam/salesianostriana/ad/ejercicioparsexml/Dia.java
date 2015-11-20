package com.dam.salesianostriana.ad.ejercicioparsexml;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by Jesús Pallares on 20/11/2015.
 */
@Root(strict = false)
public class Dia {

    @Element
    private String fecha;

    @Element
    private Temperatura temperatura;

    public Dia(){}

    public Dia(String fecha, Temperatura temperatura) {
        this.fecha = fecha;
        this.temperatura = temperatura;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Temperatura getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Temperatura temperatura) {
        this.temperatura = temperatura;
    }


    @Override
    public String toString() {
        return "Dia{" +
                "fecha='" + fecha + '\'' +
                ", temperatura=" + temperatura +
                '}';
    }
}
