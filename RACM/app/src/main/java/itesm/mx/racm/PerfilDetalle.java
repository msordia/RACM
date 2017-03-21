package itesm.mx.racm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class PerfilDetalle extends AppCompatActivity implements View.OnClickListener{

    ImageView ivFotoPD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_detalle);

        ivFotoPD= (ImageView) findViewById(R.id.image_fotoPD);
        ivFotoPD.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent  intent= new Intent(this,VerFoto.class);
        startActivity(intent);
    }
}
