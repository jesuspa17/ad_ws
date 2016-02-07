package com.dam.salesianostriana.todoapp;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{


    Spinner spinner;
    ListView listView;
    ImageView imgOrdenar;

    boolean ordenar;
    String objectId;
    String [] opciones = {"Todas","Hoy","Ayer","Hace 3 días"};

    ParseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Obtengo los elementos de la UI
        spinner = (Spinner) findViewById(R.id.spinner);
        listView = (ListView)findViewById(R.id.listView);
        imgOrdenar = (ImageView) findViewById(R.id.imageViewOrdenar);

        //Obtengo el usuario que está actualmente logueado.
        currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            objectId = currentUser.getObjectId();
            toolbar.setTitle("ToDoApp de "+currentUser.get("name"));
            setSupportActionBar(toolbar);
        }

        //Adapto el spinner.
        final ArrayAdapter<String> sp_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opciones);
        sp_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(sp_adapter);
        spinner.setOnItemSelectedListener(this);


        //Acción botón añadir.
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Creo un cuadro de diálogo y le asocio el layout creado por mi.
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.add_nota);
                dialog.setTitle("Añadir nota");

                final DatePicker datePicker = (DatePicker) dialog.findViewById(R.id.datePicker2);
                final TimePicker timePicker = (TimePicker) dialog.findViewById(R.id.timePicker);
                Button guardar = (Button) dialog.findViewById(R.id.button_guardar);
                Button cerrar = (Button) dialog.findViewById(R.id.button_cerrar);
                final EditText editTextNota = (EditText) dialog.findViewById(R.id.editTextNota);

                guardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(!Objects.equals(editTextNota.getText().toString(), "")){
                            /** Añadiendo una Nota nueva**/

                            //Creo el objeto ParseObject donde se le indica en que tabla voy a insertar el objeto.
                            ParseObject nota = new ParseObject("Todo");

                            //Obtengo la fecha y la hora seleccionada de los Pickers
                            Calendar cal = Consultas.getFechaHoy();
                            cal.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(), timePicker.getCurrentHour(), timePicker.getCurrentMinute());
                            Date fecha = cal.getTime();

                            //Creo el objeto asociandole los valores que desee.
                            nota.put("Concepto", editTextNota.getText().toString());
                            nota.put("Fecha", fecha);
                            nota.put("user_id",objectId);
                            nota.saveInBackground();

                            //Coloco el spinner en la posición inicial, adapto la lista para que muestre todas y cierro la ventana de dialogo.
                            spinner.setSelection(0);
                            listView.setAdapter(new MyParseAdapter(MainActivity.this, "Todo"));
                            dialog.dismiss();

                        }else{
                            Toast.makeText(MainActivity.this,"No puede insertar una nota vacía",Toast.LENGTH_SHORT).show();
                        }


                    }
                });

                cerrar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

        ordenar = true;
        imgOrdenar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                elementoSeleccionadoSpinner();
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
           //Cerrará la sesión de Parse, cerrará el activity y volverá a la pantalla de Login.
            if (currentUser != null) {
                ParseUser.logOut();
                currentUser = null;
                this.finish();
                startActivity(new Intent(MainActivity.this, DispatchActivity.class));
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        elementoSeleccionadoSpinner();
    }

    //Método que adapta el listView en función de la posición.
    public void elementoSeleccionadoSpinner(){

        String op = (String) spinner.getSelectedItem();

        if(op.equalsIgnoreCase("Hoy")){
            listView.setAdapter(new MyParseAdapter(MainActivity.this,new MiConsulta(Consultas.consultarNotasDeHoy(),ordenar,objectId)));
        }else if(op.equalsIgnoreCase("Todas")){
            listView.setAdapter(new MyParseAdapter(MainActivity.this,new MiConsulta(Consultas.ordenarTodas(),ordenar,objectId)));
        }else if(op.equalsIgnoreCase("Ayer")){
            listView.setAdapter(new MyParseAdapter(MainActivity.this,new MiConsulta(Consultas.consultarNotasAyer(),ordenar,objectId)));
        }else if(op.equalsIgnoreCase("Hace 3 días")){
            listView.setAdapter(new MyParseAdapter(MainActivity.this,new MiConsulta(Consultas.consultaHaceTresDias(),ordenar,objectId)));
        }
        ordenar = !ordenar;

    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
