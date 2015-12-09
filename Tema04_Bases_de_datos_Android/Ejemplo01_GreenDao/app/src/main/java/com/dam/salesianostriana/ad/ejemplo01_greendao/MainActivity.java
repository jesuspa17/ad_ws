package com.dam.salesianostriana.ad.ejemplo01_greendao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

      /*----------------------------------------------------------------------/*
       |                                                                       |
       |                  [----> PASOS PARA GREEN DAO <----]                   |
       |                                                                       |
       |-----------------------------------------------------------------------|
       |                                                                       |
       |         [0] Diseñar nuestro modelo.                                   |
       |                                                                       |
       |         [1] Generar el código fuente                                  |
       |                                                                       |
       |                 -Clases POJO (Entity) | GreenDao Generator            |
       |                 -Clases CRUD (DAO)    |       (.jar)                  |
       |                                                                       |
       |         [2] "Copiar" el código fuente generado y usarlo. | GreenDao   |
       |                                                                       |
       /*----------------------------------------------------------------------*/

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
