package com.dam.salesianostriana.ad.ejemplo01_basedatossqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jesús Pallares on 04/12/2015.
 */
public class ClienteAdapter extends ArrayAdapter<Cliente>{

    private final Context context;
    private final ArrayList<Cliente> lista_clientes;

    public ClienteAdapter(Context context, ArrayList<Cliente> lista_clientes) {
        super(context, -1, lista_clientes);
        this.context = context;
        this.lista_clientes = lista_clientes;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View layout_cliente = inflater.inflate(R.layout.list_item_cliente, parent, false);

        TextView nombre = (TextView) layout_cliente.findViewById(R.id.textViewTextViewNombre);
        TextView apellidos = (TextView) layout_cliente.findViewById(R.id.textViewApellidos);

        Cliente cliente_actual = lista_clientes.get(position);

        nombre.setText(cliente_actual.getNombre());
        apellidos.setText(cliente_actual.getApellidos());

        return layout_cliente;

    }
}
