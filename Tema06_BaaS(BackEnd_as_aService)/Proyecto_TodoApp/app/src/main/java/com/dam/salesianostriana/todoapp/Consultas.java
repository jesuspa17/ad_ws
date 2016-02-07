package com.dam.salesianostriana.todoapp;

import com.parse.ParseQuery;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Jesus Pallares on 06/02/2016.
 */
public class Consultas {

    /**
     * Devuelve un objeto Calendar con la fecha de hoy.
     * @return
     */
    public static Calendar getFechaHoy(){

        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY,0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal;
    }


    /**
     * Realiza la consulta que obtiene todas las notas con fecha de hoy
     * @return
     */
    public static ParseQuery consultarNotasDeHoy(){

        ParseQuery queryNotasHoy = new ParseQuery("Todo");

        Calendar cal = getFechaHoy();

        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);

        Date min = cal.getTime();

        cal.set(Calendar.HOUR, 23);
        cal.set(Calendar.MINUTE,59);
        cal.set(Calendar.SECOND,59);

        Date max= cal.getTime();

        queryNotasHoy.whereGreaterThanOrEqualTo("Fecha", min);
        queryNotasHoy.whereLessThan("Fecha", max);

        return queryNotasHoy;
    }

    /**
     * Realiza la consulta que obtiene todas las notas con fecha de ayer.
     * @return
     */
    public static ParseQuery consultarNotasAyer(){

        ParseQuery queryNotasAyer = new ParseQuery("Todo");

        Calendar cal = getFechaHoy();
        Date hoy= cal.getTime();
        cal.add(Calendar.DAY_OF_MONTH, -1);
        Date ayer = cal.getTime();

        queryNotasAyer.whereLessThanOrEqualTo("Fecha", hoy);
        queryNotasAyer.whereGreaterThan("Fecha", ayer);

        return queryNotasAyer;
    }

    /**
     * Realiza la consulta que obtiene las notas con fecha de hace tres días.
     * @return
     */
    public static ParseQuery consultaHaceTresDias(){

        ParseQuery queryNotasAyer = new ParseQuery("Todo");

        Calendar cal = getFechaHoy();

        cal.add(Calendar.DAY_OF_MONTH, -1);
        cal.set(Calendar.HOUR, 23);
        cal.set(Calendar.MINUTE,59);
        cal.set(Calendar.SECOND, 59);

        Date min= cal.getTime();

        cal.add(Calendar.DAY_OF_MONTH, -2);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND, 0);

        Date haceTres = cal.getTime();

        queryNotasAyer.whereLessThanOrEqualTo("Fecha", min);
        queryNotasAyer.whereGreaterThan("Fecha", haceTres);

        return queryNotasAyer;
    }

    /**
     * Realiza la consulta que devuelve la lista de todas las notas ordenadas ascedente o
     * descendentamente en función de true o false.
     * @return
     */
    public static ParseQuery ordenarTodas(){
        ParseQuery queryOrdenar = new ParseQuery("Todo");
        return queryOrdenar;
    }
}
