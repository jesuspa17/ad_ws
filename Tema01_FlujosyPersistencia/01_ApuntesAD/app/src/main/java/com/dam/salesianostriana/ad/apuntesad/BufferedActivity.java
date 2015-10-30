package com.dam.salesianostriana.ad.apuntesad;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BufferedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buffered);

        /**BufferedReader / BufferedWriter
         *  -Nos permiten un tratamiento más acorde a la realidad de los ficheros de texto.
         *  -Podemos leer/escribir cadenas de caracteres completas (String).
         */

        //LECTURA

        String texto_prueba = "Hola" +
                "\nMe llamo" +
                "\nJesús";

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(openFileInput("miarchivo.txt")));
            while ((texto_prueba = br.readLine()) != null) {
                String texto = br.readLine();
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //ESCRITURA

        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(openFileOutput("miarchivo.txt", Context.MODE_APPEND)));

            bw.write("lo que sea");

            bw.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
