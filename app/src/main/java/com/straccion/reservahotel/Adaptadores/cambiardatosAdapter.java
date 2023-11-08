package com.straccion.reservahotel.Adaptadores;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.straccion.reservahotel.AdminBD;
import com.straccion.reservahotel.Contenedor;
import com.straccion.reservahotel.R;
import com.straccion.reservahotel.objetos.HorariosMedicos;
import com.straccion.reservahotel.objetos.MostarReservas;

import java.util.ArrayList;
import java.util.List;

public class cambiardatosAdapter extends RecyclerView.Adapter<cambiardatosAdapter.ViewHolder> {
    private List<HorariosMedicos> horariosMedicos;
    private Context mcontext;
    int idReserva;

    int idUser=0;
    AdminBD adminBD;
    public cambiardatosAdapter(List<HorariosMedicos> mostarReservas, Context mcontext, int idReserva, int idUser) {
        this.horariosMedicos = mostarReservas;
        this.mcontext = mcontext;
        this.idReserva = idReserva;
        this.idUser = idUser;
    }

    // Create new views (invoked by the layout manager)
    public cambiardatosAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_agendar, parent, false);
        return new cambiardatosAdapter.ViewHolder(view);

    }
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull cambiardatosAdapter.ViewHolder holder, int position) {
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
                builder.setTitle("Datos de la nueva cita:")
                        .setMessage("Hora de atencion: " + horaCita +
                                "\nFecha: " + fecha +
                                "\nLugar de la cita: " + lugarCita +
                                "\nMedico: " + nombreMedico +
                                "\n"+
                                "\n¿Esta seguro que desea modificar la cita actual?")
                        .setCancelable(false)
                        .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                int idMedico = adminBD.saberIDMedico(nombreMedico);
                                int respuesta = adminBD.modificarDatosReserva(idReserva, idMedico, horaCita, fecha, lugarCita);
                                if (respuesta != 0){
                                    Toast.makeText(mcontext, "Cita modificada con exito", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(mcontext, Contenedor.class);
                                    intent.putExtra("idUser", idUser );
                                    intent.putExtra("abrir_Contenedor", 6 );
                                    mcontext.startActivity(intent);
                                }else {
                                    Toast.makeText(mcontext, "No se pudo modificar la cita", Toast.LENGTH_SHORT).show();
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


        View mView;
        TextView txtLugarCita;
        TextView txtHora;

        public ViewHolder(View view) {
            super(view);
            txtLugarCita = view.findViewById(R.id.txtLugarCita);
            txtHora = view.findViewById(R.id.txtHora);
            mView = view;

            adminBD = new AdminBD(mcontext);

        }
    }
}
