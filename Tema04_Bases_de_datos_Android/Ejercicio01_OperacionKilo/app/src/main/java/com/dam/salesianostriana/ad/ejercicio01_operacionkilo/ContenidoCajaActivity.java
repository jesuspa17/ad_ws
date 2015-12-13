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
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.dam.salesianostriana.ad.ejercicio01_operacionkilo.greendao.AlimCaja;
import com.dam.salesianostriana.ad.ejercicio01_operacionkilo.greendao.AlimCajaDao;
import com.dam.salesianostriana.ad.ejercicio01_operacionkilo.greendao.DaoMaster;
import com.dam.salesianostriana.ad.ejercicio01_operacionkilo.greendao.DaoSession;

import java.util.List;

public class ContenidoCajaActivity extends AppCompatActivity {


    AlimCajaDao alimCajaDao;
    ListView listView;

    long num_caja;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contenido_caja);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        listView = (ListView) findViewById(R.id.listViewContenidoCaja);

        //Se recoje el id_caja del activiy de las cajas
        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            num_caja = extras.getLong("num_caja");
        }

        toolbar.setTitle("Caja " + num_caja);
        setSupportActionBar(toolbar);

        Log.i("ID_OBTENIDA", String.valueOf(num_caja));

        //Se abre la conexión a la bd
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "operacion_kilo", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession daoSession = daoMaster.newSession();
        alimCajaDao = daoSession.getAlimCajaDao();

        //Adapto la lista con la lista obtenida de la bd.
        listView.setAdapter(new ContenidoCaja(ContenidoCajaActivity.this));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ContenidoCajaActivity.this,AddAlimentoActivity.class);
                i.putExtra("num_caja",num_caja);
                startActivity(i);
            }
        });
    }

    private class ContenidoCaja extends BaseAdapter {

        Context mContext;
        List<AlimCaja> data;

        public ContenidoCaja(Context mContext) {
            this.mContext = mContext;
           //Query query = alimCajaDao.queryBuilder().where(new WhereCondition.StringCondition("CAJA_ID = "+num_caja+ " GROUP BY ALIMENTO_ID")).build();
            data = alimCajaDao.queryBuilder().where(AlimCajaDao.Properties.Caja_id.eq(num_caja)).list();

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
            View row = LayoutInflater.from(mContext).inflate(android.R.layout.simple_list_item_2, parent, false);

            TextView nombre = (TextView) row.findViewById(android.R.id.text1);
            TextView cantidad = (TextView) row.findViewById(android.R.id.text2);

            AlimCaja item_seleccionado = (AlimCaja) getItem(position);

            nombre.setText(item_seleccionado.getAlimento().getNombre().toUpperCase());
            cantidad.setText(String.valueOf(item_seleccionado.getCantidad()) + " kg");

            return row;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(ContenidoCajaActivity.this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        this.finish();
    }
}
