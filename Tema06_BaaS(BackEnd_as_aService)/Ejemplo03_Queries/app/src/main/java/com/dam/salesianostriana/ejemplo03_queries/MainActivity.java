package com.dam.salesianostriana.ejemplo03_queries;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity{

    ToggleButton btn_todos, btn_hoy,btn_ayer,btn_tresDias;
    ImageView img_ordenar;
    ListView lista;
    ParseQueryPersonalizado adapter;

    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btn_hoy = (ToggleButton) findViewById(R.id.btn_hoy);
        btn_todos = (ToggleButton) findViewById(R.id.btn_todos);
        btn_ayer = (ToggleButton) findViewById(R.id.btn_ayer);
        img_ordenar = (ImageView) findViewById(R.id.imageViewOrdenar);
        btn_tresDias = (ToggleButton) findViewById(R.id.btn_tresDias);
        lista = (ListView) findViewById(R.id.listView);


        btn_todos.setChecked(true);
        obtenerTodas();

        //Botón muestra todas las notas.
        btn_todos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                obtenerTodas();
                btn_ayer.setChecked(false);
                btn_hoy.setChecked(false);
                btn_tresDias.setChecked(false);
            }
        });

        //Botón que muestra las notas creadas hoy.
        btn_hoy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(btn_hoy.isChecked()){

                    obtenerNotasHoy();
                    lista.setAdapter(adapter);
                    btn_todos.setChecked(false);
                    btn_ayer.setChecked(false);
                    btn_tresDias.setChecked(false);

                }else{
                    obtenerTodas();
                }
            }
        });


        //Muestra las notas de ayer.
        btn_ayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btn_ayer.isChecked()){

                    obtenerNotasAyer();
                    lista.setAdapter(adapter);
                    btn_todos.setChecked(false);
                    btn_hoy.setChecked(false);
                    btn_tresDias.setChecked(false);

                }else{
                    obtenerTodas();
                }

            }
        });


        btn_tresDias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btn_tresDias.isChecked()){

                    obtenerNotasTresDias();
                    lista.setAdapter(adapter);
                    btn_todos.setChecked(false);
                    btn_hoy.setChecked(false);
                    btn_ayer.setChecked(false);

                }else{
                    obtenerTodas();
                }
            }
        });


        img_ordenar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                count++;
                ordenarNotas();
                lista.setAdapter(adapter);
                btn_todos.setChecked(true);
                btn_hoy.setChecked(false);
                btn_ayer.setChecked(false);
                btn_tresDias.setChecked(false);

            }
        });
    }


    public Calendar getFechaHoy(){

        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY,0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal;
    }

    //Adaptador usado para la lista
    class ParseQueryPersonalizado extends ParseQueryAdapter<ParseObject> {

        public ParseQueryPersonalizado(Context context, QueryFactory<ParseObject> queryFactory) {
            super(context, queryFactory);
        }

        public ParseQueryPersonalizado(Context context, String className) {
            super(context, className);
        }

        @Override
        public View getItemView(ParseObject object, View v, ViewGroup parent) {
            v = View.inflate(MainActivity.this,R.layout.list_item_lista,null);

            super.getItemView(object,v,parent);

            TextView concepto = (TextView) v.findViewById(R.id.textView);
            TextView fecha = (TextView) v.findViewById(R.id.txtFecha);

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm a");

            fecha.setText(sdf.format(object.getDate("Fecha")));
            concepto.setText(object.getString("Concepto"));

            return v;
        }
    }


    public void obtenerTodas(){
        lista.setAdapter(new ParseQueryPersonalizado(MainActivity.this, "Todo"));
    }


    public void ordenarNotas(){

        adapter = new ParseQueryPersonalizado(MainActivity.this,new ParseQueryAdapter.QueryFactory<ParseObject>() {
            public ParseQuery<ParseObject> create() {

                ParseQuery query = new ParseQuery("Todo");
                if(count%2!=0){
                    query.orderByAscending("Fecha");
                }else{
                    query.orderByDescending("Fecha");
                }
                
                return query;
            }
        });

    }

    public void obtenerNotasHoy(){

        adapter = new ParseQueryPersonalizado(MainActivity.this,new ParseQueryAdapter.QueryFactory<ParseObject>() {

            public ParseQuery<ParseObject> create() {

                ParseQuery query = new ParseQuery("Todo");

                Calendar cal = getFechaHoy();

                Date hoy = cal.getTime();
                cal.add(Calendar.DAY_OF_MONTH, 1);
                Date tomorrow= cal.getTime();

                query.whereGreaterThanOrEqualTo("Fecha", hoy);
                query.whereLessThan("Fecha", tomorrow);

                return query;
            }
        });

    }

    public void obtenerNotasAyer(){
        adapter = new ParseQueryPersonalizado(MainActivity.this,new ParseQueryAdapter.QueryFactory<ParseObject>() {
            public ParseQuery<ParseObject> create() {

                ParseQuery query = new ParseQuery("Todo");

                Calendar cal = getFechaHoy();

                Date hoy = cal.getTime();
                cal.add(Calendar.DAY_OF_MONTH, -1);
                Date ayer= cal.getTime();

                query.whereLessThanOrEqualTo("Fecha", hoy);
                query.whereGreaterThan("Fecha", ayer);

                return query;
            }
        });

    }


    public void obtenerNotasTresDias(){

        adapter = new ParseQueryPersonalizado(MainActivity.this, new ParseQueryAdapter.QueryFactory<ParseObject>() {
            @Override
            public ParseQuery<ParseObject> create() {

                ParseQuery query = new ParseQuery("Todo");

                Calendar cal = getFechaHoy();
                Date hoy = cal.getTime();
                cal.add(Calendar.DATE,3);
                Date tres_siguientes = cal.getTime();

                query.whereGreaterThan("Fecha",hoy);
                query.whereLessThan("Fecha",tres_siguientes);

                return query;
            }
        });
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
