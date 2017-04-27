package itesm.mx.racm;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import itesm.mx.racm.datos.Contacto;
import itesm.mx.racm.datos.ContactoOperations;

public class ContactoDetalle extends AppCompatActivity implements View.OnClickListener {

    ImageButton imageFoto, imageModify, imagePhone, imageIcons;
    MenuFragment fragmentoMenu;
    long idContacto;
    ContactoOperations dao;
    TextView tvNombre, tvTelefono;
    Bitmap imageBitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto_detalle);

        fragmentoMenu = (MenuFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_menu);

        tvNombre = (TextView) findViewById(R.id.text_nombreCD);
        tvTelefono = (TextView) findViewById(R.id.text_telefonoCD);
        imageFoto = (ImageButton) findViewById(R.id.image_fotoPD);
        imageModify = (ImageButton) findViewById(R.id.photo_modify);
        imagePhone = (ImageButton) findViewById(R.id.photo_phone);
        imageIcons = (ImageButton) findViewById(R.id.photo_icons);

        imageFoto.setOnClickListener(this);
        imageModify.setOnClickListener(this);
        imagePhone.setOnClickListener(this);
        imageIcons.setOnClickListener(this);

        //Get intent extras "idContacto"

        dao = new ContactoOperations(this);
        dao.open();

        Bundle bundle= getIntent().getExtras();
        if(bundle!=null){
           idContacto = bundle.getLong("idContacto");
            Log.d("Este es el id", String.valueOf(idContacto));
            Contacto contactoPrueba;
            contactoPrueba = dao.findContacto(idContacto);
            Log.d("Nombre", contactoPrueba.getNombre());
            //Cambiar imageFoto,tvNombre y tvTelefono
            imageBitmap = BitmapFactory.decodeByteArray(contactoPrueba.getFoto(), 0, contactoPrueba.getFoto().length);
            imageFoto.setImageBitmap(imageBitmap);
            tvNombre.setText(contactoPrueba.getNombre());
            tvTelefono.setText(contactoPrueba.getTelefono());
        }

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

            case R.id.photo_icons:
                Toast.makeText(this.getApplicationContext(), "Función de envio de mensaje SMS al numero: 38499583", Toast.LENGTH_LONG).show();
                break;
        }

    }
}
