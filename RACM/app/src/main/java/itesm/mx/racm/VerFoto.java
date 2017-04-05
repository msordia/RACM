package itesm.mx.racm;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class VerFoto extends AppCompatActivity {

    ImageView ivFoto;
    Bitmap imageBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_foto);

        ivFoto= (ImageView) findViewById(R.id.image_fotoVF);

                Bundle bundle= getIntent().getExtras();
        if(bundle!=null){
            imageBitmap = BitmapFactory.decodeByteArray(bundle.getByteArray("foto"), 0, bundle.getByteArray("foto").length);
            ivFoto.setImageBitmap(imageBitmap);
        }
    }



}
