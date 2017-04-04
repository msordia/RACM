package itesm.mx.racm;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import itesm.mx.racm.datos.Contacto;

/**
 * Created by everm on 4/3/2017.
 */

public class ContactoAdapter extends ArrayAdapter<Contacto>{

    ContactoAdapter(Context context, ArrayList<Contacto> contactos) { super(context, 0, contactos); }
        public View getView(int position, View convertView, ViewGroup parent) {

            Contacto contacto = getItem(position);

            if(convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_contact, parent, false);
            }

            TextView tvNombre = (TextView) convertView.findViewById(R.id.text_nombreCC);
            TextView tvTelefono = (TextView) convertView.findViewById(R.id.text_telefonoCC);
            ImageView ivFoto = (ImageView) convertView.findViewById(R.id.photo_contact);

            tvNombre.setText(contacto.getNombre());
            tvTelefono.setText(contacto.getTelefono());

            Bitmap foto = decodeBase64(contacto.getFoto());
            ivFoto.setImageBitmap(foto);

            return convertView;
        }

        public static Bitmap decodeBase64(String input) {
            byte[] decodedByte = Base64.decode(input, 0);
            return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
        }
}
