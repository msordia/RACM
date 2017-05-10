package itesm.mx.racm;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

import de.hdodenhof.circleimageview.CircleImageView;
import itesm.mx.racm.datos.ContactoOperations;
import itesm.mx.racm.datos.Perfil;
import itesm.mx.racm.datos.PerfilOperations;

import static itesm.mx.racm.R.id.button_guardarEP;

public class EditarPerfil extends AppCompatActivity implements View.OnClickListener{

    MenuFragment fragmentoMenu;

    Button btnGuardar;
//  ImageButton ibFoto;
    CircleImageView ibFoto;
    EditText etNombre;
    EditText etCelular;
    EditText etTelefono;
    EditText etDireccion;

    Bitmap imageBitmap;
    int REQUEST_CODE = 1;
    Bitmap bitmap;
    byte[] byteArray;
    private static final int SELECT_PICTURE = 2;
    private static final int REQUEST_IMAGE_CAPTURE = 1;

    Perfil perfil;
    PerfilOperations dao_Perfil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil);
        fragmentoMenu = (MenuFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_menu);

        ibFoto = (CircleImageView) findViewById(R.id.image_fotoEP);
        ibFoto.setOnClickListener(this);

        etNombre = (EditText) findViewById(R.id.edit_nombreEP);
        etCelular = (EditText) findViewById(R.id.edit_celularEP);
        etTelefono = (EditText) findViewById(R.id.edit_telefonoEP);
        etDireccion = (EditText) findViewById(R.id.edit_direccionEP);

        dao_Perfil = new PerfilOperations(this);
        dao_Perfil.open();

        Bundle bundle= getIntent().getExtras();
        if(bundle!=null) {
            perfil = (Perfil) bundle.getSerializable("perfil");
            if(perfil.getFoto() != null) {
                imageBitmap = BitmapFactory.decodeByteArray(perfil.getFoto(), 0, perfil.getFoto().length);
                ibFoto.setImageBitmap(imageBitmap);
            }
        }

        etNombre.setText(perfil.getNombre());
        etCelular.setText(perfil.getCelular());
        etTelefono.setText(perfil.getTelefonoFIjo());
        etDireccion.setText(perfil.getDireccion());

        btnGuardar = (Button) findViewById(button_guardarEP);
        btnGuardar.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_guardarEP:
                //Intent a Contacto Detalle
                if(etNombre.getText().toString().isEmpty() || etTelefono.getText().toString().isEmpty() || etCelular.getText().toString().isEmpty())  {
                    Toast.makeText(this, "Favor de completar la información", Toast.LENGTH_SHORT).show();
                }else{
                    dao_Perfil.modificarPerfil(modificar());
                    Intent intent = new Intent(this, PerfilDetalle.class);
                    startActivity(intent);
                }
                break;

            case R.id.image_fotoEP:
                //Intent a Cambiar Foto
                abrirCamera();
                break;
            default:
                break;
        }
    }

    public Perfil modificar(){
        perfil.setNombre(etNombre.getText().toString());
        perfil.setCelular(etCelular.getText().toString());
        perfil.setTelefonoFIjo(etTelefono.getText().toString());
        perfil.setDireccion(etDireccion.getText().toString());
        perfil.setFoto(byteArray);
        return perfil;
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
            ibFoto.setImageBitmap(imageBitmap);

            bitmap = (Bitmap)data.getExtras().get("data");
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            //Imagen que se pasa con el contacto
            byteArray = stream.toByteArray();
        }
    }
}
