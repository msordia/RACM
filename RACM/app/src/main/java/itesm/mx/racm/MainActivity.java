package itesm.mx.racm;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    ImageButton imageSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_manual);

        imageSearch = (ImageButton) findViewById(R.id.photo_search);

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

            case R.id.photo_search:
                Toast.makeText(this.getApplicationContext(), "Función de busqueda, no se encontró al usuario", Toast.LENGTH_LONG).show();
                break;

            case R.id.photo_icons:
                Toast.makeText(this.getApplicationContext(), "Función de envio de mensaje SMS al numero: 38499583", Toast.LENGTH_LONG).show();
                break;
        }

    }
}
