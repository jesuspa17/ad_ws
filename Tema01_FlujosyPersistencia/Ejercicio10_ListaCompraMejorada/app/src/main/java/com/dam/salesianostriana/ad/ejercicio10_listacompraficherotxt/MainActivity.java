package com.dam.salesianostriana.ad.ejercicio10_listacompraficherotxt;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

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


        anyadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    int cant = Integer.parseInt(String.valueOf(cantidad.getText()));
                    String conc = concepto.getText().toString();

                    ArrayList<String> productos = new ArrayList<String>();
                    String concep;
                    int canti;
                    char separador;

                    dis = new DataInputStream(openFileInput("lista_compra.dat"));

                    while ((canti = dis.readInt()) != -1){
                        separador = dis.readChar();
                        concep = dis.readUTF();

                        productos.add(canti +" "+ concep);
                    }

                    //Se instancia el flujo de escritura.
                    dos = new DataOutputStream(openFileOutput("lista_compra.dat", Context.MODE_APPEND));
                    //Se almacenan los datos.
                    dos.writeInt(cant);
                    dos.writeChar(';');
                    dos.writeUTF(conc);



                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, productos);

                    listaCompra.setAdapter(adapter);

                    listaCompra.deferNotifyDataSetChanged();

                    dos.close();
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
