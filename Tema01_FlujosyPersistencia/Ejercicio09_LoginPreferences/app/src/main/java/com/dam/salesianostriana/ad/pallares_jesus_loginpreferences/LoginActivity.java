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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    //Elementos de la UI a rescatar
    EditText email;
    EditText password;
    CheckBox recordar;
    Button login;
    Intent i;
    //Usuario y contraseña que se deben introducir para acceder a BienvenidoActivity.
    final String usuario = "admin@admin.com";
    final String pass = "12345";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Rescatamos los elementos de la UI
        email = (EditText) findViewById(R.id.editEmail);
        password = (EditText) findViewById(R.id.editPass);
        recordar = (CheckBox) findViewById(R.id.cb_recrodar);
        login = (Button) findViewById(R.id.btn_login);

        //Añadimos funcionalidad al botón Login.
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Llamada al método comprobarLogeo() para comprobar previamente si se han introducido correctamente los datos
                if (comprobarLogeo()) {
                    //Si el CheckBox "Recordar" está marcado, guardará el usuario y contraseña en un archivo que almacenará
                    //las preferencias del usuario. Si no, accederá a la pantalla Bienvenida sin guardar las preferencias
                    if (recordar.isChecked()) {

                        //Archivo donde se almacenarán las preferencias.
                        SharedPreferences prefs =
                                getSharedPreferences("NuevasPreferencias", Context.MODE_PRIVATE);

                        //Se guardan las preferencias.
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putBoolean("recordar", recordar.isChecked());
                        editor.putString("email", email.getText().toString());
                        editor.putString("pass", password.getText().toString());

                        //Se suben.
                        editor.commit();

                        //Se inicia el otro Activity y se cierra este.
                        i = new Intent(LoginActivity.this, BienvenidoActivity.class);
                        startActivity(i);
                        LoginActivity.this.finish();

                    } else {
                        //Se inicia el otro Activity y se cierra este.
                        i = new Intent(LoginActivity.this, BienvenidoActivity.class);
                        startActivity(i);
                        LoginActivity.this.finish();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Usuario o contraseña incorrectos.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    /**
     * Devuelve True si la contraseña y el usuario son correctas
     * @return True
     */
    public boolean comprobarLogeo(){
        return email.getText().toString().trim().equals(usuario) && password.getText().toString().trim().equals(pass);
    }

    @Override
    protected void onStart() {
        super.onStart();
       // cargarConfiguracion();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
