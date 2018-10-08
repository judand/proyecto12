package com.example.prototipo1.proyecto12;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ItemAdaptor extends RecyclerView.Adapter<ItemAdaptor.ItemViewHolder> {

    //creacion de variables a utilizar
    private Context mcontext;
    private ArrayList<ItemEvent> mitemlist;
    public OnItemClickListener mlistener;

    //funcion cuando se hace click
    public interface OnItemClickListener{
        void OnItemClick(int position);
    }

    public void setOnItemClickLister(OnItemClickListener listener){
        mlistener =listener;
    }

    public ItemAdaptor(Context context, ArrayList<ItemEvent> itemlist){
        mcontext=context;
        mitemlist=itemlist;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(mcontext).inflate(R.layout.items_event, viewGroup , false);
        return new ItemViewHolder(v);
    }

    //funcion que  carga los datos del json en el context

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int i) {
        ItemEvent currentitem = mitemlist.get(i);
        String mimage= currentitem.getMimageurl();
        String mtitle=currentitem.getMtitle();
        String mlugar=currentitem.getMlugar();
        int mcostoboleta=currentitem.getMcostoboleta();

        itemViewHolder.mtexttitle.setText(mtitle);
        itemViewHolder.mtextlugar.setText("Lugar: "+mlugar);
        Picasso.get()
                .load(mimage)
                .fit()
                .centerInside()
                .into(itemViewHolder.mimageview);
        if (mcostoboleta!=0){
            itemViewHolder.mcostoboleta.setText("Precio: $"+mcostoboleta);
        }
        else{
            itemViewHolder.mcostoboleta.setText("Entrada Libre");
        }



    }

    @Override
    public int getItemCount() {
        return mitemlist.size();
    }

    //funcion que hace referencia a los alementos para sustituir los valores al cargar
    public class ItemViewHolder extends RecyclerView.ViewHolder{
        public ImageView mimageview;
        public TextView mtexttitle;
        public TextView mtextlugar;
        public TextView mcostoboleta;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            mimageview=itemView.findViewById(R.id.image_view_h);
            mtexttitle=itemView.findViewById(R.id.text_title_h);
            mtextlugar=itemView.findViewById(R.id.text_lugar_h);
            mcostoboleta=itemView.findViewById(R.id.text_costoboleta_h);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mlistener != null){
                        int position =getAdapterPosition();
                        if(position!= RecyclerView.NO_POSITION){
                            mlistener.OnItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
