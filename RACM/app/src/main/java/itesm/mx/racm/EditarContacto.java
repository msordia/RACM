package itesm.mx.racm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class EditarContacto extends AppCompatActivity implements View.OnClickListener {

    Button btnGuardar;
    ImageView imgFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_contacto);

        btnGuardar = (Button) findViewById(R.id.button_guardarEC);
        btnGuardar.setOnClickListener(this);

        imgFoto = (ImageView) findViewById(R.id.image_fotoEC);
        imgFoto.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_guardarEC:
                //Intent a Contacto Detalle

                Intent intent = new Intent(this, ContactoDetalle.class);
                startActivity(intent);

                break;

            case R.id.image_fotoEC:
                //Intent a Cambiar Foto

                Intent intentFoto = new Intent(this, CambiarFoto.class);
                startActivity(intentFoto);

                break;

            default:
                break;
        }
    }
}
