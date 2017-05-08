package itesm.mx.racm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import itesm.mx.racm.datos.Perfil;

public class EditarPerfil extends AppCompatActivity implements View.OnClickListener{

    MenuFragment fragmentoMenu;
    EditText etNombre;
    EditText etCelular;
    EditText etTelefono;
    EditText etDireccion;

    Perfil perfilPrueba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil);
        fragmentoMenu = (MenuFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_menu);

        etNombre = (EditText) findViewById(R.id.edit_nombreEP);
        etCelular = (EditText) findViewById(R.id.edit_celularEP);
        etTelefono = (EditText) findViewById(R.id.edit_telefonoEP);
        etDireccion = (EditText) findViewById(R.id.edit_direccionEP);

        Bundle bundle= getIntent().getExtras();
        if(bundle!=null) {
            perfilPrueba = (Perfil) bundle.getSerializable("perfil");
        }

        etNombre.setText(perfilPrueba.getNombre());
        etCelular.setText(perfilPrueba.getCelular());
        etTelefono.setText(perfilPrueba.getTelefonoFIjo());
        etDireccion.setText(perfilPrueba.getDireccion());

        Button btnGuardar = (Button) findViewById(R.id.button_guardarEP);
        btnGuardar.setOnClickListener(this);
    }

    public void onClick(View v) {
        //Intent intent = new Intent(this, inicio.class);
        //startActivity(intent);
    }
}
