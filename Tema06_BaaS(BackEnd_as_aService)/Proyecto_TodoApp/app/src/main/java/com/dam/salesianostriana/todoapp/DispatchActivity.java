package com.dam.salesianostriana.todoapp;

import com.parse.ui.ParseLoginDispatchActivity;

/**
 * Created by Jesus Pallares on 07/02/2016.
 *
 * Esta clase es lo que asocia el login de parse con la pantalla que queremos
 * que se lanze tras el logueo. En este caso el MainActivity.
 */
public class DispatchActivity extends ParseLoginDispatchActivity {

    @Override
    protected Class<?> getTargetClass() {
        return MainActivity.class;
    }


}