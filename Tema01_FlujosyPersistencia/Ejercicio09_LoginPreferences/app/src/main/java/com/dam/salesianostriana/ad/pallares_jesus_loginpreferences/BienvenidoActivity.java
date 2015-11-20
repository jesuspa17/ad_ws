package com.dam.salesianostriana.ad.pallares_jesus_loginpreferences;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class BienvenidoActivity extends AppCompatActivity {
    //Elemento de la UI
    Button cerrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenido);
        //Se rescata el elemento
        cerrar = (Button) findViewById(R.id.btn_cerrar);
        //Da funcionalidad al botón cerrar
        cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Coloca en las preferencias de usuario, recordar como False para que el MainActivity no inicie
                //directamente este BienvenidoActivity al iniciar de nuevo la aplicación.
                SharedPreferences prefs = getSharedPreferences("NuevasPreferencias", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();

                editor.putBoolean("recordar", false);
                editor.commit();
                //Cierra este activity y abre el de login.
                BienvenidoActivity.this.finish();
                Intent i =new Intent(BienvenidoActivity.this, LoginActivity.class);
                startActivity(i);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bienvenido, menu);
        return true;
    }

    /**
     * Al darle al botón atrás del móvil, cerrará direcamente la aplicación.
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
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