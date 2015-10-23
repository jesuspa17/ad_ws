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

public class LoginActivity extends AppCompatActivity {

    EditText email;
    EditText passowrd;
    CheckBox recordar;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.editEmail);
        passowrd = (EditText) findViewById(R.id.editPass);
        recordar = (CheckBox) findViewById(R.id.cb_recrodar);
        login = (Button) findViewById(R.id.btn_login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (recordar.isChecked()) {

                 SharedPreferences prefs =
                            getSharedPreferences("NuevasPreferencias", Context.MODE_PRIVATE);

                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putBoolean("recordar", recordar.isChecked());
                    editor.putString("email", email.getText().toString());
                    editor.putString("pass", passowrd.getText().toString());

                    editor.commit();

                    Intent i = new Intent(LoginActivity.this, BienvenidoActivity.class);
                    startActivity(i);


                } else {
                    Intent i = new Intent(LoginActivity.this, BienvenidoActivity.class);
                    startActivity(i);


                }

            }
        });

    }

    public void cargarConfiguracion()
    {
        SharedPreferences prefs =
                getSharedPreferences("PreferenciasUsuario", Context.MODE_PRIVATE);
        email.setText(prefs.getString("email", "jesus@gmail.com"));
        recordar.setChecked(prefs.getBoolean("recordar", true));
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
