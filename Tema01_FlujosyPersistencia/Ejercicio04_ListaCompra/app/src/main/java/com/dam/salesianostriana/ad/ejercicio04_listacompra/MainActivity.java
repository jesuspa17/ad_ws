package com.dam.salesianostriana.ad.ejercicio04_listacompra;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Jesús Pallares
 */
 
 /**
  (4) Lista de la compra con DataInputStream / DataOutputStream
  
  Implementar una app que nos permita almacenar la lista de la compra. Para ello, nuestro MainActivity contará con los siguientes elementos en la interfaz de usuario:

    Un EditText (concepto), que nos permitirá recoger que elemento vamos a comprar (patatas, huevos, leche).
    Otro EditText (cantidad), que nos permitirá recoger la cantidad de elementos del tipo anterior que recojamos.
    Un TextView (listaCompra), que nos permitirá mostrar nuestra compra. Deberá mostrar la información en el siguiente formato (se puede montar haciendo uso de un StringBuilder y añadir saltos de línea mediante \n).
        
        [5] Litros de leche
        [1] Docena de huevos
        [3] Barras de pan

    Un Button (add): este botón realizará las siguientes tareas
        Añadir al ArrayList<Integer> cantidades la cantidad recogida en el EditText correspondiente
        Añadir al ArrayList<String> conceptos el concepto recogido en el EditText correspondiente
    Un Button (save): este botón almacenará en un fichero en memoria interna, llamado listaCompra.dat, la información almacenada en ambos ArrayList. Haciendo uso de un DataOutputStream, iremos almacenando un entero, una cadena, un entero, ...
    Un Button (reset): este botón reseteará los dos ArrayList (cantidades y conceptos), así como el TextView listaCompra.

Definida la interfaz, la sistemática de la app será la siguiente:

- El usuario comenzará a usar la app
- Añadirá elementos con sus correspondientes cantidades a la lista de la compra.
- Si desea almacenar esta lista en memoria interna, pulsará sobre el botón correspondiente.
- Al entrar por segunda vez a la app, debe rescatar la lista de la compra que esté almacenada, y mostrarla en el correspondiente TextView
- Si pulsa el botón de salvar sin ningún elemento, se vaciará el fichero.
  **/

public class MainActivity extends AppCompatActivity{

    //variables
    private TextView carrito;
    private EditText producto, cantidad;
    private Button add, save, reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Rescato los elementos de la UI
        carrito = (TextView) findViewById(R.id.txtCarrito);
        producto = (EditText) findViewById(R.id.txt_producto);
        cantidad = (EditText) findViewById(R.id.txt_cantidad);
        add = (Button) findViewById(R.id.btn_add);
        save = (Button) findViewById(R.id.btn_save);
        reset = (Button) findViewById(R.id.btn_reset);


        //Arrays donde se almacenarán los datos
        final ArrayList<String> lista_productos = new ArrayList<String>();
        final ArrayList<Integer> lista_cantidad = new ArrayList<Integer>();

        //BOTON AÑADIR

        add.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View v) {
                if (cantidad.getText().toString().isEmpty() || producto.getText().toString().isEmpty()) {
                }else {

                    //se añaden productos a una lista con el precio
                    lista_productos.add(producto.getText().toString());
                    lista_cantidad.add(Integer.parseInt(cantidad.getText().toString()));



                    //añade los productos a la lista y los
                    // imprime de la manera en la que pedía el ejercicio
                    StringBuilder actualizaCarrito = new StringBuilder();
                    for (int i = 0; i < lista_cantidad.size(); i++) {
                        carrito.setText(actualizaCarrito.append("[" + lista_cantidad.get(i) + "] "+lista_productos.get(i)+"\n"));
                    }

                    //limpia campos al añadir producto
                    producto.setText("");
                    cantidad.setText("");
                }

            }
        });


        //BOTON GUARDAR

        save.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                DataOutputStream dos = null;
                try {
                    //Creamos el flujo de escritura
                    dos = new DataOutputStream(openFileOutput("listaCompra.dat", Context.MODE_PRIVATE));

                    //se escriben en el fichero los datos que queramos.
                    for (int i = 0; i < lista_cantidad.size(); i++) {
                        dos.writeInt(Integer.parseInt(lista_cantidad.get(i).toString()));
                        dos.writeUTF(lista_productos.get(i).toString());

                        Log.i("PRODUCTOS AÑADIDOS: ",lista_productos.get(i));
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (dos != null) {
                        try {
                            dos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        });

        //BOTON RESET
        reset.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v) {
                producto.setText("");
                cantidad.setText("");
                carrito.setText("");
                lista_cantidad.clear();
                lista_productos.clear();
            }
        });

        /**ESTO SIEMPRE SE EJECUTARÁ PARA TRAER EL FICHERO, ASI OBTENDREMOS LA LISTA DE ANTES**/
        //LEEMOS EL ARCHIVO MEDIANTE EL INPUT
        DataInputStream dis = null;
        StringBuilder stb = new StringBuilder();
        try {
            dis = new DataInputStream(openFileInput("listaCompra.dat"));
            String producto;
            int cantidad;

            while ((cantidad = dis.readInt()) != 1) {
                producto = dis.readUTF();
                carrito.setText(stb.append("["+cantidad + "] "
                        + producto+" \n"));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (EOFException e) {

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (dis != null)
                try {
                    dis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
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


