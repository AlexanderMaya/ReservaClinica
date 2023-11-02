package com.straccion.reservahotel.Adaptadores;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.straccion.reservahotel.AdminBD;
import com.straccion.reservahotel.R;
import com.straccion.reservahotel.objetos.HorariosMedicos;
import com.straccion.reservahotel.objetos.MostarReservas;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private List<HorariosMedicos> horariosMedicos;
    private Context mcontext;
    AdminBD adminBD;
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

        String nombreMedico = horarios.getMedico();
        String horaCita = horarios.getHora();
        String lugarCita = horarios.getClinica();
        String fecha = horarios.getFecha();

        //datos de las reservas
        List<MostarReservas> datos = new ArrayList<>();
        datos = adminBD.validarReservasAMostrar();
        boolean encontrado = false;
        //***************************************************
        //busqueda 1    HORA

        holder.txtHora.setText(horarios.getHora());
        holder.txtLugarCita.setText(horarios.getClinica());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Confirmar Cita")
                        .setMessage("Hora de atencion: " + horaCita +
                                "\nFecha: " + fecha +
                                "\nLugar de la cita: " + lugarCita +
                                "\nMedico: " + nombreMedico)
                        .setCancelable(false)
                        .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                int idMedico = adminBD.saberIDMedico(nombreMedico);
                                int respuesta = adminBD.llenarHorarios(idMedico, horaCita, fecha, lugarCita);
                                if (respuesta != 0){
                                    Toast.makeText(mcontext, "Cita reservada con exito", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(mcontext, "No se pudo reservar la cita", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog mensaje = builder.create();
                mensaje.show();
            }
        });
    }
    @Override
    public int getItemCount() {
        return horariosMedicos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtLugarCita;
        TextView txtHora;
        View mView;

        public ViewHolder(View view) {
            super(view);
            txtLugarCita = view.findViewById(R.id.txtLugarCita);
            txtHora = view.findViewById(R.id.txtHora);
            mView = view;

            adminBD = new AdminBD(mcontext);

        }
    }
}