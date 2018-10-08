package com.example.prototipo1.proyecto12;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


import static com.example.prototipo1.proyecto12.centro.extra_capacidad;
import static com.example.prototipo1.proyecto12.centro.extra_costoboleta;
import static com.example.prototipo1.proyecto12.centro.extra_descripcion;
import static com.example.prototipo1.proyecto12.centro.extra_fecha;
import static com.example.prototipo1.proyecto12.centro.extra_hora;
import static com.example.prototipo1.proyecto12.centro.extra_lugar;
import static com.example.prototipo1.proyecto12.centro.extra_titulo;
import static com.example.prototipo1.proyecto12.centro.extra_url;

public class DetalleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        //extraccion de las variables antes definidas

        Intent intent =getIntent();
        String imageurl= intent.getStringExtra(extra_url);
        String title=intent.getStringExtra(extra_titulo);
        String lugar= intent.getStringExtra(extra_lugar);
        int capacidad = intent.getIntExtra(extra_capacidad,0);
        String descrpcion = intent.getStringExtra(extra_descripcion);
        String fecha =intent.getStringExtra(extra_fecha);
        String hora =intent.getStringExtra(extra_hora);
        int costoboleta=intent.getIntExtra(extra_costoboleta,0);

        //sustitutucion de cada imageview, textview con los datos del eventos json en cuestion



        ImageView imageview = findViewById(R.id.image_view_deta);
        TextView texttitulo =findViewById(R.id.text_title_deta);
        TextView textlugar = findViewById(R.id.text_lugar_deta);
        TextView textcapacidad = findViewById(R.id.text_capacidad_deta);
        TextView textdescripcion = findViewById(R.id.text_descripcion_deta);
        TextView textfecha=findViewById(R.id.text_fecha_deta);
        TextView texthora= findViewById(R.id.text_hora_deta);
        TextView textcostoboleta=findViewById(R.id.text_costoboleta_deta);


        //funcion para cargar la url de la imagen
        Picasso.get()
                .load(imageurl)
                .fit()
                .centerInside()
                .into(imageview);


        //se sustituyen los valores cargados
        texttitulo.setText(title);
        textlugar.setText("Lugar: "+lugar);
        textcapacidad.setText("Capacidad: "+capacidad);
        textdescripcion.setText(descrpcion);
        textfecha.setText("Fecha:"+fecha);
        texthora.setText("Hora:"+hora);
        if(costoboleta!=0){
            textcostoboleta.setText("Precio: $"+costoboleta);
        }else{
            textcostoboleta.setText("Entrada Libre");
        }



    }
}
