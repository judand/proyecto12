package com.example.prototipo1.proyecto12;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class centro extends AppCompatActivity implements ItemAdaptor.OnItemClickListener{
    //atributos para pasar al detalle del evento
    public static final String extra_url ="imageurl";
    public static final String extra_titulo="titulo";
    public static final String extra_lugar="lugar";
    public static final String extra_capacidad ="capacidad";
    public static final String extra_descripcion="descripcion";
    public static final String extra_fecha="fecha";
    public static final String extra_hora="hora";
    public static final String extra_costoboleta="costoboleta";

    private RecyclerView mreciclerview;
    private ItemAdaptor mitemadaptor;
    private ArrayList<ItemEvent> mitemevent;
    private RequestQueue mrequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_centro);
        //creacion de las variables para cargar

        mreciclerview = findViewById(R.id.recicler_view);
        mreciclerview.setHasFixedSize(true);
        mreciclerview.setLayoutManager(new LinearLayoutManager(this));

        mitemevent =new ArrayList<>();

        mrequest= Volley.newRequestQueue(this);
        parseJson();
    }

    //funcion para leer datos del json

    private void parseJson(){
        //url donde esta subido el json
        String url= "https://api.myjson.com/bins/87lro";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray= response.getJSONArray("eventos");

                    for(int i=0; i<jsonArray.length();i++){
                        JSONObject evento =jsonArray.getJSONObject(i);

                        String titulo=evento.getString("nombre");
                        String lugar =evento.getString("lugar");
                        String imageurl=evento.getString("imagen");
                        int capacidad =evento.getInt("capacidad");
                        String descrpcion =evento.getString("descripcion");
                        String fecha =evento.getString("fecha");
                        String hora= evento.getString("hora");
                        int costoboleta=evento.getInt("costoboleta");

                        //creacion de cada objeto itemevent


                        mitemevent.add(new ItemEvent(titulo,imageurl,lugar,capacidad,descrpcion,fecha,hora,costoboleta));

                    }

                    mitemadaptor =new ItemAdaptor(centro.this,mitemevent);
                    mreciclerview.setAdapter(mitemadaptor);
                    mitemadaptor.setOnItemClickLister(centro.this);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });


        mrequest.add(request);
    }

    @Override
    public void OnItemClick(int position) {
        Intent detalleintent =new Intent(this,DetalleActivity.class);

        //se llenan las variables para llamarlas en la otra clase
        ItemEvent clickitem=mitemevent.get(position);
        detalleintent.putExtra(extra_url, clickitem.getMimageurl());
        detalleintent.putExtra(extra_titulo,clickitem.getMtitle());
        detalleintent.putExtra(extra_lugar,clickitem.getMlugar());
        detalleintent.putExtra(extra_capacidad, clickitem.getMcapacidad());
        detalleintent.putExtra(extra_descripcion,clickitem.getMdescripcion());
        detalleintent.putExtra(extra_fecha,clickitem.getMfecha());
        detalleintent.putExtra(extra_hora,clickitem.getMhora());
        detalleintent.putExtra(extra_costoboleta,clickitem.getMcostoboleta());

        startActivity(detalleintent);
    }
}
