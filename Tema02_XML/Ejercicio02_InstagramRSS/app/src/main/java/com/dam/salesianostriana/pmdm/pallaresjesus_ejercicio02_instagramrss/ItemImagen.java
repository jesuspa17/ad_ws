package com.dam.salesianostriana.pmdm.pallaresjesus_ejercicio02_instagramrss;

/**
 * Created by Jesús Pallares on 24/11/2015.
 */
public class ItemImagen {

    private String imagen;
    private String autor;
    private String fecha;


    public ItemImagen(){

    }
    public ItemImagen(String imagen, String autor, String fecha) {
        this.imagen = imagen;
        this.autor = autor;
        this.fecha = fecha;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String descripcion) {
        this.autor = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }


}
