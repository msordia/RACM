package itesm.mx.racm;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

import itesm.mx.racm.datos.Contacto;
import itesm.mx.racm.datos.ContactoOperations;

public class EditarContacto extends AppCompatActivity implements View.OnClickListener {

    Button btnGuardar,btnEliminar;
    ImageView imgFoto;
    MenuFragment fragmentoMenu;
    ContactoOperations dao;
    EditText etNombre, etCelular, etTelefono;
    Spinner spinnerCategoria;
    CheckBox checkEmergencia,checkFavorito;
    Contacto contact;
    Bitmap imageBitmap;
    int REQUEST_CODE = 1;
    Bitmap bitmap;
    byte[] byteArray;

    private static final int SELECT_PICTURE = 2;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_contacto);

        dao = new ContactoOperations(this);
        dao.open();
        fragmentoMenu = (MenuFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_menu);

        etNombre = (EditText) findViewById(R.id.edit_nombreEC);
        etCelular = (EditText) findViewById(R.id.edit_celularEC);
        etTelefono= (EditText) findViewById(R.id.edit_telefonoEC);
        spinnerCategoria= (Spinner) findViewById(R.id.spinnner_categoriasEC);
        checkEmergencia = (CheckBox) findViewById(R.id.check_EmergenciaEC);
        checkFavorito = (CheckBox) findViewById(R.id.check_FavoritoEC);
        btnGuardar = (Button) findViewById(R.id.button_guardarEC);

        btnEliminar = (Button) findViewById(R.id.button_eliminarEC);
        imgFoto = (ImageView) findViewById(R.id.image_fotoEC);

        String[] items = new String[]{"Seleccione la categoría","Familia", "Amigos", "Salud", "Proveedores", "Servicios"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spinnerCategoria.setAdapter(adapter);

        Bundle bundle= getIntent().getExtras();
        if(bundle!=null) {
            contact = (Contacto) bundle.getSerializable("contacto");

            etNombre.setText(contact.getNombre());
            etCelular.setText(contact.getCelular());
            etTelefono.setText(contact.getTelefono());
            spinnerCategoria.setSelection(contact.getCategoria());

            if(contact.getEmergencia() == 1){
                checkEmergencia.setChecked(true);
            }
            if (contact.getFavorito()==1){
                checkFavorito.setChecked(true);
            }
            if(contact.getFoto().length != 1) {
                Bitmap imageBitmap = BitmapFactory.decodeByteArray(contact.getFoto(), 0, contact.getFoto().length);
                imgFoto.setImageBitmap(imageBitmap);
            }
        }

        btnGuardar.setOnClickListener(this);
        imgFoto.setOnClickListener(this);
        btnEliminar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_guardarEC:
                //Intent a Contacto Detalle

                Intent intent = new Intent(this, ContactoDetalle.class);
                intent.putExtra("idContacto", contact.getId());
                startActivity(intent);

                if(spinnerCategoria.getSelectedItemPosition() == 0 || etNombre.getText().toString().isEmpty() || etTelefono.getText().toString().isEmpty() || etCelular.getText().toString().isEmpty())  {
                    Toast.makeText(this, "Favor de completar la información", Toast.LENGTH_SHORT).show();
                }else{
                    dao.modificarContacto(modificar());
                    finish();
                }
                break;

            case R.id.image_fotoEC:
                //Intent a Cambiar Foto
                abrirCamera();
                break;

            case R.id.button_eliminarEC:
                showLocationDialog();
                break;
            default:
                break;
        }
    }

    public Contacto modificar(){
        contact.setNombre(etNombre.getText().toString());
        contact.setCelular(etCelular.getText().toString());
        contact.setTelefono(etTelefono.getText().toString());
        contact.setCategoria(spinnerCategoria.getSelectedItemPosition());
        if (checkEmergencia.isChecked())
            contact.setEmergencia(1);
        else
            contact.setEmergencia(0);

        if (checkFavorito.isChecked())
            contact.setFavorito(1);
        else
            contact.setFavorito(0);
        contact.setFoto(byteArray);
        return contact;
    }

    private void showLocationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.alertDialog);
        builder.setTitle("Eliminar Contacto");
        builder.setMessage("¿Seguro que desea el eliminar a "+contact.getNombre()+"?");
        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dao.borrarContacto(contact.getId());
                Toast.makeText(getApplicationContext(), "Contacto eliminado con éxito", Toast.LENGTH_SHORT).show();

                Intent intentLista = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intentLista);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which) {


            }
        });
        builder.show();
    }

    public void abrirCamera() {
        Intent fotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (fotoIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(fotoIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            imgFoto.setImageBitmap(imageBitmap);

            bitmap = (Bitmap)data.getExtras().get("data");
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            //Imagen que se pasa con el contacto
            byteArray = stream.toByteArray();
        }
    }
}