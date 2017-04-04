package itesm.mx.racm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import itesm.mx.racm.datos.ListaContacto;

public class ListaContactoAdapter extends ArrayAdapter<ListaContacto>{

    ContactoAdapter adapter;

    public ListaContactoAdapter(Context context, ArrayList<ListaContacto> contactos) {
        super(context, 0, contactos);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ListaContacto listaContactos = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.categories, parent, false);
        }

        TextView tvNombre = (TextView) convertView.findViewById(R.id.text_nombre_categoria);
        ListView lvLista= (ListView)  convertView.findViewById(R.id.list_contactos);

        adapter = new ContactoAdapter(getContext(),listaContactos.getLista());
        lvLista.setAdapter(adapter);

        tvNombre.setText(listaContactos.getTitulo());

        return convertView;
    }
}
