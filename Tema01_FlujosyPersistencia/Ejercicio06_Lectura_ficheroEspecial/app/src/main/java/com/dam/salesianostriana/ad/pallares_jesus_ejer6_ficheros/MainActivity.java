package com.dam.salesianostriana.ad.pallares_jesus_ejer6_ficheros;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    TextView fichero;
    TextView tipo_fichero;
    TextView txt_comentarios;
    TextView txt_ancho;
    TextView txt_alto;
    TextView bytes;

    Button leer;

    int tipo, ancho, alto;
    byte color;
    String comentarios;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //rescata los elementos de la UI
        fichero = (TextView) findViewById(R.id.editFichero);
        tipo_fichero = (TextView) findViewById(R.id.textTipo);
        txt_comentarios = (TextView) findViewById(R.id.textComentarios);
        txt_ancho = (TextView) findViewById(R.id.textAncho);
        txt_alto = (TextView) findViewById(R.id.textAlto);
        bytes = (TextView) findViewById(R.id.textBytes);
        leer = (Button) findViewById(R.id.btn_leer);
        //le asignamos una tarea al pulsar sobre el botón "LEER"
        leer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {

                    String nom = fichero.getText().toString().trim();
                    //Estos ifs los hice para que el usuario al escribir el nombre del archivo lo lea.
                    //Lo hice así por que no sabía como leer un archivo que no estuviese en la memoria interna o
                    //dentro del proyecto.
                    //La ruta no la cogía bien o no la estaba poniendo bien con openFileInput
                    int num_fichero = 0;
                    if (nom.equalsIgnoreCase("fichero1.dat")) {
                        num_fichero = R.raw.fichero1;
                    } else if (nom.equalsIgnoreCase("fichero2.dat")) {
                        num_fichero = R.raw.fichero2;
                    } else if (nom.equalsIgnoreCase("fichero3.dat")) {
                        num_fichero = R.raw.fichero3;
                    } else if (nom.equalsIgnoreCase("fichero4.dat")) {
                        num_fichero = R.raw.fichero4;
                    }
                    //se le pasa el archivo a leer.
                    DataInputStream dis = new DataInputStream(getResources().openRawResource(num_fichero));
                    //se lee según el orden indicado en el ejercicio
                    while ((tipo = dis.readInt()) != 0) {
                        //se almacenan los datos leídos en las respectivas variables.
                        comentarios = dis.readUTF();
                        ancho = dis.readInt();
                        alto = dis.readInt();
                        color = dis.readByte();
                        //se muestran los resultados por la UI
                        tipo_fichero.setText(String.valueOf(tipo));
                        txt_comentarios.setText(String.valueOf(comentarios));
                        txt_alto.setText(String.valueOf(alto));
                        txt_ancho.setText(String.valueOf(ancho));
                        bytes.setText(String.valueOf(color));
                    }
                    //se cierra el flujo de lectura
                    dis.close();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
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
