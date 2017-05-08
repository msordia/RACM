package itesm.mx.racm.datos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class CategoriaOperations {
    private SQLiteDatabase db;
    private RACM_DBHelper dbHelper;
    private Categoria categoria;

    public CategoriaOperations(Context context) {
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

    public long añadirCategoria(Categoria Categoria){
        long newRowId= 0;
        try{
            ContentValues values= new ContentValues();
            values.put(DataBaseSchema.CategoriaTable.COLUMN_NAME_NOMBRE,Categoria.getNombreCategoria());

            newRowId = db.insert(DataBaseSchema.CategoriaTable.TABLE_NAME,null,values);
        }catch(SQLException e){
            Log.e("SQLADD",e.toString());
        }
        return newRowId;
    }


    public ArrayList<Categoria> obtenerCategorias(){
        ArrayList<Categoria> listaCategorias= new ArrayList<Categoria>();

        String selectQuery= "SELECT * FROM "+ DataBaseSchema.CategoriaTable.TABLE_NAME;
        Log.d("Select", selectQuery);

        try{
            Cursor cursor= db.rawQuery(selectQuery, null);
            if(cursor.moveToFirst()){
                do{
                    categoria= new Categoria(
                            cursor.getInt(0),
                            cursor.getString(1));

                    listaCategorias.add(categoria);
                }while(cursor.moveToNext());
            }
            cursor.close();
        }catch(SQLException e){
            Log.e("SQLList", e.toString());
        }
        return listaCategorias;
    }
}
