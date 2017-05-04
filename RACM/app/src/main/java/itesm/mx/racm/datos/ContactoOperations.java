package itesm.mx.racm.datos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class ContactoOperations {

    private SQLiteDatabase db;
    private RACM_DBHelper dbHelper;
    private Contacto contacto;

    public ContactoOperations(Context context) {
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

   public long añadirContacto(Contacto contacto){
        long newRowId= 0;
        try{
            ContentValues values= new ContentValues();
            values.put(DataBaseSchema.ContactoTable.COLUMN_NAME_NOMBRE,contacto.getNombre());
            values.put(DataBaseSchema.ContactoTable.COLUMN_NAME_CELULAR,contacto.getCelular());
            values.put(DataBaseSchema.ContactoTable.COLUMN_NAME_TELEFONO,contacto.getTelefono());
            values.put(DataBaseSchema.ContactoTable.COLUMN_NAME_CATEGORIA,contacto.getCategoria());
            values.put(DataBaseSchema.ContactoTable.COLUMN_NAME_EMERGENCIA,contacto.getEmergencia());
            values.put(DataBaseSchema.ContactoTable.COLUMN_NAME_FAVORITO,contacto.getFavorito());
            values.put(DataBaseSchema.ContactoTable.COLUMN_NAME_IMAGEN,contacto.getFoto());


            newRowId = db.insert(DataBaseSchema.ContactoTable.TABLE_NAME,null,values);
        }catch(SQLException e){
            Log.e("SQLADD",e.toString());
        }
        return newRowId;
    }


    public ArrayList<Contacto> obtenerContactos(){
        ArrayList<Contacto> listaContactos= new ArrayList<Contacto>();

        String selectQuery= "SELECT * FROM "+ DataBaseSchema.ContactoTable.TABLE_NAME+ " Order By idCategoria ASC";;
        Log.d("Select", selectQuery);

        try{
            Cursor cursor= db.rawQuery(selectQuery, null);
            if(cursor.moveToFirst()){
                do{
                    contacto= new Contacto(
                            cursor.getInt(0),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3),
                            cursor.getInt(4),
                            cursor.getInt(5),
                            cursor.getInt(6),
                            cursor.getBlob(7));

                    listaContactos.add(contacto);
                }while(cursor.moveToNext());
            }
            cursor.close();
        }catch(SQLException e){
            Log.e("SQLList", e.toString());
        }
        return listaContactos;
    }

    public void modificarContacto(Contacto contacto){
        try{
            ContentValues values= new ContentValues();
            values.put(DataBaseSchema.ContactoTable.COLUMN_NAME_NOMBRE,contacto.getNombre());
            values.put(DataBaseSchema.ContactoTable.COLUMN_NAME_CELULAR,contacto.getCelular());
            values.put(DataBaseSchema.ContactoTable.COLUMN_NAME_TELEFONO,contacto.getTelefono());
            values.put(DataBaseSchema.ContactoTable.COLUMN_NAME_CATEGORIA,contacto.getCategoria());
            values.put(DataBaseSchema.ContactoTable.COLUMN_NAME_EMERGENCIA,contacto.getEmergencia());
            values.put(DataBaseSchema.ContactoTable.COLUMN_NAME_FAVORITO,contacto.getFavorito());
            values.put(DataBaseSchema.ContactoTable.COLUMN_NAME_IMAGEN,contacto.getFoto());

            String selection= DataBaseSchema.ContactoTable._ID+"=?";
            String[] selectionArgs = {Long.toString(contacto.getId())};
            db.update(DataBaseSchema.ContactoTable.TABLE_NAME,values,selection,selectionArgs);
        }catch(SQLException e){
            Log.e("SQLUPD",e.toString());
        }
    }

    public void borrarContacto(long id) {
        try{
        db.delete(DataBaseSchema.ContactoTable.TABLE_NAME, DataBaseSchema.ContactoTable._ID+"="+id, null);
        }catch(SQLException e){
            Log.e("SQLDEL",e.toString());
        }
    }
}
