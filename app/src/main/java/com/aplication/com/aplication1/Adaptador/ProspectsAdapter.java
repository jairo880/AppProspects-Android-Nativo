package com.aplication.com.aplication1.Adaptador;

import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aplication.com.aplication1.Models.Prospects;
import com.aplication.com.aplication1.R;

import java.util.List;

public class ProspectsAdapter extends RecyclerView.Adapter<ProspectsAdapter.ViewHolder> implements View.OnClickListener{


    public View.OnClickListener listener;
    private List<Prospects> prospectos;

    public ProspectsAdapter(List<Prospects> prospectos) {
        this.prospectos = prospectos;
    }


    public  class ViewHolder extends RecyclerView.ViewHolder {

        //Esta es la clase que contendrà el ViewHolder que a su vez contendrà un constructor encardado de encontrar los
        //elementos que se van a inflar en nuestra vista por medio del ID, esto se hace una sola vez ya que por eso se
        //utilza el ViewHolder para mejorar el rendimiento de nuestra aplicaciòn.

        private TextView txtName;
        private TextView txtStatus;
        private TextView txtTelephone;
        private TextView txtIdentification;
        private ImageView colorBorder;

        public ViewHolder(View itemView) {
            super(itemView);

            txtName = (TextView)itemView.findViewById(R.id.txtName);
            txtStatus = (TextView) itemView.findViewById(R.id.txtStatus);
            colorBorder = (ImageView) itemView.findViewById(R.id.colorBorder);
            txtTelephone = (TextView) itemView.findViewById(R.id.txtTelephone);
            txtIdentification = (TextView) itemView.findViewById(R.id.txtidentification);

        }

        public void bindTitular(Prospects prospects) {

            inflCard(prospects);

        }

        private void inflCard(Prospects prospects) {

            txtName.setText(prospects.getName().toUpperCase()+" "+prospects.getSurname().toUpperCase());
            txtTelephone.setText(prospects.getTelephone());
            txtIdentification.setText(prospects.getId());

            stateAdvisor(prospects.getStatusCd());

        }

        private void stateAdvisor(Integer statusCd) {

            switch (statusCd){
                case 0 :
                    txtStatus.setText(R.string.pending);
                    txtStatus.setTextColor(Color.BLACK);
                    colorBorder.setBackgroundColor(Color.GRAY);
                    txtName.setPaintFlags(0);
                    break;
                case 1 :
                    txtStatus.setText(R.string.aproved);
                    colorBorder.setBackgroundColor(Color.GRAY);
                    txtStatus.setTextColor(Color.BLACK);
                    txtName.setPaintFlags(0);
                    break;
                case 2 :
                    txtStatus.setText(R.string.acepted);
                    colorBorder.setBackgroundColor(Color.GRAY);
                    txtStatus.setTextColor(Color.BLACK);
                    txtName.setPaintFlags(0);
                    break;
                case 3 :
                    txtStatus.setText(R.string.rejected);
                    colorBorder.setBackgroundColor(Color.RED);
                    txtStatus.setTextColor(Color.RED);
                    txtName.setPaintFlags(txtName.getPaintFlags() |   Paint.STRIKE_THRU_TEXT_FLAG);
                    break;
                default :
                    txtStatus.setText(R.string.inhabilit);
                    colorBorder.setBackgroundColor(Color.YELLOW);
                    txtStatus.setTextColor(Color.RED);
                    txtName.setPaintFlags(txtName.getPaintFlags() |   Paint.STRIKE_THRU_TEXT_FLAG);
            }
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_prospects, parent, false);

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