package com.dam.salesianostriana.ad.ejercicio01_kilo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.util.List;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;
import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;


// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "ALIMENTO".
*/
public class AlimentoDao extends AbstractDao<Alimento, Long> {

    public static final String TABLENAME = "ALIMENTO";

    /**
     * Properties of entity Alimento.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Nombre = new Property(1, String.class, "nombre", false, "NOMBRE");
        public final static Property Alimento_id = new Property(2, long.class, "alimento_id", false, "ALIMENTO_ID");
    };

    private DaoSession daoSession;

    private Query<Alimento> alimento_AlimentosQuery;

    public AlimentoDao(DaoConfig config) {
        super(config);
    }
    
    public AlimentoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"ALIMENTO\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"NOMBRE\" TEXT NOT NULL ," + // 1: nombre
                "\"ALIMENTO_ID\" INTEGER NOT NULL );"); // 2: alimento_id
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"ALIMENTO\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Alimento entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getNombre());
    }

    @Override
    protected void attachEntity(Alimento entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Alimento readEntity(Cursor cursor, int offset) {
        Alimento entity = new Alimento( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getString(offset + 1) // nombre
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Alimento entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setNombre(cursor.getString(offset + 1));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Alimento entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Alimento entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "alimentos" to-many relationship of Alimento. */
    public List<Alimento> _queryAlimento_Alimentos(long alimento_id) {
        synchronized (this) {
            if (alimento_AlimentosQuery == null) {
                QueryBuilder<Alimento> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.Alimento_id.eq(null));
                alimento_AlimentosQuery = queryBuilder.build();
            }
        }
        Query<Alimento> query = alimento_AlimentosQuery.forCurrentThread();
        query.setParameter(0, alimento_id);
        return query.list();
    }

}
