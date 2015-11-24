package com.dam.salesianostriana.pmdm.pallaresjesus_ejercicio02_instagramrss;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 Ejercicio 2: Instagram RSS Client

 Vamos a crear una aplicación que consulte un RSS y sea capaz de extraer información de él.

 En este caso, consultaremos el API de Instagram, que nos permite obtener las últimas imágenes subidas y etiquetadas con un determinado hashtag, a través de la siguente url: http://iconosquare.com/tagFeed/[tag]. Por ejemplo:

 http://iconosquare.com/tagFeed/triana
 http://iconosquare.com/tagFeed/sevilla
 http://iconosquare.com/tagFeed/ubeda

 La aplicación tendrá un EditText y un botón, que nos servirá para recoger el "tag" sobre el cual el usuario quiere buscar fotos. Debajo de estos elementos, tendremos un RecyclerView, que visualizará un grid en el que aparecerá, como mínimo, la siguiente información:

 autor de la foto
 fecha
 la imagen

 NOTA: Hay que tener en cuenta que la imagen a mostrar se encuentra dentro del elemento <description>, y es el src de la etiqueta img, con lo cual habrá que procesar el texto para poder extraer esta información.

 <item>
    <author>miguemn</author>
    <description><![CDATA[<a href='http://iconosquare.com/p/1122364237162125994_30892469' target='_blank'>
    <img src='https://scontent.cdninstagram.com/hphotos-xpf1/t51.2885-15/s640x640/sh0.08/e35/12256883_929825957055260_541404677_n.jpg'/></a>]]></description>
    <link>http://iconosquare.com/p/1122364237162125994_30892469</link>
    <pubDate>Fri, 20 Nov 2015 11:44:25 +0100</pubDate>
    <title><![CDATA[Water Mirror #igerscadiz #igersjaen #ubeda]]></title>
 </item>
 */

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<ItemImagen> imagenes;

    EditText direccion;
    Button buscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        direccion = (EditText) findViewById(R.id.editTextBuscar);
        buscar = (Button) findViewById(R.id.btn_buscar);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new GetImagesTask().execute();

            }
        });



    }

    private class GetImagesTask extends AsyncTask<Void,Void,Rss>{

        @Override
        protected Rss doInBackground(Void... params) {

            Rss rss = null;

            try {
                URL url = new URL("http://iconosquare.com/tagFeed/triana");
                InputStream is = url.openStream();
                Serializer serializer = new Persister();
                rss = new Rss();
                rss = serializer.read(Rss.class,is,false);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return rss;

        }

        @Override
        protected void onPostExecute(Rss rss) {
            super.onPostExecute(rss);

            imagenes = new ArrayList<>();

            for(int i = 0;i<rss.getChannel().getItem().size();i++){
                String imagen = rss.getChannel().getItem().get(i).getDescripcion();
                String autor = rss.getChannel().getItem().get(i).getAutor();
                String fecha = rss.getChannel().getItem().get(i).getPubdate();
                //Tue, 24 Nov 2015 09:43:21 +0100

                imagenes.add(new ItemImagen(imagen,autor,fecha));
            }

            mAdapter = new BloqueAdapter(MainActivity.this,imagenes);
            mRecyclerView.setAdapter(mAdapter);

            Log.i("CHANNEL", rss.getChannel().getItem().get(0).getDescripcion());
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
