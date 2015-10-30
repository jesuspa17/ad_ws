package com.dam.salesianostriana.ad.pallares_suarez_jesus_examenud1;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Jesús Pallares Suárez
 *
 * Examen AD, Tema 1. TIPO A
 *
 */

public class MainActivity extends AppCompatActivity {

    //Atributos

    TextView carrito;
    EditText concepto, cantidad;
    Button anyadir;
    StringBuilder sb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        carrito = (TextView) findViewById(R.id.textViewCarrito);
        concepto = (EditText) findViewById(R.id.editConcepto);
        cantidad = (EditText)findViewById(R.id.editCantidad);
        anyadir = (Button) findViewById(R.id.btn_anyadir);

        sb = new StringBuilder(carrito.getText().toString());
        final ArrayList<String> listaProductos = new ArrayList<String>();

           //Flujo de lectura
            try {

                DataInputStream dis = new DataInputStream(openFileInput("listadelacompra.txt"));
                int c;
                char separador;
                String con;
                char espacio;

                while (dis.available()> 0){
                    c = dis.readInt();
                    separador = dis.readChar();
                    con = dis.readUTF();
                    espacio = dis.readChar();

                    sb.append("["+c+"] "+con+"\n");
                    carrito.setText(sb);
                }

                dis.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        anyadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(cantidad.getText().toString().isEmpty() || concepto.getText().toString().isEmpty()){

                    Toast.makeText(MainActivity.this, "Rellene campos vacíos",Toast.LENGTH_SHORT).show();

                }else {

                    int cant = Integer.parseInt(cantidad.getText().toString());
                    String concep = concepto.getText().toString();

                    //Flujo de escritura.
                    try {
                        DataOutputStream dos = new DataOutputStream(openFileOutput("listadelacompra.txt", Context.MODE_APPEND));
                        dos.writeInt(cant);
                        dos.writeChar(';');
                        dos.writeUTF(concep);
                        dos.writeChar('\n');

                        listaProductos.add("[" + cant + "] " + concep + "\n");

                        sb.append("[" + cant + "] " + concep + "\n");
                        carrito.setText(sb);

                        dos.close();
                        cantidad.setText("");
                        concepto.setText("");
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

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
