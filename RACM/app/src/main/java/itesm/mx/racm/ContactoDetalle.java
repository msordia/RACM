package itesm.mx.racm;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AlertDialog;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


import de.hdodenhof.circleimageview.CircleImageView;
import itesm.mx.racm.datos.Contacto;
import itesm.mx.racm.datos.ContactoOperations;

public class ContactoDetalle extends AppCompatActivity implements View.OnClickListener {

//  ImageButton ibFoto;
    CircleImageView ibFoto;
    ImageButton ibModify;
    ImageButton ibPhone;
    ImageButton ibHambre;
    ImageButton ibEnfermo;
    ImageButton ibSolo;
    ImageButton ibTriste;



    MenuFragment fragmentoMenu;

    long idContacto;
    TextView tvNombre;
    TextView tvTelefono;
    TextView tvCelular;
    TextView tvEmergencia;
    TextView tvFavorito;
    Bitmap imageBitmap;

    long id;
    String telefono;
    String texto = "";

    ContactoOperations dao_Contactos;
    Contacto contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto_detalle);

        fragmentoMenu = (MenuFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_menu);

        ibFoto = (CircleImageView) findViewById(R.id.image_fotoPD);
        ibFoto.setOnClickListener(this);

        ibModify = (ImageButton) findViewById(R.id.photo_modify);
        ibPhone = (ImageButton) findViewById(R.id.photo_phone);
        ibHambre = (ImageButton) findViewById(R.id.image_button_hambreCD);
        ibEnfermo = (ImageButton) findViewById(R.id.image_button_enfermoCD);
        ibSolo = (ImageButton) findViewById(R.id.image_button_soloCD);
        ibTriste = (ImageButton) findViewById(R.id.image_button_tristeCD);

        tvNombre = (TextView) findViewById(R.id.text_nombreCD);
        tvTelefono = (TextView) findViewById(R.id.text_telefonoCD);
        tvCelular = (TextView) findViewById(R.id.text_celularCD);
        tvEmergencia = (TextView) findViewById(R.id.text_emergenciaCD);
        tvFavorito = (TextView) findViewById(R.id.text_favoritoCD);

        /*
        // SI SE QUIERE PROBAR LA QUERY: CAMBIAR LA VARIABLE NOMBRE A UN NOMBRE QUE EXISTA
        tvNombre.setText(contacto.getNombre());
        tvTelefono.setText(contacto.getTelefono());
        */

        ibModify.setOnClickListener(this);
        ibPhone.setOnClickListener(this);
        ibHambre.setOnClickListener(this);
        ibEnfermo.setOnClickListener(this);
        ibSolo.setOnClickListener(this);
        ibTriste.setOnClickListener(this);

        //Get intent extras "idContacto"

        dao_Contactos = new ContactoOperations(this);
        dao_Contactos.open();

        Bundle bundle= getIntent().getExtras();
        if(bundle!=null){
           idContacto = bundle.getLong("idContacto");
            Log.d("Este es el id", String.valueOf(idContacto));

            contact = dao_Contactos.findContacto(idContacto);
            Log.d("Nombre", contact.getNombre());
            LinearLayout ll;
            ll = (LinearLayout) findViewById(R.id.linearLayoutCD);
            switch (contact.getCategoria()) {
                case 1:
                    ll.setBackgroundColor(Color.parseColor("#2244aa"));//Azul #000000
                    break;
                case 2:
                    ll.setBackgroundColor(Color.parseColor("#c601de"));//Morado #e300ff //#c601de //IGUAL #b006c4
                    break;
                case 3:
                    ll.setBackgroundColor(Color.parseColor("#e10000"));//Rojo
                    break;
                case 4:
                    ll.setBackgroundColor(Color.parseColor("#00897b"));//Verde Marino
                    break;
                case 5:
                    ll.setBackgroundColor(Color.parseColor("#f28500"));//Naranja
                    break;
            }
            //Cambiar imageFoto,tvNombre y tvTelefono


            if(contact.getFoto().length != 1) {
                imageBitmap = BitmapFactory.decodeByteArray(contact.getFoto(), 0, contact.getFoto().length);
                ibFoto.setImageBitmap(imageBitmap);
            }

            tvNombre.setText(contact.getNombre());
            tvCelular.setText("Cel. " + contact.getCelular());
            tvTelefono.setText("Tel. " + contact.getTelefono());

            if(contact.getEmergencia() == 1) {
                tvEmergencia.setVisibility(View.VISIBLE);
            }
            if(contact.getFavorito() == 1) {
                tvFavorito.setVisibility(View.VISIBLE);
            }

            telefono = contact.getCelular();
        }
        // Marca al numero de CELULAR
        ibPhone.setFocusable(false);
        ibPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_VIEW);
                callIntent.setData(Uri.parse("tel:"+contact.getCelular()));
                startActivity(callIntent);
            }
        });

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
                Toast.makeText(getApplicationContext(), "Mensaje enviado", Toast.LENGTH_SHORT).show();


            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to execute after dialog

            }
        });
        builder.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.image_fotoPD:
                if(contact.getFoto().length != 1) {
                    Intent intent = new Intent(this, VerFoto.class);
                    intent.putExtra("foto",contact.getFoto());
                    startActivity(intent);
                }
                break;

            case  R.id.photo_modify:
                Intent intent2 = new Intent(this, EditarContacto.class);
                intent2.putExtra("contacto",contact);
                startActivity(intent2);
                break;

            case R.id.image_button_hambreCD:
                texto = "Tengo hambre";
                showLocationDialog(texto);
                break;

            case R.id.image_button_enfermoCD:
                texto = "Estoy enfermo";
                showLocationDialog(texto);
                break;

            case R.id.image_button_soloCD:
                texto = "Me siento solo";
                showLocationDialog(texto);
                break;

            case R.id.image_button_tristeCD:
                texto = "Estoy triste";
                showLocationDialog(texto);
                break;
        }

    }
}
