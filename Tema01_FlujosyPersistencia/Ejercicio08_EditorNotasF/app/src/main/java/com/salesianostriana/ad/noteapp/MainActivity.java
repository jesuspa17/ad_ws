package com.salesianostriana.ad.noteapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
     (8) Editor de notas

     Implementar una aplicación que sea capaz de realizar las funciones de un editor
     de texto plano de ficheros que se encuentren en memoria externa.

     La aplicación debe tener dos modos de funcionamiento:

     Por un lado, debe permitir la creación de nuevas notas de texto. La interfaz de usuario incluirá dos EditText,
     uno para el nombre del fichero, y otro para el texto de la nota (este segundo, deberá ser multilínea).
     Además, también deberá incluir un Button que permita almacenar el contenido de la nota en un fichero
     (el nombre del fichero siempre debe incluir la extensión .txt). La ruta base del almacenamiento será
     siempre la que devuelva el método  getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS) (ver tutorial de sgoliver).
     Por otro lado, debe permitir editar notas de texto existentes. Para ello, en el proyecto que se adjunta,
     se ha implementado un FileChooser, es decir, un componente que permite escoger un fichero de la memoria externa,
     y que nos devolverá la ruta de dicho fichero. Este selector está limitado a los ficheros cuya extensión sea .txt.

 */

public class MainActivity extends Activity {

    final int FILE_CHOOSER = 1;

    Button btn_abrir;
    String filePathSelected;

    Button btn_guardar;
    EditText contenido;
    EditText nombre_archivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_abrir = (Button) findViewById(R.id.btn_abrir);
        btn_guardar = (Button) findViewById(R.id.btn_guardar);

        contenido = (EditText) findViewById(R.id.edit_contenido);
        nombre_archivo = (EditText) findViewById(R.id.edit_nombreTxt);


        btn_guardar.setOnClickListener(new View.OnClickListener(){
        final String msg = "Ya existe el archivo";
            @Override
            public void onClick(View v) {
                try {

                    String nombre = nombre_archivo.getText().toString();
                    String cont = contenido.getText().toString();

                    File ruta_sd = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_NOTIFICATIONS);
                    File f = new File(ruta_sd, nombre);

                    OutputStreamWriter fout= new OutputStreamWriter(new FileOutputStream(f));
                    fout.write(cont);
                    fout.close();


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {

                    nombre_archivo.setText("");
                    contenido.setText("");

                }

            }
        });

        btn_abrir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FileChooser.class);
                ArrayList<String> extensions = new ArrayList<String>();
                extensions.add(".txt");
                intent.putStringArrayListExtra("filterFileExtension", extensions);
                startActivityForResult(intent, FILE_CHOOSER);

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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if ((requestCode == FILE_CHOOSER) && (resultCode == -1)) {
            String fileSelected = data.getStringExtra("fileSelected");
            String nom = data.getStringExtra("nomArchivo");
            filePathSelected = fileSelected;
            Toast.makeText(this, fileSelected, Toast.LENGTH_SHORT).show();

            try {

                BufferedReader br = new BufferedReader(new FileReader(fileSelected));
                nombre_archivo.setText(nom);
                String linea = "";
                StringBuilder texto_final = new StringBuilder(linea);

                while(linea != null){
                    texto_final.append(linea);
                    texto_final.append(System.getProperty("line.separator"));
                    linea = br.readLine();
                }
                contenido.setText(texto_final);
                br.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
