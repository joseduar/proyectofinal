package net.unadeca.ana.myrest.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;


import net.unadeca.ana.myrest.R;
import net.unadeca.ana.myrest.subclases.RunImage;
import net.unadeca.ana.myrest.util.Functions;

/**
 * Created by ANA on 13/04/2018.
 */

public class ImagenGrande extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imagengrande);

        final ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        ImageView imageView = findViewById(R.id.image);
        TextView textView = findViewById(R.id.descripcion);

        RunImage image = new RunImage();
        if(!getIntent().getExtras().isEmpty()) {
            image.name = getIntent().getStringExtra("name");
            image.author = getIntent().getStringExtra("author");
            image.url = getIntent().getStringExtra("url");
           // image.precio = getIntent().getStringExtra("precio");

            Functions.loadImage(image.url, imageView, this);
            textView.setText("Autor : "+ image.author +"\n" + "Nombre: "+ image.name  );
        }else{
            Functions.loadImage( imageView, this);
            textView.setText("No se envio ninguna información" );
        }


    }
}


