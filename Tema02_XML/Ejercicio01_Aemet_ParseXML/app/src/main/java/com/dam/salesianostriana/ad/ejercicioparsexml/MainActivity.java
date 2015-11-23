package com.dam.salesianostriana.ad.ejercicioparsexml;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
Ejercico 1: Parser para XML aemet: temperaturas
Implementar una aplicación que sea capaz de parsear un fichero 
(por ejemplo http://www.aemet.es/xml/municipios/localidad_41091.xml) 
y que sea capaz de mostrar la la población y la provincia, y una lista 
(ListView, RecyclerView....) con la temperatura mínima y máxima para cada día.
 * */

public class MainActivity extends AppCompatActivity {

    ListView lista_tempraturas;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setTitle("");
        lista_tempraturas = (ListView) findViewById(R.id.listView);
        new GetDataTimeTask().execute();


    }

    private class GetDataTimeTask extends AsyncTask<Void, Void, Tiempo>{

        @Override
        protected Tiempo doInBackground(Void... params) {

            Tiempo tiempo = null;

            try {
                URL url = new URL("http://www.aemet.es/xml/municipios/localidad_41067.xml");
                InputStream is = url.openStream();

                Serializer serializer = new Persister();
                tiempo = new Tiempo();
                tiempo = serializer.read(Tiempo.class,is);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return tiempo;
        }

        @Override
        protected void onPostExecute(Tiempo tiempo) {
            super.onPostExecute(tiempo);
            toolbar.setTitle(tiempo.getNombre()+" ("+tiempo.getNombre_provincia()+")");
            Adaptador adaptador =new Adaptador(MainActivity.this,tiempo.getPredicciones().getLista_dias());
            lista_tempraturas.setAdapter(adaptador);
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
