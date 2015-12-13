package com.dam.salesianostriana.ad.ejercicio01_operacionkilo;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.dam.salesianostriana.ad.ejercicio01_operacionkilo.greendao.AlimCaja;
import com.dam.salesianostriana.ad.ejercicio01_operacionkilo.greendao.AlimCajaDao;
import com.dam.salesianostriana.ad.ejercicio01_operacionkilo.greendao.Alimento;
import com.dam.salesianostriana.ad.ejercicio01_operacionkilo.greendao.AlimentoDao;
import com.dam.salesianostriana.ad.ejercicio01_operacionkilo.greendao.DaoMaster;
import com.dam.salesianostriana.ad.ejercicio01_operacionkilo.greendao.DaoSession;

public class AddAlimentoActivity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener{

    EditText cantidad;
    Button anyadir;
    Spinner spinner_alimentos;

    AlimCajaDao alimCajaDao;
    AlimentoDao alimentoDao;


    //Almacenará el nombre del producto a añadir
    String nombre;
    //Almacenara el número de caja al que se está añadiendo el producto
    long num_caja;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alimento);

        //Rescato los elementos del layout
        cantidad = (EditText) findViewById(R.id.editTextCantidad);
        anyadir = (Button) findViewById(R.id.button_add);
        spinner_alimentos = (Spinner) findViewById(R.id.spinner_alimentos);

        //Array de string que contendrá el spinner de los productos
        String [] productos = {"Enlatados","Cartón/Plástico","Granos/Pastas","Congelados","Líquidos"};

        //Creo el adaptador del spinner y se lo agrego al spinner.
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, productos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_alimentos.setAdapter(adapter);
        spinner_alimentos.setOnItemSelectedListener(this);

        //Se recoge el número de caja que ha sido enviado desde el intent ContenidoCajaActivity
        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            num_caja = extras.getLong("num_caja");
            Log.i("ID_OBTENIDA", String.valueOf(num_caja));
        }

        anyadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Se recoge la cantidad del editext
                String cant = cantidad.getText().toString();

                if(!cant.isEmpty() && !nombre.isEmpty()){
                    //Se crea el Alimento y se inserta en la tabla
                    Alimento alimento = new Alimento(null,nombre.toUpperCase());
                    alimentoDao.insert(alimento);

                    //Se crea el objeto AlimCaja con la id de la caja y alimento y se inserta en la tabla
                    AlimCaja alimCaja = new AlimCaja(Integer.parseInt(cant),alimento.getId(),num_caja);
                    alimCajaDao.insert(alimCaja);

                    Toast.makeText(AddAlimentoActivity.this,"Ha añadido a la caja, "+cant+" kg de "+nombre,Toast.LENGTH_SHORT).show();
                    cantidad.setText("");
                    Log.i("AÑADIDO", "AÑADIDO");

                }else{
                    Toast.makeText(AddAlimentoActivity.this,"Debe completar el formulario",Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Se abre la conexión a la bd.
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "operacion_kilo", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession daoSession = daoMaster.newSession();
        alimCajaDao = daoSession.getAlimCajaDao();
        alimentoDao = daoSession.getAlimentoDao();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(AddAlimentoActivity.this,ContenidoCajaActivity.class);
        i.putExtra("num_caja", num_caja);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        this.finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        nombre = (String) spinner_alimentos.getSelectedItem();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
