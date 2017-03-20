package itesm.mx.racm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class CrearContacto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_contacto);

        Spinner spinnerCategorias = (Spinner)findViewById(R.id.spinnner_categoriasCC);
        String[] items = new String[]{"Seleccione la categoría","Familia", "Amigos", "Salud", "Proveedores", "Servicios"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spinnerCategorias.setAdapter(adapter);
    }
}
