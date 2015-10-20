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

                    InputStream is = new InputStream() {
                        @Override
                        public int read() throws IOException {
                            return 0;
                        }
                    };

                    is = getInputStreamFromURL(url.toString());

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