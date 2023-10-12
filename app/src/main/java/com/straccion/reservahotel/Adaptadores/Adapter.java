package com.straccion.reservahotel.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.straccion.reservahotel.R;
import com.straccion.reservahotel.HorariosMedicos;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private List<HorariosMedicos> horariosMedicos;
    private Context mcontext;
    public Adapter(List<HorariosMedicos> horariosMedicos, Context mcontext) {
        this.horariosMedicos = horariosMedicos;
        this.mcontext = mcontext;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_agendar, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HorariosMedicos horarios = horariosMedicos.get(position);

        holder.txtHora.setText(horarios.getHora());
        holder.txtLugarCita.setText(horarios.getClinica());
    }
    @Override
    public int getItemCount() {
        return horariosMedicos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtLugarCita;
        TextView txtHora;

        public ViewHolder(View view) {
            super(view);
            txtLugarCita = view.findViewById(R.id.txtLugarCita);
            txtHora = view.findViewById(R.id.txtHora);

        }
    }
}