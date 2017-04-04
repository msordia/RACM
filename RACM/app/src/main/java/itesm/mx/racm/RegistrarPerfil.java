package itesm.mx.racm;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


public class RegistrarPerfil extends AppCompatActivity implements View.OnClickListener {

    private Button btnGuardar, btnDireccion;
    private EditText editNombre, editTelefono, editDireccion;
    private ImageButton btnImagePhoto;
    private SessionManager session;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_perfil);


        editNombre = (EditText) findViewById(R.id.edit_nombreRP);
        editDireccion = (EditText) findViewById(R.id.edit_direccionRP);
        editTelefono = (EditText) findViewById(R.id.edit_telefonoRP);

        btnGuardar = (Button) findViewById(R.id.button_guardarRP);
        btnDireccion = (Button) findViewById(R.id.button_ubicacionRP);
        btnImagePhoto = (ImageButton) findViewById(R.id.image_fotoRP);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

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

                if(!name.isEmpty() && !adress.isEmpty() && !phone.isEmpty()){
                    login(name, phone, adress);
                }

                break;

            case  R.id.image_fotoRP:
                Intent intent2 = new Intent(this, CambiarFoto.class);
                startActivity(intent2);
                break;

            case R.id.button_ubicacionRP:
                Toast.makeText(this.getApplicationContext(), "Activar GPS", Toast.LENGTH_LONG).show();
                break;
        }


    }

    private void login(final String name, final String phone, final String adress){
        String tag_string_req = "req_login";

        pDialog.setMessage("Registrando in ...");
        showDialog();




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
