package com.dam.salesianostriana.ad.apuntesad;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileStreamActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //LECTURA
        FileInputStream fis = null;
        FileOutputStream fos = null;

        try {
            fis = openFileInput("miarchivo.txt");
            int bytes = 0;
            while((bytes = fis.read()) != -1){
                bytes++;
            }
            Log.i("INFORMACIÓN LEIDA", "Núm bytes; " + bytes);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //ESCRITURA

        try {
            fos = openFileOutput("miarchiv.txt", Context.MODE_PRIVATE);

            fos.write(1);

            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
