package itesm.mx.racm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;


public class RegistrarPerfil extends AppCompatActivity implements View.OnClickListener {

    Button btnGuardar, btnDireccion;
    ImageButton btnImagePhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_perfil);
        Button btnGuardar = (Button) findViewById(R.id.button_guardarRP);
        btnGuardar.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_guardarRP:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
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
}
