package itesm.mx.racm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import itesm.mx.racm.datos.Contacto;
import itesm.mx.racm.datos.ContactoOperations;

public class ContactoDetalle extends AppCompatActivity implements View.OnClickListener {

    ImageButton ibFoto;
    ImageButton ibModify;
    ImageButton ibPhone;
    ImageButton ibHambre;
    ImageButton ibEnfermo;
    ImageButton ibSolo;
    ImageButton ibTriste;

    TextView tvNombre;
    TextView tvTelefono;

    MenuFragment fragmentoMenu;

    long id;
    String telefono;
    Contacto contacto;
    String nombre;
    ContactoOperations dao_Contactos;
    ArrayList<Contacto> contactosCompletos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto_detalle);

        fragmentoMenu = (MenuFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_menu);

        ibFoto = (ImageButton) findViewById(R.id.image_fotoPD);
        ibModify = (ImageButton) findViewById(R.id.photo_modify);
        ibPhone = (ImageButton) findViewById(R.id.photo_phone);
        ibHambre = (ImageButton) findViewById(R.id.image_button_hambreCD);
        ibEnfermo = (ImageButton) findViewById(R.id.image_button_enfermoCD);
        ibSolo = (ImageButton) findViewById(R.id.image_button_soloCD);
        ibTriste = (ImageButton) findViewById(R.id.image_button_tristeCD);

        tvNombre = (TextView) findViewById(R.id.text_nombreCD);
        tvTelefono = (TextView) findViewById(R.id.text_telefonoCD);

        dao_Contactos = new ContactoOperations(this);
        dao_Contactos.open();

        nombre = "MARIA";
        contacto = dao_Contactos.obtenerContactoDetalle(nombre);
        /*
        // SI SE QUIERE PROBAR LA QUERY: CAMBIAR LA VARIABLE NOMBRE A UN NOMBRE QUE EXISTA
        tvNombre.setText(contacto.getNombre());
        tvTelefono.setText(contacto.getTelefono());
        */
        ibFoto.setOnClickListener(this);
        ibModify.setOnClickListener(this);
        ibPhone.setOnClickListener(this);
        ibHambre.setOnClickListener(this);
        ibEnfermo.setOnClickListener(this);
        ibSolo.setOnClickListener(this);
        ibTriste.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.image_fotoPD:
                Intent intent = new Intent(this, VerFoto.class);
                startActivity(intent);
                break;

            case  R.id.photo_modify:
                Intent intent2 = new Intent(this, EditarContacto.class);
                startActivity(intent2);
                break;

            case R.id.photo_phone:
                Toast.makeText(this.getApplicationContext(), "Función de llamada al numero: 38499583", Toast.LENGTH_LONG).show();
                break;

            case R.id.image_button_hambreCD:
                Toast.makeText(this.getApplicationContext(), "hungry" , Toast.LENGTH_LONG).show();
                break;

            case R.id.image_button_enfermoCD:
                Toast.makeText(this.getApplicationContext(), "enfermo", Toast.LENGTH_LONG).show();
                break;

            case R.id.image_button_soloCD:
                Toast.makeText(this.getApplicationContext(), "solo", Toast.LENGTH_LONG).show();
                break;

            case R.id.image_button_tristeCD:
                Toast.makeText(this.getApplicationContext(), "triste", Toast.LENGTH_LONG).show();
                break;
        }

    }
}
