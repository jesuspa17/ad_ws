package com.dam.salesianostriana.ejemplo01_parsesdk;

/**
 * Created by Jesús Pallares on 25/01/2016.
 */
public class Nota {

    private String concepto;
    private String fecha;

    public Nota(){}


    public Nota(String concepto, String fecha) {
        this.concepto = concepto;
        this.fecha = fecha;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
