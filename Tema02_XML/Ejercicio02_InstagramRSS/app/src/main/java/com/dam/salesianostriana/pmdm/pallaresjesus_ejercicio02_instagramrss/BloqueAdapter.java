package com.dam.salesianostriana.pmdm.pallaresjesus_ejercicio02_instagramrss;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Clase que servirá como adaptador para la lista donde se mostrará la consulta de los 5 días.
 */

public class BloqueAdapter extends RecyclerView.Adapter<BloqueAdapter.ViewHolder> {

    private ArrayList<ItemImagen> lista_imagenes;
    Context contexto;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imagen;
        public TextView fecha;
        public TextView autor;


        public ViewHolder(View v) {
            super(v);
            fecha = (TextView)v.findViewById(R.id.textFecha);
            autor = (TextView) v.findViewById(R.id.txtAutor);
            imagen  = (ImageView) v.findViewById(R.id.imageViewInsta);
        }
    }

    public BloqueAdapter(Context context, ArrayList<ItemImagen> lista_imagenes) {
        this.contexto = context;
        this.lista_imagenes = lista_imagenes;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item_recycler, viewGroup, false);

        contexto = v.getContext();
        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.fecha.setText(lista_imagenes.get(position).getFecha());
        holder.autor.setText(lista_imagenes.get(position).getAutor());
        Picasso.with(contexto).load(lista_imagenes.get(position).getImagen()).resize(1000,1000).into(holder.imagen);
    }

    @Override
    public int getItemCount()  {
        return lista_imagenes.size();
    }
}
