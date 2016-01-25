package com.dam.salesianostriana.ejemplo01_parsesdk;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by Jesús Pallares on 21/01/2016.
 */
public class MyApplication  extends Application{
    final String APPLICATION_ID = "SyMfd8VbISoPIoAu9GRgU32lVoQZ7O72ZXNzWrIj";
    final String CLIENT_KEY = "EgGx0hnNNPRNmQTuEKNo4oMSTE6C9jzvgMG5hqQ7";
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this,APPLICATION_ID,CLIENT_KEY);

    }
}
