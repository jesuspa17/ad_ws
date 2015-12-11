package com.dam.salesianostriana.ad.greendaogenerator;

import java.nio.file.Files;
import java.nio.file.Paths;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;
import de.greenrobot.daogenerator.ToMany;

public class GreenDaoGenerator {
    public static void main(String[] args) {

        Schema schema = new Schema(1000, "");
        createTables(schema);
        try {

            if (!Files.isDirectory(Paths.get("./src-gen")))
                Files.createDirectory(Paths.get("./src-gen"));

            new DaoGenerator().generateAll(schema, "./src-gen");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void createTables(Schema schema){

        //Tabla alimento
        Entity alimento = schema.addEntity("Alimento");
        alimento.addIdProperty();
        alimento.addStringProperty("nombre").notNull();


        //Tabla caja
        Entity caja = schema.addEntity("Caja");
        caja.addIdProperty();
        caja.addIntProperty("numero").notNull();

        //Tabla alimCaja
        Entity alimCaja = schema.addEntity("AlimCaja");
        alimCaja.addIdProperty();
        alimCaja.addIntProperty("cantidad").notNull();

        Property alimento_id = alimCaja.addLongProperty("alimento_id").notNull().getProperty();
        alimCaja.addToOne(alimento, alimento_id);

        ToMany alimetoToAlimCaja = alimento.addToMany(alimento, alimento_id);
        alimetoToAlimCaja.setName("alimentos");

        Property caja_id = alimCaja.addLongProperty("caja_id").notNull().getProperty();
        alimCaja.addToOne(caja, caja_id);

        ToMany cajaToAlimCaja = caja.addToMany(caja,caja_id);
        cajaToAlimCaja.setName("cajas");


    }

}
