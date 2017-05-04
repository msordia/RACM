package itesm.mx.racm;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

import itesm.mx.racm.datos.Contacto;
import itesm.mx.racm.datos.ContactoOperations;

public class EditarContacto extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    Button btnGuardar;
    MenuFragment fragmentoMenu;
    ImageButton ibFoto;
    EditText etNombre;
    EditText etTelefono;
    CheckBox checkEmergencia;
    CheckBox checkFavorito;
    Spinner spinnerCategorias;
    int posicionCategoria;

    Bitmap imageBitmap;
    int REQUEST_CODE = 1;
    Bitmap bitmap;
    byte[] byteArray;

    long idContacto;
    ContactoOperations dao_Contactos;
    Contacto contacto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_contacto);

        fragmentoMenu = (MenuFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_menu);

        btnGuardar = (Button) findViewById(R.id.button_guardarEC);
        btnGuardar.setOnClickListener(this);

        btnGuardar = (Button) findViewById(R.id.button_guardarEP);
        etNombre = (EditText) findViewById(R.id.edit_nombreEC);
        etTelefono = (EditText) findViewById(R.id.edit_telefonoEC);
        checkEmergencia = (CheckBox) findViewById(R.id.check_EmergenciaEC);
        checkFavorito = (CheckBox) findViewById(R.id.check_FavoritoEC);
        ibFoto = (ImageButton) findViewById(R.id.image_fotoEC);
        ibFoto.setOnClickListener(this);

        spinnerCategorias = (Spinner)findViewById(R.id.spinnner_categoriasEC);
        String[] items = new String[]{"Seleccione la categoría","Familia", "Amigos", "Salud", "Proveedores", "Servicios"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spinnerCategorias.setAdapter(adapter);
        spinnerCategorias.setOnItemSelectedListener(this);

        dao_Contactos = new ContactoOperations(this);
        dao_Contactos.open();

        Bundle bundle= getIntent().getExtras();

        if(bundle!=null) {
            idContacto = bundle.getLong("idContacto");
            Log.d("Este es el id", String.valueOf(idContacto));
            contacto = dao_Contactos.findContacto(idContacto);
            Log.d("Nombre", contacto.getNombre());
        }
        spinnerCategorias.setSelection(contacto.getCategoria());

        if(contacto.getEmergencia() == 1) {
            checkEmergencia.setChecked(true);
        }
        if(contacto.getFavorito() == 1) {
            checkFavorito.setChecked(true);
        }
        etNombre.setText(contacto.getNombre());
        etTelefono.setText(contacto.getTelefono());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_guardarEC:
                //Intent a Contacto Detalle
                Intent intent = new Intent(this, ContactoDetalle.class);
                intent.putExtra("idContacto",contacto.getId());
                startActivity(intent);

                break;

            case R.id.image_fotoEC:
                //Intent a Cambiar Foto
                Intent intentFoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(intentFoto.resolveActivity(getPackageManager()) != null){
                    startActivityForResult(intentFoto, REQUEST_CODE);
                }
                break;
            case R.id.button_guardarCC:

                //Guardar el contacto en la base de datos

                if(posicionCategoria == 0 || etNombre.getText().toString().isEmpty() || etTelefono.getText().toString().isEmpty())  {
                    Toast.makeText(getApplicationContext(), "Favor de completar la información", Toast.LENGTH_SHORT).show();
                }else{

                    //nuevoContacto();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            ibFoto.setImageBitmap(imageBitmap);


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
