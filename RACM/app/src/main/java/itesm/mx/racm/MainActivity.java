package itesm.mx.racm;

import android.content.Intent;
import android.media.Image;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    ImageButton imageSearch, imageContact, imagePhone, imageCreate;
    ListView lvLista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvLista = (ListView) findViewById(R.id.listview_with_fab);

        String[] listItwms = new String[]{"Prueba1", "Prueba2", "Prueba3", "Prueba4",
                "Prueba5", "Prueba6", "Prueba7", "Prueba8", "Prueba9",
                "Prueba10"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, listItwms);
        lvLista.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floating_action_button_fab_with_listview);
        fab.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, CrearContacto.class);
        startActivity(intent);
    }
}
