package com.dam.salesianostriana.ad.ejercicioparsexml;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by Jesús Pallares on 20/11/2015.
 */
@Root(strict = false)
public class Temperatura {

    @Element
    private double temp_max;
    @Element
    private double temp_min;

    public Temperatura(){}

    public Temperatura(double temp_max, double temp_min) {
        this.temp_max = temp_max;
        this.temp_min = temp_min;
    }

    public double getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(double temp_max) {
        this.temp_max = temp_max;
    }

    public double getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(double temp_min) {
        this.temp_min = temp_min;
    }

    @Override
    public String toString() {
        return "Temperatura{" +
                "temp_max=" + temp_max +
                ", temp_min=" + temp_min +
                '}';
    }
}
