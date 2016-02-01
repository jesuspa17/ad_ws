package com.dam.salesianostriana.ejemplo01_parsesdk;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = (ListView) findViewById(R.id.listView);


        //1.INSERTAR OBJETO.

       /* //Se instancia el objeto Parse
        ParseObject nuevoTodo = new ParseObject("Todo");


        //Se va creando el objeto
        nuevoTodo.put("Concepto","Hacer la compra");
        nuevoTodo.put("Fecha",new Date());

        //Se guardan los cambios
        nuevoTodo.saveInBackground();*/


        //2. OBTENER DATOS DE UN OBJETO
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Todo");
        //Se le pasa el objectId
        query.getInBackground("4MFfz39Jkq", new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                    String concepto = object.getString("Concepto");
                    String fecha = object.getDate("Fecha").toString();
                    Log.i("CONCEPTO", "CONCEPTO: " + concepto);
                    Log.i("FECHA", "FECHA: " + fecha);
                } else {
                    Log.e("ERROR", "ERROR " + e.getCode());
                }
            }
        });

        //3. OBTENER LISTA DE TODOS LOS OBJETOS DE UNA TABLA
       /* ParseQuery<ParseObject> pq = ParseQuery.getQuery("Todo");
        final List<Nota> listaNotas = new ArrayList<>();

        pq.findInBackground(new FindCallback<ParseObject>() {

            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e == null){

                    for(ParseObject parse : objects){

                        String concepto = parse.getString("Concepto");
                        String fecha = parse.getDate("Fecha").toString();

                        Log.i("LISTA_CONCEPTO","LISTA_CONCEPTO: " +concepto);
                        Log.i("LISTA_FECHA", "LISTA_FECHA: " + fecha);

                        listaNotas.add(new Nota(concepto,fecha));
                    }

                    listView.setAdapter(new ListAdapter(MainActivity.this,android.R.layout.simple_list_item_2,objects));

                }else{
                    Log.e("ERROR", "ERROR "+e.getCode());
                }
            }
        });*/

        //4. PINTAR LA LISTA CON EL ADAPTADOR DE PARSE
        /*ParseQueryAdapter<ParseObject> adapter = new ParseQueryAdapter<ParseObject>(this, "Todo");
        adapter.setTextKey("Fecha");
        adapter.setTextKey("Concepto");*/


        //5. PINTAR LISTA CON ADAPTADOR PERSONALIZADO EXTENDIENDO DE LA CLASE ParseQueryAdapter
        listView.setAdapter(new ParseQueryPersonalizado(this,"Todo"));


    }

    class ParseQueryPersonalizado extends ParseQueryAdapter<ParseObject>{

        public ParseQueryPersonalizado(Context context, String className) {
            super(context, className);
        }

        @Override
        public View getItemView(ParseObject object, View v, ViewGroup parent) {
            v = View.inflate(MainActivity.this,R.layout.list_item_lista,null);

            super.getItemView(object,v,parent);

            TextView concepto = (TextView) v.findViewById(R.id.textView);
            TextView fecha = (TextView) v.findViewById(R.id.txtFecha);

            fecha.setText(object.getDate("Fecha").toString());
            concepto.setText(object.getString("Concepto"));

            return v;
        }
    }

    class ListAdapter extends ArrayAdapter<ParseObject> {

        private List<ParseObject> items;

        public ListAdapter(Context context, int textViewResourceId, List<ParseObject> items) {
            super(context, textViewResourceId, items);
            this.items = items;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;

            if (v == null) {
                LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.list_item_lista, null);
            }

            ParseObject item = items.get(position);

            if (item != null) {
                TextView concepto = (TextView) v.findViewById(R.id.textView);
                TextView fecha = (TextView) v.findViewById(R.id.txtFecha);
                if (concepto != null) {
                    concepto.setText(item.getString("Concepto"));
                }
                if (fecha != null) {
                    fecha.setText(item.getDate("Fecha").toString());
                }
            }
            return v;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
