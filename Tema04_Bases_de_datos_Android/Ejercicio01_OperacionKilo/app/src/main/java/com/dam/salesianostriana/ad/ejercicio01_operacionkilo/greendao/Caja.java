package com.dam.salesianostriana.ad.ejercicio01_operacionkilo.greendao;


// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "CAJA".
 */
public class Caja {

    private Long id;
    private int numero;

    public Caja() {
    }

    public Caja(Long id) {
        this.id = id;
    }

    public Caja(Long id, int numero) {
        this.id = id;
        this.numero = numero;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

}
