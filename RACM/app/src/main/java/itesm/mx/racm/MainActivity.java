package itesm.mx.racm;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    ImageButton imageSearch, imageContact, imagePhone, imageCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageSearch = (ImageButton) findViewById(R.id.photo_search);
        imageContact = (ImageButton) findViewById(R.id.photo_contact);
        imagePhone = (ImageButton) findViewById(R.id.photo_phone);
        imageCreate = (ImageButton) findViewById(R.id.photo_create);

        imageSearch.setOnClickListener(this);
        imageContact.setOnClickListener(this);
        imagePhone.setOnClickListener(this);
        imageCreate.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.photo_contact:
                Intent intent = new Intent(this, ContactoDetalle.class);
                startActivity(intent);
                break;

            case R.id.photo_create:
                Intent intent2 = new Intent(this, CrearContacto.class);
                startActivity(intent2);
                break;

            case R.id.photo_search:
                Toast.makeText(this.getApplicationContext(), "Función de busqueda, no se encontró al usuario", Toast.LENGTH_LONG).show();
                break;

            case R.id.photo_phone:
                Toast.makeText(this.getApplicationContext(), "Función de llamada al numero: 38499583", Toast.LENGTH_LONG).show();
                break;
        }

    }
}
