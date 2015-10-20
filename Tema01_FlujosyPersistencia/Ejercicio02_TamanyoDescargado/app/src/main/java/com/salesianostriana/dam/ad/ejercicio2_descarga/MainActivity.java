package com.salesianostriana.dam.ad.ejercicio2_descarga;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
(2) Tamaño de un fichero descargado

Implementar una aplicación Android que descargue un fichero desde una URL dada, 
y muestre por el log el tamaño (en bytes) del fichero descargado.

Se proporciona el código fuente para la obtención de el flujo de entrada desde una URL.

        public InputStream getInputStreamFromURL(String url) {
           
           URL u = new URL(url);
           URLConnection con = u.openConnection();
           InputStream is = con.getInputStream();
        
           return is;
        }

Hay que tener en cuenta que hay que añadir el siguiente código:

    1) Por un lado, para poder abrir una conexión de red en el hilo principal, debemos añadir
    
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        
        StrictMode.setThreadPolicy(policy);
    
    Por otro lado, también debemos modificar el fichero de manifiesto, para añadir el permiso de conexión a internet.
    
        <uses-permission android:name="android.permission.INTERNET"/>
 * */

public class MainActivity extends Activity {

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
                    URL url = new URL("https://docs.google.com/uc?export=download&id=0B3b6MFUtZc42dEFYekNJRkRlWWc");

                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);

                    InputStream is = getInputStreamFromURL(url.toString());

                    int tam = 0;
                    int numBytes;
                    while (( numBytes = is.read()) != -1){
                        tam++;
                    }
                    Log.i("Tamaño fichero: ", String.valueOf(tam)+ " bytes");

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
