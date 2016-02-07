package com.dam.salesianostriana.todoapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseObject;
import com.parse.ParseQueryAdapter;

import java.text.SimpleDateFormat;

/**
 * Created by Jesus Pallares on 06/02/2016.
 */
public class MyParseAdapter extends ParseQueryAdapter<ParseObject> {

    Context context;
    QueryFactory<ParseObject> queryFactory;

    public MyParseAdapter(Context context, QueryFactory<ParseObject> queryFactory) {
        super(context, queryFactory);
        this.context = context;
        this.queryFactory = queryFactory;

    }

    public MyParseAdapter(Context context, String className) {
        super(context, className);
        this.context = context;
    }

    @Override
    public View getItemView(ParseObject object, View v, ViewGroup parent) {
        v = View.inflate(context,R.layout.list_item_lista,null);

        super.getItemView(object,v,parent);

        TextView concepto = (TextView) v.findViewById(R.id.textView);
        TextView fecha = (TextView) v.findViewById(R.id.txtFecha);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String fec = sdf.format(object.getDate("Fecha"));
        String [] spliteada = fec.split(" ");
        fecha.setText(spliteada[0]+" a las "+spliteada[1]);
        concepto.setText(object.getString("Concepto"));

        return v;
    }
}

