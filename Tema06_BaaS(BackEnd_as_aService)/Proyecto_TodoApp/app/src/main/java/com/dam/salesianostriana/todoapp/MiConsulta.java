package com.dam.salesianostriana.todoapp;

import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

/**
 * Created by Jesus Pallares on 06/02/2016.
 */
public class MiConsulta implements ParseQueryAdapter.QueryFactory {

    ParseQuery query;
    boolean ordenar;
    String userId;

    public MiConsulta(ParseQuery query){
        this.query = query;
    }

    public MiConsulta(ParseQuery query, boolean ordenar) {
        this.query = query;
        this.ordenar = ordenar;
    }

    public MiConsulta(ParseQuery query, boolean ordenar,String userId) {
        this.query = query;
        this.ordenar = ordenar;
        this.userId = userId;
    }


    @Override
    public ParseQuery create() {

        query.whereEqualTo("user_id",userId);
        if(this.ordenar){
            query.orderByDescending("Fecha");
        }else{
            query.orderByAscending("Fecha");
        }
        return this.query;
    }
}
