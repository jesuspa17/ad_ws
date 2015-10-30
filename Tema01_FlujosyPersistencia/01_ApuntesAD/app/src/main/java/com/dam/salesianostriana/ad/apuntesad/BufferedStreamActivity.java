package com.dam.salesianostriana.ad.apuntesad;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class BufferedStreamActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /** BufferedInputStream / BufferedOutputStream
         *Lectura y escritura Byte a Byte.
         */

        final int TAM = 32 * 1024;

        //LECTURA

        try {
            BufferedInputStream bis = new BufferedInputStream(openFileInput("miarchivo"));
            int cantBytes;
            byte [] buffer = new byte[TAM];

            int c = 0;

            while((cantBytes = bis.read(buffer,0,TAM)) != -1){
                c++;
            }
            Log.i("NUM BYTES;", "num:" +c);

            bis.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        //ESCRITURA

        try {
            BufferedOutputStream bos = new BufferedOutputStream(openFileOutput("miarchivo.txt", Context.MODE_APPEND));

            byte [] buffer = new byte[TAM];
            int contador = 255;
            while(contador == 0){
                contador--;
                bos.write(buffer,0, TAM);
            }
            
           bos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
