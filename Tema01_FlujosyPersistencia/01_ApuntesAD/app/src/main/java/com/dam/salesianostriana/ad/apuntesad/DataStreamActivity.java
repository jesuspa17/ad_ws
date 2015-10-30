package com.dam.salesianostriana.ad.apuntesad;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DataStreamActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_stream);

        //LECTURA.
        //El orden de lectura de los elementos debe estar implementado por el programador.
        //O nos lo tienen que dar.
        try {
            DataInputStream dis = new DataInputStream(openFileInput("miarchivo.txt"));

            String primerdato;
            int segundodato;
            boolean tercerdato;


            while(dis.available()>0){
               primerdato = dis.readUTF();
               segundodato = dis.readInt();
                tercerdato = dis.readBoolean();

                Log.i("LEYENDO DATOS: ", "Datos: " + primerdato + segundodato + tercerdato);
            }

            dis.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //ESCRITURA

        try {
            DataOutputStream dos = new DataOutputStream(openFileOutput("miarchivo.txt", Context.MODE_APPEND));

            dos.writeUTF("HOLA");
            dos.writeInt(5);
            dos.writeBoolean(false);

            dos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
