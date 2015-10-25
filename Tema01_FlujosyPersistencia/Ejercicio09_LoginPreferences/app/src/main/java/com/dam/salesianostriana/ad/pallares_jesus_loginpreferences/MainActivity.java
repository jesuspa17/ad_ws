package com.dam.salesianostriana.ad.pallares_jesus_loginpreferences;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

/**
 (9) Login con Preferences

 Elaborar el sistema de logueo de una aplicación android que sea capaz de recordar, a partir de la
 segunda vez que se ejecute, el nombre de usuario y contraseña.

 La aplicación, al comenzar la primera vez, debe mostrar una pantalla para que el usuario
 se logue, introduciendo su email y su contraseña. Además, hay que incluir un botón de envío,
 y un checkbox que nos va a permitir darle al usuario la opción de recordar los datos.

 Al introducir los datos y pulsar el botón de enviar, la aplicación debe:

     1) Si está marcada la contraseña de recordar, almacenar los datos del usuario en un fichero de preferencias.

     2) Independientemente de si la casilla está marcada o no, se comprobarán los datos del usuario.
    Debe hacerse de forma estática, es decir, con un nombre de usuario y contraseña predefinido
    en dos cadenas de caracteres constantes. Si los datos de login son correctos, nos debe dar
    acceso a una pantalla que ponga en grande en el centro BIENVENIDO.

 La siguiente vez que el usuario ejecute la aplicación, si existe un fichero de preferencias,
 leerá esos datos, y los usará para acceder a la aplicación.
 */

public class MainActivity extends AppCompatActivity {

    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences prefs = getSharedPreferences("NuevasPreferencias", Context.MODE_PRIVATE);
        //Comprueba que en el archivo de las preferencias del Usuario si el booleano recordar está como True (recordado) o
        //False (no recordado), para iniciar un activity u otro. Si está recordado se logea directamente, si no muestra la pantalla
        //de Login.
        if(!prefs.getBoolean("recordar", false)){
            i = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(i);
            this.finish();
        }else{
            i = new Intent(MainActivity.this, BienvenidoActivity.class);
            startActivity(i);
            this.finish();
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
