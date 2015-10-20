package com.salesianostriana.ad.tema01_ejercicio05;

import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

/**
 * @author Jesús Pallares
 */
 
 /**
 (5) Descarga de un fichero y almacenamiento en memoria interna

    Realizar (en un proyecto nuevo) las modificaciones necesarias en el ejercicio 3 para que en lugar 
    de solamente contar bytes y el tiempo de descarga, que el fichero quede almacenado en memoria interna. 
    Como a priori no sabemos el nombre del fichero que vamos a descargar, lo llamaremos siempre download.file.
    
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

        try {

            //url de descarga
            URL url = new URL("https://upload.wikimedia.org/wikipedia/commons/4/48/Rio_Grande_White_Rock_Overlook_Park_View_2006_09_05.jpg");
            //se abre conexión a internet
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            //obtenemos hora del sistema al iniciar la descarga
            Calendar calendario = new GregorianCalendar();
            int hora, minutos, segundos;
            hora = calendario.get(Calendar.HOUR_OF_DAY);
            minutos = calendario.get(Calendar.MINUTE);
            segundos = calendario.get(Calendar.SECOND);
            Log.i("HORA INICIAL: ", hora + ":" + minutos + ":" + segundos);


            //BufferedInput para leer la ruta que le hemos pasado
            BufferedInputStream bis = new BufferedInputStream(getInputStreamFromURL(url.toString()));

            //BufferedOutPut para guardar en un fichero lo que hemos descargado.
            BufferedOutputStream bos = new BufferedOutputStream(openFileOutput("download.file", Context.MODE_PRIVATE));

            //se obtiene el tamaño del archivo descargado
            byte[] buffer = new byte[TAM];
            int c = 0;
            int cantBytes = 0;
            try {
                while ((c = bis.read(buffer, 0, TAM)) != -1) {
                    cantBytes += c;
                    //guarda en el archivo en el archivo indicado en el "BOS" mientras lee
                    bos.write(buffer,0,c);
                }

                //obtenemos hora del sistema al finalizarla descarga
                Calendar calendario2 = new GregorianCalendar();
                int hora_final, minutos_final, segundos_final;
                hora_final = calendario2.get(Calendar.HOUR_OF_DAY);
                minutos_final = calendario2.get(Calendar.MINUTE);
                segundos_final = calendario2.get(Calendar.SECOND);

                //Calculamos el tiempo de descarga total
                Log.i("HORA FINAL: ", hora_final + ":" + minutos_final + ":" + segundos_final);
                int hora_total = hora_final - hora;
                int minutos_total = minutos_final - minutos;
                int segundos_total = segundos_final - segundos;

                Log.i("TIEMPO DE DESCARGA: ", hora_total + "h " + minutos_total + "m " + segundos_total + "s");
                Log.i("Tamaño fichero: ", formatearTamanyo(cantBytes));

                bos.close();
                bis.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String formatearTamanyo(long size){

        String total = "";
        int bytes = 1024;
        long kb = size/bytes;
        long mb = size/(bytes*bytes);
        long gb = size/(bytes*bytes*bytes);
        DecimalFormat dec = new DecimalFormat("0.00");
        if (gb>1) {
            total = dec.format(kb)+"GB";
        }else if (mb>1) {
            total = dec.format(mb)+"MB";
        }else if (kb>1) {
            total = dec.format(gb)+"KB";
        }else{
            total = dec.format(gb)+"Bytes";
        }
        return total;
    }

    public String humanizeSeconds(long diff){
        return String.format("%d min, %d seg",
        TimeUnit.MILLISECONDS.toMinutes(diff),
        TimeUnit.MILLISECONDS.toSeconds(diff),
        TimeUnit.MILLISECONDS.toSeconds(TimeUnit.MILLISECONDS.toMinutes(diff)));
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
