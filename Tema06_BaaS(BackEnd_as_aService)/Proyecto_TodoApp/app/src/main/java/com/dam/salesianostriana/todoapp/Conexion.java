package com.dam.salesianostriana.todoapp;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by Jesús Pallares on 03/02/2016.
 */
public class Conexion extends Application {

    final String APPLICATION_ID = "SyMfd8VbISoPIoAu9GRgU32lVoQZ7O72ZXNzWrIj";
    final String CLIENT_KEY = "EgGx0hnNNPRNmQTuEKNo4oMSTE6C9jzvgMG5hqQ7";
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, APPLICATION_ID, CLIENT_KEY);

    }
}
