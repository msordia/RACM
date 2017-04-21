package itesm.mx.racm;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

import itesm.mx.racm.datos.Perfil;
import itesm.mx.racm.datos.PerfilOperations;
import itesm.mx.racm.datos.RACM_DBHelper;


public class RegistrarPerfil extends FragmentActivity implements View.OnClickListener {

    private Button btnGuardar, btnDireccion;
    private EditText editNombre, editTelefono, editDireccion;
    private ImageButton btnImagePhoto;
    private SessionManager session;
    private ProgressDialog pDialog;
    private PerfilOperations dao_Perfil;

    MenuFragment fragmentoMenu;

    Bitmap imageBitmap;
    int REQUEST_CODE = 1;
    Bitmap bitmap;
    byte[] byteArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_perfil);

        fragmentoMenu = (MenuFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_menu);

        editNombre = (EditText) findViewById(R.id.edit_nombreRP);
        editDireccion = (EditText) findViewById(R.id.edit_direccionRP);
        editTelefono = (EditText) findViewById(R.id.edit_telefonoRP);

        btnGuardar = (Button) findViewById(R.id.button_guardarRP);
        //btnDireccion = (Button) findViewById(R.id.button_ubicacionRP);
        btnImagePhoto = (ImageButton) findViewById(R.id.image_fotoRP);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        dao_Perfil = new PerfilOperations(this);
        dao_Perfil.open();


        session = new SessionManager(getApplicationContext());

        if(session.isLoggedIn()){
            Intent intent = new Intent(RegistrarPerfil.this, MainActivity.class);
            startActivity(intent);
            finish();
        }


        btnGuardar.setOnClickListener(this);
        btnImagePhoto.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_guardarRP:
                String name = editNombre.getText().toString().trim();
                String phone = editTelefono.getText().toString().trim();
                String adress = editDireccion.getText().toString().trim();

                //Falta la foto
                if(!name.isEmpty() && !adress.isEmpty() && !phone.isEmpty()){
                    login(name, phone, adress);
                }else{
                    Toast.makeText(getApplicationContext(), "Favor de completar la información", Toast.LENGTH_SHORT).show();

                }

                break;

            case  R.id.image_fotoRP:
                //Intent a Media Store
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(intent.resolveActivity(getPackageManager()) != null){
                    startActivityForResult(intent, REQUEST_CODE);
                }
                break;
            /*
            case R.idx.button_ubicacionRP:
                Toast.makeText(this.getApplicationContext(), "Activar GPS", Toast.LENGTH_LONG).show();
                break;
              */
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            btnImagePhoto.setImageBitmap(imageBitmap);


            bitmap = (Bitmap)data.getExtras().get("data");
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            //Imagen que se pasa con el contacto
            byteArray = stream.toByteArray();
        }
    }

    private void login(final String name, final String phone, final String adress){
        String tag_string_req = "req_login";

        pDialog.setMessage("Registrando ...");
        showDialog();

        session.setLogin(true);


        Perfil perfil = new Perfil(name, phone, phone, adress, adress, byteArray);

        long id = dao_Perfil.registrarPerfil(perfil);
        perfil.setId(id);

        hideDialog();


        Toast.makeText(this, "Correctamente Agregado", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        //finish();

    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
