package com.rdc.bdsqliterecyclerview.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.rdc.bdsqliterecyclerview.R;
import com.rdc.bdsqliterecyclerview.entidades.Mascota;

import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder>{

    private ArrayList<Mascota> mascotas;


    public MyRecyclerViewAdapter(ArrayList<Mascota> mascotas) {
        this.mascotas = mascotas;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView id,dueño,nombre,raza; //TextView del archivo item_mascota

        public ViewHolder(View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.tvid);
            dueño = itemView.findViewById(R.id.tvdueño);
            nombre = itemView.findViewById(R.id.tvnombre);
            raza = itemView.findViewById(R.id.tvraza);
        }
    }


    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mascota, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {

        holder.id.setText(mascotas.get(position).getId().toString());
        holder.dueño.setText(mascotas.get(position).getDueño());
        holder.nombre.setText(mascotas.get(position).getNombre());
        holder.raza.setText(mascotas.get(position).getRaza());
    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }
}