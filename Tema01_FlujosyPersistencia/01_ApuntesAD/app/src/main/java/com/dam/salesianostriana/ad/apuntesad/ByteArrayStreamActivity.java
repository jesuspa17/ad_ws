package com.dam.salesianostriana.ad.apuntesad;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ByteArrayStreamActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**ByteArrayInputStream / ByteArrayOutputStream
         *
         * En determinadas ocasiones, nos puede interesar trabajar
         * con la memoria RAM como si de el origen o destino de un flujo se tratase.
         */

        final int TAM = 32 * 1024;
        byte [] buffer = new byte[TAM];

        //ESCRITURA

        try {

            BufferedInputStream bin = new BufferedInputStream(openFileInput("miarchivo.txt"));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            int len;
            while ((len = bin.read(buffer)) > 0){
                baos.write(buffer,0,TAM);
            }
            byte [] result = baos.toByteArray();

            bin.close();
            baos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //LECTURA
        byte [] src = new byte[1];
        try {

            ByteArrayInputStream bain = new ByteArrayInputStream(src);
            BufferedOutputStream bos = new BufferedOutputStream(openFileOutput("miarchivo.txt", Context.MODE_PRIVATE));
            int len;
            while((len = bain.read(buffer)) > 0 ){
                bos.write(buffer,0,TAM);
            }

            bain.close();
            bos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
