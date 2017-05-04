package itesm.mx.racm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class ContactoDetalle extends AppCompatActivity implements View.OnClickListener {

    ImageButton imageFoto, imageModify, imagePhone, imageIcons;
    MenuFragment fragmentoMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto_detalle);

        fragmentoMenu = (MenuFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_menu);

        imageFoto = (ImageButton) findViewById(R.id.image_fotoPD);
        imageModify = (ImageButton) findViewById(R.id.photo_modify);
        imagePhone = (ImageButton) findViewById(R.id.photo_phone);
        imageIcons = (ImageButton) findViewById(R.id.photo_icons);

        imageFoto.setOnClickListener(this);
        imageModify.setOnClickListener(this);
        imagePhone.setOnClickListener(this);
        imageIcons.setOnClickListener(this);

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
                intent.putExtra("",)
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
