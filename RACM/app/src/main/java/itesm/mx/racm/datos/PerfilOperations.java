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
    private Contacto contacto;

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

    public long registrarPerfil(Perfil perfil){
        long newRowId= 0;
        try{
            ContentValues values= new ContentValues();
            values.put(DataBaseSchema.PerfilTable.COLUMN_NAME_NOMBRE,perfil.getNombre());
            values.put(DataBaseSchema.PerfilTable.COLUMN_NAME_CELULAR,perfil.getCelular());
            values.put(DataBaseSchema.PerfilTable.COLUMN_NAME_TELEFONO,perfil.getTelefonoFIjo());
            values.put(DataBaseSchema.PerfilTable.COLUMN_NAME_DIRECCION,perfil.getDireccion());
            values.put(DataBaseSchema.PerfilTable.COLUMN_NAME_UBICACION,perfil.getUbicacion());
            values.put(DataBaseSchema.PerfilTable.COLUMN_NAME_IMAGEN,perfil.getFoto());

            newRowId = db.insert(DataBaseSchema.ContactoTable.TABLE_NAME,null,values);
        }catch(SQLException e){
            Log.e("SQLADD",e.toString());
        }
        return newRowId;
    }



}
