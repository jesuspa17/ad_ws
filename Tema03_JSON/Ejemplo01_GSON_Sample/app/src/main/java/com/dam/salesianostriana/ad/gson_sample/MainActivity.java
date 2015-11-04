package com.dam.salesianostriana.ad.gson_sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    String json = "{ \"titulo\" : \"Enviar memorando\", " +
            "\"fecha\" : \"19/11/2015\", " +
            "\"cuerpo\": \"No olvidar enviar el memorando sobre el proyecto a Miguel\" }";


    TextView txt_titulo, txt_fecha, txt_cuerpo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_titulo = (TextView) findViewById(R.id.txt_titulo);
        txt_fecha = (TextView) findViewById(R.id.txt_fecha);
        txt_cuerpo = (TextView) findViewById(R.id.txt_cuerpo);


        Gson gson = new Gson();
        Nota n = gson.fromJson(json, Nota.class);

        txt_titulo.setText(n.getTitulo());
        txt_fecha.setText(n.getFecha());
        txt_cuerpo.setText(n.getCuerpo());

        Log.i("SALIDA", n.toString());


        Nota otraNota = new Nota("ASDFG", "01/01/2015","Lorem ipsum dolor sit amet");
        StringBuilder resultado = new StringBuilder();
        gson.toJson(otraNota,resultado);
        Log.i("SALIDA", resultado.toString());


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
