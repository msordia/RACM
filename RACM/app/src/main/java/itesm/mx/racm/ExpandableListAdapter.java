package itesm.mx.racm;

    import java.util.HashMap;
    import java.util.List;
    import android.content.Context;
    import android.content.Intent;
    import android.graphics.Bitmap;
    import android.graphics.BitmapFactory;
    import android.graphics.Color;
    import android.graphics.Typeface;
    import android.net.Uri;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.BaseExpandableListAdapter;
    import android.widget.ExpandableListView;
    import android.widget.ImageButton;
    import android.widget.ImageView;
    import android.widget.LinearLayout;
    import android.widget.TextView;
    import itesm.mx.racm.datos.Contacto;



public class ExpandableListAdapter extends BaseExpandableListAdapter {
        private Context _context;
        private List<String> _listDataHeader; //titulos de las listas
        private HashMap<String,List<Contacto>> _listDataChild;
        LinearLayout ll;

        public ExpandableListAdapter(Context context, List<String> listDataHeader,
                                     HashMap<String, List<Contacto>> listChildData) {
            this._context = context;
            this._listDataHeader = listDataHeader;
            this._listDataChild = listChildData;
        }

        @Override
        public Object getChild(int groupPosition, int childPosititon) {
            return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                    .get(childPosititon);
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public View getChildView(int groupPosition, final int childPosition,
                                 boolean isLastChild, View convertView, ViewGroup parent) {

            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.row_contact, null);
            }
            final Contacto contact= (Contacto) getChild(groupPosition, childPosition);

            ll= (LinearLayout)  convertView.findViewById(R.id.linear_Row);
            TextView tvNombre = (TextView) convertView.findViewById(R.id.text_nombreCC);
            TextView tvTelefono = (TextView) convertView.findViewById(R.id.text_telefonoCC);
            ImageView ivFoto = (ImageView) convertView.findViewById(R.id.photo_contact);
            ImageButton ibLlamar = (ImageButton) convertView.findViewById(R.id.photo_phone);

            ibLlamar.setFocusable(false);
            ivFoto.setFocusable(false);

            tvNombre.setText(contact.getNombre().toUpperCase());
            tvTelefono.setText(contact.getCelular());

            byte[] image = contact.getFoto();
            if(image.length>1){
                Bitmap bmimage = BitmapFactory.decodeByteArray(image, 0, image.length);
               ivFoto.setImageBitmap(bmimage);
            }else{
               ivFoto.setImageResource(R.drawable.white_user);
            }


            ibLlamar.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Intent callIntent = new Intent(Intent.ACTION_VIEW);
                                                callIntent.setData(Uri.parse("tel:"+contact.getCelular()));
                                                _context.startActivity(callIntent);
                                            }
                                        }
            );

            ivFoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent= new Intent(_context,VerFoto.class);
                    intent.putExtra("foto",contact.getFoto());
                    _context.startActivity(intent);
                }
            });
            cambiarColor(contact);

            return convertView;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                    .size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return this._listDataHeader.get(groupPosition);
        }

        @Override
        public int getGroupCount() {
            return this._listDataHeader.size();
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded,View convertView, ViewGroup parent) {
            String headerTitle = (String) getGroup(groupPosition);

            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.list_group, null);
            }

            TextView lblListHeader = (TextView) convertView.findViewById(R.id.lblListHeader);
            lblListHeader.setTypeface(null, Typeface.BOLD);
            lblListHeader.setText(headerTitle);

            //EXPANDIDAS POR DEFAULT
            ExpandableListView mExpandableListView = (ExpandableListView) parent;
            mExpandableListView.expandGroup(groupPosition);

            return convertView;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }

        public void setTitles(List<String> listaTitulos){
            _listDataHeader=listaTitulos;
        }

        public void setContent(HashMap<String,List<Contacto>> listaContenido){
            _listDataChild= listaContenido;
        }



    private void cambiarColor(Contacto contact) {

        switch (contact.getCategoria()) {
            case 1:
                ll.setBackgroundColor(Color.parseColor("#2244aa"));//Azul #000000
                break;
            case 2:
                ll.setBackgroundColor(Color.parseColor("#c601de"));//Morado #e300ff //#c601de //IGUAL #b006c4
                break;
            case 3:
                ll.setBackgroundColor(Color.parseColor("#e10000"));//Rojo
                break;
            case 4:
                ll.setBackgroundColor(Color.parseColor("#00897b"));//Verde Marino
                break;
            case 5:
                ll.setBackgroundColor(Color.parseColor("#f28500"));//Naranja
                break;
        }
    }
    }
