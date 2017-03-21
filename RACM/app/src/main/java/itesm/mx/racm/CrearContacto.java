package itesm.mx.racm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class CrearContacto extends AppCompatActivity implements View.OnClickListener{

    Button btnGuardar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_contacto);

        Spinner spinnerCategorias = (Spinner)findViewById(R.id.spinnner_categoriasCC);
        String[] items = new String[]{"Seleccione la categoría","Familia", "Amigos", "Salud", "Proveedores", "Servicios"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spinnerCategorias.setAdapter(adapter);

        btnGuardar = (Button) findViewById(R.id.button_guardarCC);
        btnGuardar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_guardarCC:
                //Intent a Contacto Detalle

                Intent intent = new Intent(this, ContactoDetalle.class);
                startActivity(intent);

                break;

            default:
                break;
        }
    }
}
