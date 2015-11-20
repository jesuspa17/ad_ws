package com.dam.salesianostriana.ad.ejercicioparsexml;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //URL: http://www.aemet.es/xml/municipios/localidad_41091.xml

        new GetDataTimeTask().execute();


    }

    private static String leer(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    private class GetDataTimeTask extends AsyncTask<Void, Void, Tiempo>{

        @Override
        protected Tiempo doInBackground(Void... params) {
            Tiempo tiempo = null;

            try {
                URL url = new URL("http://www.aemet.es/xml/municipios/localidad_41091.xml");

                BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));

                Serializer serializer = new Persister();

                String xml = leer(br);

                Log.i("XML3","XML: " + xml);

                tiempo = new Tiempo();

                tiempo = serializer.read(Tiempo.class,br);

                //Log.i("XML","XML: " + br.toString());



            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
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
