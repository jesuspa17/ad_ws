package com.dam.salesianostriana.ad.ejercicio01_operacionkilo;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.dam.salesianostriana.ad.ejercicio01_operacionkilo.greendao.AlimCajaDao;
import com.dam.salesianostriana.ad.ejercicio01_operacionkilo.greendao.Caja;
import com.dam.salesianostriana.ad.ejercicio01_operacionkilo.greendao.CajaDao;
import com.dam.salesianostriana.ad.ejercicio01_operacionkilo.greendao.DaoMaster;
import com.dam.salesianostriana.ad.ejercicio01_operacionkilo.greendao.DaoSession;

import java.util.List;

/**
 * Ejercicio 1: Operación Kilo
 * <p/>
 * Vamos a realizar una app que nos permita gestionar la organización de los alimentos de la campaña del kilo.
 * <p/>
 * Como sabes, cuando se realiza la organización de estos alimentos, se hace en dos pasos diferentes:
 * <p/>
 * Agrupar los diferentes tipos de alimentos que tenemos para contabilizar los kilo.
 * Empaquetar esos alimentos en cajas; estas se buscan que sean homogéneas, aunque alguna de
 * ellas puede ser heterogénea (es decir, tener alimentos de diferente tipo).
 * <p/>
 * Nuestra app se encargará de gestionar el segundo paso, permitiendo añadir a una caja el tipo
 * de alimento y la cantidad de kilos o unidades del mismo.
 * Todo ello se realizará desde un formulario. (Como idea, se sugiere al programador que proponga
 * al usuario los tipos de alimentos que se han introducido antes, para que pueda elegirlo en lugar de escribirlo).
 * <p/>
 * La app también tendrá la posibilidad de de mostrar un listado con todas las cajas que hay
 * y el contenido que tiene cada una, y otro listado en el que se muestren los tipos de alimentos
 * y la suma de kilos que hay de cada uno.
 */

public class MainActivity extends AppCompatActivity {


    ListView listView_cajas;
    CajaDao cajaDao;
    AlimCajaDao alimCajaDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView_cajas = (ListView) findViewById(R.id.listView);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Caja caja = new Caja(null, (cajaDao.queryBuilder().list().size() + 1));
                cajaDao.insert(caja);
                Log.i("CAJA", String.valueOf(caja.getId() + " " + caja.getNumero()));
                listView_cajas.setAdapter(new CajaAdapter(MainActivity.this));

            }
        });

        //Se abre la conexión a la bd
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "operacion_kilo", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession daoSession = daoMaster.newSession();
        cajaDao = daoSession.getCajaDao();
        alimCajaDao = daoSession.getAlimCajaDao();

        //Se adapta la lista con los datos obtenidos de la bd
        listView_cajas.setAdapter(new CajaAdapter(MainActivity.this));

        listView_cajas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MainActivity.this, ContenidoCajaActivity.class);
                Log.i("CAJA_SELECCIONADA", String.valueOf(cajaDao.queryBuilder().list().get(position).getId()));
                i.putExtra("num_caja", (long) cajaDao.queryBuilder().list().get(position).getId());
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private class CajaAdapter extends BaseAdapter {

        Context mContext;
        List<Caja> data;

        public CajaAdapter(Context mContext) {
            //this.data = data;
            this.mContext = mContext;
            data = cajaDao.queryBuilder().list();


        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = LayoutInflater.from(mContext).inflate(android.R.layout.simple_list_item_1, parent, false);

            TextView title = (TextView) row.findViewById(android.R.id.text1);

            Caja item = (Caja) getItem(position);
            title.setText("Caja " + item.getNumero());

            return row;
        }
    }
}
