package itesm.mx.racm;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import static android.R.attr.button;


public class MenuFragment extends Fragment implements View.OnClickListener{

    private static final String DEBUG_TAG = "DEBUG_FRAGMENT_MENU";
    ImageButton ibtnContactos;
    ImageButton ibtnAyuda;
    ImageButton ibtnPerfil;
    public MenuFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(DEBUG_TAG, "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(DEBUG_TAG, "onCreateView");
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        ibtnContactos = (ImageButton) view.findViewById(R.id.image_button_contactos);
        ibtnAyuda = (ImageButton) view.findViewById(R.id.image_button_ayuda);
        ibtnPerfil = (ImageButton) view.findViewById(R.id.image_button_perfil);

        ibtnContactos.setOnClickListener(this);
        //ibtnAyuda.setOnClickListener(this);
        ibtnPerfil.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_button_contactos:
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                break;

            case R.id.image_button_ayuda:
                Intent intent2 = new Intent(getActivity(), CrearContacto.class);
                startActivity(intent2);
                break;

            case R.id.image_button_perfil:
                Intent intent3 = new Intent(getActivity(), ContactoDetalle.class);
                startActivity(intent3);
                break;

        }
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
