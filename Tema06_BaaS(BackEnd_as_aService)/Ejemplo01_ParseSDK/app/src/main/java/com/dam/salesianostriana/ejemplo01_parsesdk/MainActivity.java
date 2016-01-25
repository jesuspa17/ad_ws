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

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
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
                if(e == null){
                    String concepto = object.getString("Concepto");
                    String fecha = object.getDate("Fecha").toString();
                    Log.i("CONCEPTO","CONCEPTO: " +concepto);
                    Log.i("FECHA","FECHA: " + fecha);
                }else{
                    Log.e("ERROR", "ERROR "+e.getCode());
                }
            }
        });

        //3. OBTENER LISTA DE TODOS LOS OBJETOS DE UNA TABLA
        ParseQuery<ParseObject> pq = ParseQuery.getQuery("Todo");
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

                    listView.setAdapter(new ListAdapter(MainActivity.this,android.R.layout.simple_list_item_2,listaNotas));

                }else{
                    Log.e("ERROR", "ERROR "+e.getCode());
                }
            }
        });

    }

    class ListAdapter extends ArrayAdapter<Nota> {

        private List<Nota> items;

        public ListAdapter(Context context, int textViewResourceId, List<Nota> items) {
            super(context, textViewResourceId, items);
            this.items = items;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;

            if (v == null) {
                LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.list_item_lista, null);
            }

            Nota item = items.get(position);

            if (item != null) {
                TextView concepto = (TextView) v.findViewById(R.id.textView);
                TextView fecha = (TextView) v.findViewById(R.id.txtFecha);
                if (concepto != null) {
                    concepto.setText(item.getConcepto());
                }
                if (fecha != null) {
                    fecha.setText(item.getFecha());
                }
            }
            return v;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
