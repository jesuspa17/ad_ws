package com.salesianostriana.ad.tema01_ejercicio03;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    public InputStream getInputStreamFromURL(String url) throws IOException {

        URL u = new URL(url);
        URLConnection con = u.openConnection();
        InputStream is = con.getInputStream();

        return is;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn_descargar = (Button) findViewById(R.id.btn_descargar);

        btn_descargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    //url de descarga
                    URL url = new URL("https://docs.google.com/uc?export=download&id=0B3b6MFUtZc42dEFYekNJRkRlWWc");
                    //se abre conexión a internet
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);

                    InputStream is;

                    //obtenemos hora del sistema al iniciar la descarga

                    Calendar calendario = new GregorianCalendar();
                    int hora, minutos, segundos;
                    hora = calendario.get(Calendar.HOUR_OF_DAY);
                    minutos = calendario.get(Calendar.MINUTE);
                    segundos = calendario.get(Calendar.SECOND);
                    Log.i("HORA INICIAL: ", hora + ":" + minutos + ":" + segundos);

                    //se obtiene el tamaño del archivo descargado

                    is = getInputStreamFromURL(url.toString());
                    int tam = 0;
                    int c;
                    while ((c = is.read()) != -1) {
                        tam += c;
                    }

                    //obtenemos hora del sistema al finalizarla descarga
                    Calendar calendario2 = new GregorianCalendar();
                    int hora2, minutos2, segundos2;
                    hora2 = calendario2.get(Calendar.HOUR_OF_DAY);
                    minutos2 = calendario2.get(Calendar.MINUTE);
                    segundos2 = calendario2.get(Calendar.SECOND);

                    //Calculamos el tiempo de descarga total
                    Log.i("HORA FINAL: ", hora2 + ":" + minutos2 + ":" + segundos2);
                    int hora_total = hora2 - hora;
                    int minutos_total = minutos2 - minutos;
                    int segundos_total = segundos2 - segundos;

                    Log.i("TIEMPO DE DESCARGA: ", hora_total + "h " + minutos_total + "m " + segundos_total + "s");
                    Log.i("Tamaño fichero: ", String.valueOf(tam) + " bytes");


                    is.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        });

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

