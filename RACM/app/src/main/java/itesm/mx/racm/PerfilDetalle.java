package itesm.mx.racm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PerfilDetalle extends AppCompatActivity implements View.OnClickListener {

    TextView tvNombrePD,tvTelefonoPD,tvDireccionPD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_detalle);

        tvNombrePD= (TextView) findViewById(R.id.text_nombrePD);
        tvTelefonoPD= (TextView) findViewById(R.id.text_telefonoPD);
        tvDireccionPD= (TextView) findViewById(R.id.text_direccionPD);
       // Button btnModificarPD= (Button) findViewById(R.id.); //falta boton....

        //btnModificarPD.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

    }
}
