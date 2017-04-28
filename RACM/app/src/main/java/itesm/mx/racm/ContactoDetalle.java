package itesm.mx.racm;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
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



    MenuFragment fragmentoMenu;
    long idContacto;
    ContactoOperations dao;
    TextView tvNombre, tvTelefono;
    Bitmap imageBitmap;


    long id;
    String telefono;
    String texto = "";
    Contacto contacto;
    String nombre;
    ContactoOperations dao_Contactos;
    ArrayList<Contacto> contactosCompletos;
    Contacto contactoPrueba;

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

        //Get intent extras "idContacto"

        dao = new ContactoOperations(this);
        dao.open();

        Bundle bundle= getIntent().getExtras();
        if(bundle!=null){
           idContacto = bundle.getLong("idContacto");
            Log.d("Este es el id", String.valueOf(idContacto));

            contactoPrueba = dao.findContacto(idContacto);
            Log.d("Nombre", contactoPrueba.getNombre());
            //Cambiar imageFoto,tvNombre y tvTelefono
            imageBitmap = BitmapFactory.decodeByteArray(contactoPrueba.getFoto(), 0, contactoPrueba.getFoto().length);
            ibFoto.setImageBitmap(imageBitmap);
            tvNombre.setText(contactoPrueba.getNombre());
            tvTelefono.setText(contactoPrueba.getCelular());
            telefono = contactoPrueba.getCelular();
        }

    }
    private void showLocationDialog(final String texto) {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this, R.style.alertDialog);
        builder.setTitle("SMS");
        builder.setMessage("¿Está seguro?");
        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to execute after dialog
                // smsManager.sendTextMessage(phoneNo, null, sms, null, null);
                String sms = texto;

                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(telefono, null, sms, null, null);
                Toast.makeText(getApplicationContext(), "Sí", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to execute after dialog
                Toast.makeText(getApplicationContext(), "No", Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
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
                Toast.makeText(ContactoDetalle.this, "Hungry", Toast.LENGTH_SHORT).show();
                texto = "Tengo hambre";

                showLocationDialog(texto);


                break;

            case R.id.image_button_enfermoCD:
                Toast.makeText(this.getApplicationContext(), "enfermo", Toast.LENGTH_LONG).show();
                texto = "Estoy enfermo";

                showLocationDialog(texto);
                break;

            case R.id.image_button_soloCD:
                Toast.makeText(this.getApplicationContext(), "solo", Toast.LENGTH_LONG).show();
                texto = "Me siento solo";

                showLocationDialog(texto);
                break;

            case R.id.image_button_tristeCD:
                Toast.makeText(this.getApplicationContext(), "triste", Toast.LENGTH_LONG).show();
                texto = "Estoy triste";

                showLocationDialog(texto);
                break;
        }

    }
}
