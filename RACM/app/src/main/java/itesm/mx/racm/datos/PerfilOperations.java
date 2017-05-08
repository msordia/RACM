package itesm.mx.racm.datos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by sergiosanchez on 04/04/17.
 */

public class PerfilOperations {

    private SQLiteDatabase db;
    private RACM_DBHelper dbHelper;
    private Perfil perfil;

    public PerfilOperations(Context context) {
        this.dbHelper = new RACM_DBHelper(context);
    }


    public void open() throws SQLException {
        try{
            db= dbHelper.getWritableDatabase();
        }catch(SQLException e){
            Log.e("SQLOPEN", e.toString());
        }
    }

    public void close(){
        db.close();
    }


    //Acciones sobre Base de Datos

    public Perfil findPerfil(){
        String query = "Select * FROM " + DataBaseSchema.PerfilTable.TABLE_NAME;
        try{
            Cursor cursor = db.rawQuery(query, null);
            perfil = null;
            if(cursor.moveToFirst()){
                perfil = new Perfil(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getBlob(7));

            }
            cursor.close();
        }catch (SQLException e){
            Log.e("SQLFind", e.toString());
        }
        return perfil;
    }

    public long registrarPerfil(Perfil perfil){
        long newRowId= 0;
        try{
            ContentValues values = new ContentValues();
            values.put(DataBaseSchema.PerfilTable.COLUMN_NAME_NOMBRE,perfil.getNombre());
            values.put(DataBaseSchema.PerfilTable.COLUMN_NAME_CELULAR,perfil.getTelefonoFIjo());
            values.put(DataBaseSchema.PerfilTable.COLUMN_NAME_TELEFONO,perfil.getTelefonoFIjo());
            values.put(DataBaseSchema.PerfilTable.COLUMN_NAME_DIRECCION,perfil.getDireccion());
            values.put(DataBaseSchema.PerfilTable.COLUMN_NAME_UBICACION,perfil.getDireccion());
            values.put(DataBaseSchema.PerfilTable.COLUMN_NAME_IMAGEN,perfil.getFoto());

            newRowId = db.insert(DataBaseSchema.PerfilTable.TABLE_NAME,null,values);
        }catch(SQLException e){
            Log.e("SQLADD",e.toString());
        }
        return newRowId;
    }

   /* public Perfil obtenerPerfilDetalle() {

        String [] columns = {DataBaseSchema.PerfilTable._ID, DataBaseSchema.PerfilTable.COLUMN_NAME_NOMBRE,
                DataBaseSchema.PerfilTable.COLUMN_NAME_CELULAR, DataBaseSchema.PerfilTable.COLUMN_NAME_TELEFONO,
                DataBaseSchema.PerfilTable.COLUMN_NAME_CATEGORIA, DataBaseSchema.PerfilTable.COLUMN_NAME_EMERGENCIA,
                DataBaseSchema.PerfilTable.COLUMN_NAME_FAVORITO, DataBaseSchema.PerfilTable.COLUMN_NAME_IMAGEN};
        Cursor cursor = db.query(DataBaseSchema.PerfilTable.TABLE_NAME,
                columns, DataBaseSchema.PerfilTable.COLUMN_NAME_NOMBRE, null, null, null, null);
        StringBuffer buffer = new StringBuffer();
        while(cursor.moveToNext()) {
            perfil = new Perfil(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getBlob(7));
        }
        return perfil;
    }

    */

    public String ups() {
        String count = "SELECT count(*) FROM " + DataBaseSchema.PerfilTable.TABLE_NAME;
        Cursor mcursor = db.rawQuery(count, null);
        mcursor.moveToFirst();
        int icount = mcursor.getInt(0);
        if(icount>0) {
            return "siii";
        }
        else{
            return Integer.toString(icount);
        }
    }

}
