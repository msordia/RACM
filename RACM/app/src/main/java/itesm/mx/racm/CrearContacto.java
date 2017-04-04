package itesm.mx.racm;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

import itesm.mx.racm.datos.Contacto;
import itesm.mx.racm.datos.ContactoOperations;

public class CrearContacto extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener{

    Button btnGuardar;
    EditText etNombre;
    EditText etTelefono;
    EditText etCelular;
    CheckBox checkEmergencia;
    CheckBox checkFavorito;
    ImageView ivFoto;
    Spinner spinnerCategorias;
    int posicionCategoria;
    ContactoOperations dao;

    Bitmap imageBitmap;
    int REQUEST_CODE = 1;
    Bitmap bitmap;
    byte[] byteArray;
    int checkEme;
    int checkFav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_contacto);
        byteArray = new byte[1];
        checkEme = 0;
        checkFav = 0;
        dao = new ContactoOperations(this);
        dao.open();

        spinnerCategorias = (Spinner)findViewById(R.id.spinnner_categoriasCC);
        String[] items = new String[]{"Seleccione la categoría","Familia", "Amigos", "Salud", "Proveedores", "Servicios"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spinnerCategorias.setAdapter(adapter);
        spinnerCategorias.setOnItemSelectedListener(this);
        btnGuardar = (Button) findViewById(R.id.button_guardarCC);
        etNombre = (EditText) findViewById(R.id.edit_nombreCC);
        etTelefono = (EditText) findViewById(R.id.edit_telefonoCC);
        etCelular = (EditText) findViewById(R.id.edit_celularCC);
        checkEmergencia = (CheckBox) findViewById(R.id.check_EmergenciaCC);
        checkFavorito = (CheckBox) findViewById(R.id.check_FavoritoCC);
        ivFoto = (ImageView)findViewById(R.id.image_fotoCC);

        ivFoto.setOnClickListener(this);
        btnGuardar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_fotoCC:


                //Intent a Media Store
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(intent.resolveActivity(getPackageManager()) != null){
                    startActivityForResult(intent, REQUEST_CODE);
                }

                break;
            case R.id.button_guardarCC:

                //Guardar el contacto en la base de datos

                if(posicionCategoria == 0 || etNombre.getText().toString().isEmpty() || etTelefono.getText().toString().isEmpty() || etCelular.getText().toString().isEmpty())  {
                    Toast.makeText(getApplicationContext(), "Favor de completar la información", Toast.LENGTH_SHORT).show();
                }else{

                    nuevoContacto();
                    finish();
                }
                /*
                //Intent a Contacto Detalle
                Intent intent2 = new Intent(this, ContactoDetalle.class);
                startActivity(intent2);
                */
                break;

            default:
                break;
        }
    }


    public Contacto nuevoContacto(){
        if (checkEmergencia.isChecked()){

            checkEme = 1;
        }

        if (checkFavorito.isChecked()){

            checkFav = 1;
        }

        Contacto contacto = new Contacto(etNombre.toString(), etCelular.toString(), etTelefono.toString(), posicionCategoria, checkEme, checkFav, byteArray);

        long id = dao.añadirContacto(contacto);
        contacto.setId(id);

        Toast.makeText(getApplicationContext(), "Contacto Agregado", Toast.LENGTH_SHORT).show();
        finish();
        return contacto;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            ivFoto.setImageBitmap(imageBitmap);


            bitmap = (Bitmap)data.getExtras().get("data");
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            //Imagen que se pasa con el contacto
            byteArray = stream.toByteArray();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
     //Sacar la posicion y pasarla a posicionCategoria
        posicionCategoria = position;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
