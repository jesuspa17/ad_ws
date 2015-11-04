package com.dam.salesianostriana.ad.gson_sample;

/**
 * Created by jpallares on 04/11/2015.
 */
public class Nota {

    private String titulo;
    private String fecha;
    private String cuerpo;

    public Nota() {}

    public Nota(String titulo, String fecha, String cuerpo) {
        this.titulo = titulo;
        this.fecha = fecha;
        this.cuerpo = cuerpo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    @Override
    public String toString() {
        return "[" +
                "titulo='" + titulo + '\'' +
                ", fecha='" + fecha + '\'' +
                ", cuerpo='" + cuerpo + '\'' +
                ']';
    }
}
