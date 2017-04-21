package itesm.mx.racm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ImageView;
public class PerfilDetalle extends AppCompatActivity implements View.OnClickListener {

    ImageView ivFotoPD;
    MenuFragment fragmentoMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_detalle);

        fragmentoMenu = (MenuFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_menu);

        ivFotoPD= (ImageView) findViewById(R.id.image_fotoPD);
        ivFotoPD.setOnClickListener(this);
        ImageButton ibModificarPD= (ImageButton) findViewById(R.id.image_modificarPD);
        ibModificarPD.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.image_modificarPD:
                Intent  intentModify= new Intent(this,EditarPerfil.class);
                startActivity(intentModify);
                break;

            case R.id.image_fotoPD:
                Intent  intentFoto= new Intent(this,VerFoto.class);
                startActivity(intentFoto);
                break;
        }
    }
}
