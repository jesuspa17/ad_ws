package com.dam.salesianostriana.pmdm.pallaresjesus_ejercicio02_instagramrss;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.util.List;

/**
 * Created by Jesús Pallares on 24/11/2015.
 */
@Element(name = "channel")
public class Channel {

    @ElementList(name = "item", inline = true)
    private List<Item> item;

    public Channel(){}


    public Channel(List<Item> item) {
        this.item = item;
    }

    public List<Item> getItem() {
        return item;
    }

    public void setItem(List<Item> item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "item=" + item +
                '}';
    }
}
