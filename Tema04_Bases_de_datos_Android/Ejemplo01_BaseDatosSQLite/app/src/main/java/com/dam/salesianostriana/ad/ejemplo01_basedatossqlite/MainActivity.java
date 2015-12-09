package com.dam.salesianostriana.ad.ejemplo01_basedatossqlite;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lista;
    ArrayList<Cliente> lista_clientes;
    ClienteAdapter adaptador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lista = (ListView) findViewById(R.id.listView);

        /**AÑADIR DATOS**/

        /*//Abrimos la base de datos 'DBUsuarios' en modo escritura
        ClienteSQLite usdbh =
                new ClienteSQLite(this, "DBClientes", null, 1);

        SQLiteDatabase db = usdbh.getWritableDatabase();

        //Si hemos abierto correctamente la base de datos
        if (db != null) {
            db.execSQL("INSERT INTO Cliente (nombre, apellidos, telefono, num_pelados) VALUES ('Jesús', 'Pallares', 664595724, 0)");
            db.execSQL("INSERT INTO Cliente (nombre, apellidos, telefono, num_pelados) " +
                    "VALUES ('Nue', 'Peña', 123456789, 0)");
            db.execSQL("INSERT INTO Cliente (nombre, apellidos, telefono, num_pelados) " +
                    "VALUES ('Jose Maria', 'Montero Vargas', 987456123, 0)");
        }

        //Cerramos la base de datos
        db.close();*/

        /**CONSULTAR DATOS**/

        ClienteSQLite usdbh =
                new ClienteSQLite(this, "DBClientes", null, 1);

        SQLiteDatabase db = usdbh.getWritableDatabase();

        lista_clientes = new ArrayList<>();

        if(db!=null){

            String [] campos= new String[]{"nombre","apellidos","telefono","num_pelados"};

            Cursor c = db.query("Cliente", campos, null, null, null, null, null);

            if(c.moveToFirst()){
                do {
                    lista_clientes.add(new Cliente(c.getString(0), c.getString(1)));
                }while(c.moveToNext());
            }
            db.close();

        }

        adaptador = new ClienteAdapter(this,lista_clientes);
        lista.setAdapter(adaptador);


        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MainActivity.this,ClienteActivity.class);
                startActivity(i);
            }
        });

        registerForContextMenu(lista);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_contextual,menu);
        AdapterView.AdapterContextMenuInfo info;
        try {
            info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        } catch (ClassCastException e) {
            Log.e("TAG", "bad menuInfo", e);
            return;
        }
        String cliente_selec = lista_clientes.get(info.position).getNombre();
        menu.setHeaderTitle(cliente_selec);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int op_selecc = item.getItemId();

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        String cliente_seleccionado = lista_clientes.get(info.position).getNombre();

        if(op_selecc == R.id.Eliminar){

            ClienteSQLite usdbh =
                    new ClienteSQLite(this, "DBClientes", null, 1);

            SQLiteDatabase db = usdbh.getWritableDatabase();

            if(db!=null){
                String[] args = new String[]{cliente_seleccionado};
                db.execSQL("DELETE FROM Cliente WHERE nombre=?", args);
                lista_clientes.remove(lista_clientes.get(info.position));
                Toast.makeText(this, "Eliminado " + cliente_seleccionado, Toast.LENGTH_LONG).show();
                adaptador.notifyDataSetChanged();
            }

            db.close();


        }



        return super.onContextItemSelected(item);
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
