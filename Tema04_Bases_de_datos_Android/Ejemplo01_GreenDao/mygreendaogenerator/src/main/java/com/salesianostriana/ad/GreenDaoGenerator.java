package com.salesianostriana.ad;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class GreenDaoGenerator {

    public static void main(String[] args) throws Exception {

        //1. Creamos el esquema
        Schema schema = new Schema(1000, "de.greenrobot.daoexample");
        addNote(schema);
        //2. Se genera el GreenDao!!! =)
        new DaoGenerator().generateAll(schema, "../DaoExample/src-gen");

    }

    private static void addNote(Schema schema) {

        //Añadimos la entidad.
        Entity note = schema.addEntity("Note");

        //Añadimos las propiedades.
        note.addIdProperty();

        //Propiedad de tipo String llamada text, que tiene la propiedad que no puede ser nulo
        note.addStringProperty("text").notNull();

        //Otra tipo String, si podrá ser nulo
        note.addStringProperty("comment");

        //Otra tipo fecha llamada Date
        note.addDateProperty("date");
    }


}
