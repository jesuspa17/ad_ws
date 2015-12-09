package com.dam.salesianostriana.ad.ejemplo01_basedatossqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Jesús Pallares on 04/12/2015.
 */
public class ClienteSQLite extends SQLiteOpenHelper{

    String sql = "CREATE TABLE Cliente (nombre TEXT, apellidos TEXT, telefono INTEGER, num_pelados INTEGER)";


    public ClienteSQLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS Cliente");

        //Se crea la nueva versión de la tabla
        db.execSQL(sql);

    }
}
