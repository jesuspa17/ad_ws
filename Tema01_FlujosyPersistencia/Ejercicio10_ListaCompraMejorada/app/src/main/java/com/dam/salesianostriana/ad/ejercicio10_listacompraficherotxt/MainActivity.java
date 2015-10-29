package com.dam.salesianostriana.ad.ejercicio10_listacompraficherotxt;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText cantidad, concepto;
    Button anyadir, resetear;
    ListView listaCompra;

    DataOutputStream dos = null;
    DataInputStream dis = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cantidad = (EditText) findViewById(R.id.editCantidad);
        concepto = (EditText) findViewById(R.id.editConcepto);
        anyadir = (Button) findViewById(R.id.btn_anyadir);
        resetear = (Button) findViewById(R.id.btn_reset);
        listaCompra = (ListView) findViewById(R.id.listViewProductos);


        final ArrayList<String> productos = new ArrayList<String>();

        //Lee siempre el archivo para cargar en el listView la lista que ya esté almacenada.
        try {
            //Se abre flujo de lectura, lee según como esté almacenada la información
            //en el fichero y lo añade a la lista que se mostrará en el listView
            dis = new DataInputStream(openFileInput("lista_compra.dat"));

            String concep;
            int canti;
            char separador;

            while (dis.available()>0){
                canti = dis.readInt();
                separador = dis.readChar();
                concep = dis.readUTF();
                //Se añaden elementos

                productos.add(canti + " " + concep);
                Log.i("LEIDO: ", canti + " " + concep);
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

                try {
                    //Se obtienen los datos de los EditTexts
                    int cant = Integer.parseInt(String.valueOf(cantidad.getText()));
                    String conc = concepto.getText().toString();

                    //Se abre flujo de escritura, se escriben los datos obtenidos de los correspodientes
                    //EditTexts y se añaden a la lista que mostraremos en el ListView
                    dos = new DataOutputStream(openFileOutput("lista_compra.dat", Context.MODE_APPEND));
                    dos.writeInt(cant);
                    dos.writeChar(';');
                    dos.writeUTF(conc);
                    dos.close();

                    productos.add(cant + " " + conc);

                    Log.i("ESCRITO Y AÑADIDO: ", cant + " " + conc);

                    //Vacía los campos
                    cantidad.setText("");
                    concepto.setText("");

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });


        //Coloca el ArrayList en el listView a través de ArrayAdapter
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, productos);

        listaCompra.setAdapter(adapter);

        //Vacía el fichero y limpia el listView y el ArrayList
        resetear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(productos.isEmpty()){

                }else {
                    productos.clear();
                    adapter.clear();
                    try {
                        dos = new DataOutputStream(openFileOutput("lista_compra.dat", Context.MODE_PRIVATE));
                        dos.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Toast.makeText(MainActivity.this, "Lista limpiada correctamente", Toast.LENGTH_SHORT).show();
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
