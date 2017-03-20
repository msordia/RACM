package itesm.mx.racm;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

public class CambiarFoto extends AppCompatActivity implements View.OnClickListener {

    private static final int SELECT_PICTURE = 2;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageView ivFotoCF;
    Bitmap imageBitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar_foto);

        ivFotoCF= (ImageView) findViewById(R.id.image_fotoCF);
        Button btnGuardar= (Button) findViewById(R.id.button_guardarCF);
        Button btnCambiarFoto= (Button) findViewById(R.id.button_cambiarFotoCF);

        btnGuardar.setOnClickListener(this);
        btnCambiarFoto.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_guardarCF:
                Toast.makeText(getApplicationContext(), "Guardado con éxito", Toast.LENGTH_LONG).show();
                finish();
                break;
            case R.id.button_cambiarFotoCF:
                mostrarOpciones();
                break;
        }
    }

    public void mostrarOpciones(){
        final CharSequence[] option = {"Tomar fotografía", "Elegir de galería", "Cancelar"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(CambiarFoto.this);
        builder.setTitle("Seleccione una opción");
        builder.setItems(option, new DialogInterface.OnClickListener() {

            @Override

            public void onClick(DialogInterface dialog, int which) {
                if(option[which] == "Tomar fotografía"){
                    abrirCamera();
                }else if(option[which] == "Elegir de galería"){
                    cargarGaleria();
                }else {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    public void abrirCamera() {
        Intent fotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (fotoIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(fotoIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    public void cargarGaleria(){
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent.createChooser(intent, "Selecciona"), SELECT_PICTURE);
    }

   protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if((requestCode == REQUEST_IMAGE_CAPTURE) && resultCode == RESULT_OK){
            Bundle extras= data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            ivFotoCF.setImageBitmap(imageBitmap);
        }
       else{
            Uri path = data.getData();
            ivFotoCF.setImageURI(path);
        }
    }


}
