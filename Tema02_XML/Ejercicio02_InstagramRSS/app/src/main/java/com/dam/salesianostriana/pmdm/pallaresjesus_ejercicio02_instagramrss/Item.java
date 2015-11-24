package com.dam.salesianostriana.pmdm.pallaresjesus_ejercicio02_instagramrss;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

/**
 * Created by Jesús Pallares on 24/11/2015.
 */
@ElementList(name = "item")
public class Item {

    @Element(name = "author")
    private String autor;

    @Element(name = "pubDate")
    private String pubdate;

    @Element(name = "description")
    private String descripcion;

    public Item(){}

    public Item(String autor, String pubdate, String descripcion) {
        this.autor = autor;
        this.pubdate = pubdate;
        this.descripcion = descripcion;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public String getDescripcion() {
        String[] des1 = this.descripcion.split("<");
        String[] des2 = this.descripcion.split(">");
        return des2[1].replace("<img src='","").replace("'/","").replace(" ","").trim();
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Item{" +
                "autor='" + autor + '\'' +
                ", pubdate='" + pubdate + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
