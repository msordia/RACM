package itesm.mx.racm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EditarPerfil extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil);

        Button btnGuardar = (Button) findViewById(R.id.button_guardarEP);
        btnGuardar.setOnClickListener(this);
    }

    public void onClick(View v) {
        //Intent intent = new Intent(this, inicio.class);
        //startActivity(intent);
    }
}
