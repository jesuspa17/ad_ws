package com.salesianostriana.ad.ejercicio03_buffering;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.TimeUnit;

/**
(3) Tamaño de un fichero descargado (Buffering)

Realizar las siguientes modificaciones en el ejercicio anterior

1) Medir el tiempo de ejecución de la descarga del fichero. Para ello tenemos que:
    Tomar el tiempo del sistema justo antes de comenzar la descarga.
    Tomar el tiempo del sistema justo después de finalizar la descarga.
    Restar ambas cantidades, y transformarlas a un formato humanizado.
    Imprimir, junto con la cantidad de bytes, dicha cantidad.

2) Implementar (en otro proyecto diferente, basado en la plantilla que se proporciona en el ejercicio anterior). 
En este caso, la descarga del fichero la tendremos que hacer usando como flujo BufferedInputStream, 
y un tamaño de flujo que determinará el alumno. En este caso, también hay que medir el tiempo de ejecución.
 * */


public class MainActivity extends AppCompatActivity {

    public InputStream getInputStreamFromURL(String url) throws IOException {

        URL u = new URL(url);
        URLConnection con = u.openConnection();
        InputStream is = con.getInputStream();

        return is;
    }

    final int TAM = 1 * 1024;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //url a descargar
        try {
            final URL url = new URL("https://docs.google.com/uc?export=download&id=0B3b6MFUtZc42dEFYekNJRkRlWWc");

            //se abre conexión a internet
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);


            //Guarda hora inicio descarga
            long comienzo = System.currentTimeMillis();

            String hora_comienzo = String.format("%d m, %m s", TimeUnit.MILLISECONDS.toMinutes(comienzo),
                    TimeUnit.MILLISECONDS.toSeconds(comienzo));

            Log.i("HORA INICIAL: ", hora_comienzo);


            BufferedInputStream bis = new BufferedInputStream(getInputStreamFromURL(url.toString()));

            byte[] buffer = new byte[TAM];
            int c = 0;
            int cantBytes = 0;

            try {
                while ((cantBytes = bis.read(buffer, 0, TAM)) != -1) {
                    cantBytes += c;
                }

                //Guarda hora fin de descarga
                long fin = System.currentTimeMillis();
                String hora_final = String.format("%d m, %m s", TimeUnit.MILLISECONDS.toMinutes(fin),
                        TimeUnit.MILLISECONDS.toSeconds(fin));

                Log.i("HORA FINAL: ", hora_final);

                //Calculamos el tiempo de descarga total
                //diferencia entre los tiempos de inicio y fin.
                long diferencia_tiempo = comienzo - fin;

                String tiempo_total = String.format("%d min, %d seg", TimeUnit.MILLISECONDS.toMinutes(diferencia_tiempo),
                        TimeUnit.MILLISECONDS.toSeconds(diferencia_tiempo));
                Log.i("TIEMPO DE DESCARGA: ", tiempo_total);

                //tamaño total de fichero
                Log.i("Tamaño fichero: ", String.valueOf(cantBytes) + " bytes");
                bis.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
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
