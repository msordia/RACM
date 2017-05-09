package itesm.mx.racm;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import itesm.mx.racm.datos.Contacto;
import itesm.mx.racm.datos.ContactoOperations;
import itesm.mx.racm.datos.Perfil;
import itesm.mx.racm.datos.PerfilOperations;

import static android.R.attr.button;


public class MenuFragment extends Fragment implements View.OnClickListener {

    private static final String DEBUG_TAG = "DEBUG_FRAGMENT_MENU";
    ImageButton ibtnContactos;
    ImageButton ibtnAyuda;
    ImageButton ibtnCasa;
    ImageButton ibtnPerfil;
    ContactoOperations dao_Contactos;
    PerfilOperations dao_Perfil;
    ArrayList<Contacto> contactos;
    List<Contacto> listaContactos = new ArrayList<Contacto>();
    Perfil perfil;
    public MenuFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(DEBUG_TAG, "onCreate");

        dao_Contactos = new ContactoOperations(this.getContext());
        dao_Contactos.open();

        dao_Perfil = new PerfilOperations(this.getContext());
        dao_Perfil.open();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(DEBUG_TAG, "onCreateView");
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        ibtnContactos = (ImageButton) view.findViewById(R.id.image_button_contactos);
        ibtnAyuda = (ImageButton) view.findViewById(R.id.image_button_ayuda);
        ibtnPerfil = (ImageButton) view.findViewById(R.id.image_button_perfil);
        ibtnCasa = (ImageButton) view.findViewById(R.id.image_button_casa);

        ibtnContactos.setOnClickListener(this);
        ibtnAyuda.setOnClickListener(this);
        ibtnCasa.setOnClickListener(this);
        ibtnPerfil.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.image_button_contactos:
                intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                break;

            case R.id.image_button_ayuda:
                contactos = dao_Contactos.obtenerContactosEmergencia();
                showLocationDialog(contactos);
                break;

            case R.id.image_button_perfil:
                intent = new Intent(getActivity(), PerfilDetalle.class);
                startActivity(intent);
                break;

            case R.id.image_button_casa:
                intent = new Intent(getActivity(), NavegarCasaActivity.class);
                startActivity(intent);
                break;

        }
    }

    private void showLocationDialog(final ArrayList<Contacto> lista) {

        //perfil = dao_Perfil.findPerfil();
        Log.d("Este es el perfil", dao_Perfil.findPerfil().getNombre());
        AlertDialog.Builder builder =
                new AlertDialog.Builder(getContext(), R.style.alertDialog);
        builder.setTitle("Se enviará un mensaje a los contactos de emergencia");
        builder.setMessage("¿Está seguro?");
        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // smsManager.sendTextMessage(phoneNo, null, sms, null, null);
                for (int i = 0; i < lista.size(); i++) {
                    Log.d("Celular", lista.get(i).getCelular());
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(lista.get(i).getCelular(), null, "¡Necesito Ayuda!", null, null);
                }


            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to execute after dialog
                //Toast.makeText(getContext(), "No", Toast.LENGTH_SHORT).show();
            }
        });

        builder.show();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(DEBUG_TAG, "onAttach");
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(DEBUG_TAG, "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(DEBUG_TAG, "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(DEBUG_TAG, "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(DEBUG_TAG, "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(DEBUG_TAG, "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(DEBUG_TAG, "onDestroy");
    }
}
