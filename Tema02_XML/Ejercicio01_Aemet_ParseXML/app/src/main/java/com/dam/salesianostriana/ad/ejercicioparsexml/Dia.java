package com.dam.salesianostriana.ad.ejercicioparsexml;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.util.Date;

/**
 * Created by Jesús Pallares on 20/11/2015.
 */
@Root(strict = false)
public class Dia {

    @Attribute(name="fecha")
    private Date fecha;

    @Element(name="temperatura")
    private Temperatura temperatura;

    public Dia(){}

    public Dia(Date fecha, Temperatura temperatura) {
        this.fecha = fecha;
        this.temperatura = temperatura;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
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
