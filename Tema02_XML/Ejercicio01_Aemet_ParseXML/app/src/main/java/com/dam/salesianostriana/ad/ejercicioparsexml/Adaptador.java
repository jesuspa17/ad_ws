package com.dam.salesianostriana.ad.ejercicioparsexml;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author Jesús Pallares on 22/11/2015.
 */
public class Adaptador extends ArrayAdapter<Dia> {
    private final Context context;
    private final List<Dia> values;


    public Adaptador(Context context, List<Dia> values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // La siguiente línea de código recibe como parámetro el layout
        // que he diseñado para un elemento del ListView, en este caso
        // para un Alumno de la lista >>> R.layout.list_item_alumno
        View layout = inflater.inflate(R.layout.list_item_temperatura, parent, false);

        TextView fecha = (TextView) layout.findViewById(R.id.txtFecha);
        TextView maxima = (TextView) layout.findViewById(R.id.txtMaxima);
        TextView minima = (TextView) layout.findViewById(R.id.txtMinima);

        Dia pred = values.get(position);
        SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy");
        fecha.setText(d.format(pred.getFecha()));
        maxima.setText(pred.getTemperatura().getTemp_max()+" º");
        minima.setText(pred.getTemperatura().getTemp_min()+" º");


        return layout;
    }
}