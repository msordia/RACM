package itesm.mx.racm.datos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RACM_DBHelper extends SQLiteOpenHelper {

        public static final String DATABASE_NAME= "RACM.db";
        public static final int DATABASE_VERSION= 1;

        public RACM_DBHelper(Context context) {
            super(context,DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(crearTablaCategoria());
            db.execSQL(crearTablaContacto());
            db.execSQL(crearTablaPerfil());
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(borrarTablaContacto());
            db.execSQL(borrarTablaCategoria());
            db.execSQL(borrarTablaPerfil());
            onCreate(db);
        }

        public String crearTablaContacto(){
            String query = "CREATE TABLE "+
                    DataBaseSchema.ContactoTable.TABLE_NAME +
                    "("+
                    DataBaseSchema.ContactoTable._ID+ " INTEGER PRIMARY KEY,"+
                    DataBaseSchema.ContactoTable.COLUMN_NAME_NOMBRE+ " TEXT,"+
                    DataBaseSchema.ContactoTable.COLUMN_NAME_CELULAR+ " TEXT,"+
                    DataBaseSchema.ContactoTable.COLUMN_NAME_TELEFONO+ " TEXT,"+
                    DataBaseSchema.ContactoTable.COLUMN_NAME_CATEGORIA+ " INTEGER,"+
                    DataBaseSchema.ContactoTable.COLUMN_NAME_EMERGENCIA+ " INTEGER,"+
                    DataBaseSchema.ContactoTable.COLUMN_NAME_FAVORITO+ " INTEGER,"+
                    DataBaseSchema.ContactoTable.COLUMN_NAME_IMAGEN+ " TEXT, "+
                    "FOREIGN KEY("+DataBaseSchema.ContactoTable.COLUMN_NAME_CATEGORIA+") "+
                    "REFERENCES "+DataBaseSchema.CategoriaTable.TABLE_NAME+"("+
                    DataBaseSchema.CategoriaTable._ID+")"+
                    ")";
            return query;
        }

    public String crearTablaPerfil(){
        String query = "CREATE TABLE "+
                DataBaseSchema.PerfilTable.TABLE_NAME +
                "("+
                DataBaseSchema.PerfilTable._ID+ " INTEGER PRIMARY KEY,"+
                DataBaseSchema.PerfilTable.COLUMN_NAME_NOMBRE+ " TEXT,"+
                DataBaseSchema.PerfilTable.COLUMN_NAME_CELULAR+ " TEXT,"+
                DataBaseSchema.PerfilTable.COLUMN_NAME_TELEFONO+ " TEXT,"+
                DataBaseSchema.PerfilTable.COLUMN_NAME_DIRECCION+ " TEXT,"+
                DataBaseSchema.PerfilTable.COLUMN_NAME_UBICACION+ " TEXT,"+
                DataBaseSchema.PerfilTable.COLUMN_NAME_IMAGEN+ " TEXT "+
                ")";
        return query;
    }

    public String crearTablaCategoria(){
        String query = "CREATE TABLE "+
                DataBaseSchema.CategoriaTable.TABLE_NAME +
                "("+
                DataBaseSchema.CategoriaTable._ID+ " INTEGER PRIMARY KEY,"+
                DataBaseSchema.CategoriaTable.COLUMN_NAME_NOMBRE+ " TEXT "+
                ")";
        return query;
    }

    public String borrarTablaContacto(){
        String query= "DROP TABLE IF EXISTS "+
                DataBaseSchema.ContactoTable.TABLE_NAME;
        return query;
    }

    public String borrarTablaPerfil(){
        String query= "DROP TABLE IF EXISTS "+
                DataBaseSchema.PerfilTable.TABLE_NAME;
        return query;
    }

    public String borrarTablaCategoria(){
        String query= "DROP TABLE IF EXISTS "+
                DataBaseSchema.CategoriaTable.TABLE_NAME;
        return query;
    }

}