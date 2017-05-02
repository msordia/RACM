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

    public Contacto findContacto(long idContacto){
        String query = "Select * FROM " + DataBaseSchema.ContactoTable.TABLE_NAME + " WHERE " + DataBaseSchema.ContactoTable._ID + " = \"" + idContacto + "\"";
        try{
            Cursor cursor = db.rawQuery(query, null);
            contacto = null;
            if(cursor.moveToFirst()){
                contacto = new Contacto(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), Integer.parseInt(cursor.getString(4)), Integer.parseInt(cursor.getString(5)), Integer.parseInt(cursor.getString(6)), cursor.getBlob(7));

            }
            cursor.close();
        }catch (SQLException e){
            Log.e("SQLFind", e.toString());
        }
        return contacto;
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

    public Contacto obtenerContactoDetalle(long iId) {

        String [] columns = {DataBaseSchema.ContactoTable._ID, DataBaseSchema.ContactoTable.COLUMN_NAME_NOMBRE,
                DataBaseSchema.ContactoTable.COLUMN_NAME_CELULAR, DataBaseSchema.ContactoTable.COLUMN_NAME_TELEFONO,
                DataBaseSchema.ContactoTable.COLUMN_NAME_CATEGORIA, DataBaseSchema.ContactoTable.COLUMN_NAME_EMERGENCIA,
                DataBaseSchema.ContactoTable.COLUMN_NAME_FAVORITO, DataBaseSchema.ContactoTable.COLUMN_NAME_IMAGEN};
        String[] selectionArg = {Long.toString(iId)};
        Cursor cursor = db.query(DataBaseSchema.ContactoTable.TABLE_NAME,
                columns, DataBaseSchema.ContactoTable._ID + "=?",selectionArg, null, null, null, null);
        while(cursor.moveToNext()) {
            contacto= new Contacto(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getInt(4),
                    cursor.getInt(5),
                    cursor.getInt(6),
                    cursor.getBlob(7));
        }
        cursor.close();
        return contacto;
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

    public ArrayList<Contacto> obtenerContactosEmergencia(){
        ArrayList<Contacto> listaContactos= new ArrayList<Contacto>();

        String query = "Select * FROM " + DataBaseSchema.ContactoTable.TABLE_NAME + " WHERE " + DataBaseSchema.ContactoTable.COLUMN_NAME_EMERGENCIA + " = \"" + 1 + "\"";
        Log.d("Select", query);

        try{
            Cursor cursor= db.rawQuery(query, null);
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
}
