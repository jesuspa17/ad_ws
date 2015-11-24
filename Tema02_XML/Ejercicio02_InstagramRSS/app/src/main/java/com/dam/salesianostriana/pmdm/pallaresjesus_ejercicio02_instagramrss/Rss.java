package com.dam.salesianostriana.pmdm.pallaresjesus_ejercicio02_instagramrss;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by Jesús Pallares on 24/11/2015.
 */
@Root(name = "rss", strict = false)
public class Rss {

    @Element(name = "channel")
    private Channel channel;

    public Rss(){}

    public Rss(Channel channel){
        this.channel = channel;
    }

    public Channel getChannel(){
        return this.channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    @Override
    public String toString() {
        return "Rss{" +
                "channel=" + channel +
                '}';
    }
}


