package com.aplication.com.aplication1.Adaptador;

import android.content.ClipData;
import android.content.Context;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.aplication.com.aplication1.Models.Prospects;
import com.aplication.com.aplication1.R;

import java.util.ArrayList;
import java.util.List;

public class ListViewAdapter extends RecyclerView.Adapter<ListViewAdapter.ViewHolder> implements View.OnClickListener{


    public View.OnClickListener listener;

    private List<Prospects> prospectos;

    public ListViewAdapter(List<Prospects> prospectos) {
        this.prospectos = prospectos;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        //Esta es la clase que contendrà el ViewHolder que a su vez contendrà un constructor encardado de encontrar los
        //elementos que se van a inflar en nuestra vista por medio del ID, esto se hace una sola vez ya que por eso se
        //utilza el ViewHolder para mejorar el rendimiento de nuestra aplicaciòn.

        private TextView txtName;


        public ViewHolder(View itemView) {
            super(itemView);

            txtName = (TextView)itemView.findViewById(R.id.list_row_title);
        }

        public void bindTitular(Prospects prospects) {
            txtName.setText(prospects.getName().toString());

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row, parent, false);

        itemView.setOnClickListener(this.listener);

        ViewHolder vh = new ViewHolder(itemView);

        return vh;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Prospects item = prospectos.get(position);

        holder.bindTitular(item);
    }

    @Override
    public int getItemCount() {

        return prospectos.size();

    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {

    }

}