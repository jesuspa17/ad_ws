package com.dam.salesianostriana.ad.ejemplo01_basedatossqlite;

/**
 * Created by Jesús Pallares on 04/12/2015.
 */
public class Cliente {

    private String nombre;
    private String apellidos;



    public Cliente(String nombre, String apellidos) {
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    public String getNombre() {

        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
}
