package com.straccion.reservahotel.Adaptadores;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

public class reservasAdapter extends RecyclerView.Adapter<reservasAdapter.ViewHolder> {
    private List<MostarReservas> MostarReservas;
    private Context mcontext;
    AdminBD adminBD;
    int posicion=0;
    public reservasAdapter(List<MostarReservas> mostarReservas, Context mcontext) {
        this.MostarReservas = mostarReservas;
        this.mcontext = mcontext;
    }

    // Create new views (invoked by the layout manager)
    public reservasAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_reserva_realizada, parent, false);
        return new reservasAdapter.ViewHolder(view);

    }
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MostarReservas mostarReservas = MostarReservas.get(position);

        //dia y fecha
        String[] partes = mostarReservas.getFecha().split("/");
        String dia = "";
        String mes = "";
        String nombreMes = "";
        if (partes.length >= 1) {
            dia = partes[0];
            mes = partes[1];
            String[] nombresMeses = {
                    "enero", "febrero", "marzo", "abril", "mayo", "junio",
                    "julio", "agosto", "septiembre", "octubre", "noviembre", "diciembre"
            };

            int numeroMes = Integer.parseInt(mes);

            if (numeroMes >= 1 && numeroMes <= 12) {
                nombreMes = nombresMeses[numeroMes - 1];
            }
        }

        //medico
        int idMedico = mostarReservas.getIdMedico();
        List<String> medico = new ArrayList<>();
        medico = adminBD.nombreMedicoyEspecialidad(idMedico);

        holder.txtDia.setText(dia);
        holder.txtMes.setText(nombreMes.toUpperCase());
        holder.txthora.setText(mostarReservas.getHora());
        holder.txtLugar.setText(mostarReservas.getLugar());
        holder.txtDoctor.setText(medico.get(0));
        holder.txtEspecialidad.setText(medico.get(1));
        holder.txtfechaOculta.setText(mostarReservas.getFecha());
        holder.txtPosicion.setText("" + position);
        holder.imgBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Borrar Cita")
                        .setMessage("¿Esta seguro que deseas borrar esta cita?")
                        .setCancelable(false)
                        .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //llevo los datos vel card seleccionado a variables:
                                String hora, fecha, lugar;
                                hora = holder.txthora.getText().toString();
                                int medico = adminBD.saberIDMedico(holder.txtDoctor.getText().toString());
                                lugar = holder.txtLugar.getText().toString();
                                fecha = holder.txtfechaOculta.getText().toString();
                                posicion = Integer.parseInt(holder.txtPosicion.getText().toString());

                                int idReserva = adminBD.saberIDReserva(medico, hora, fecha,lugar);
                                int respuesta = adminBD.borrarReservasRealizadas(idReserva);
                                if (respuesta != 0){

                                    Toast.makeText(mcontext, "Cita borrada con exito", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(mcontext, "No se pudo borrar la cita", Toast.LENGTH_SHORT).show();
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
        return MostarReservas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtDia;
        TextView txtMes;
        TextView txtEspecialidad;
        TextView txthora;
        TextView txtDoctor;
        TextView txtLugar;
        TextView txtfechaOculta;
        TextView txtPosicion;
        ImageView imgBorrar;

        View mView;

        public ViewHolder(View view) {
            super(view);
            txtDia = view.findViewById(R.id.txtDia);
            txtMes = view.findViewById(R.id.txtMes);
            txtEspecialidad = view.findViewById(R.id.txtEspecialidad);
            txthora = view.findViewById(R.id.txthora);
            txtDoctor = view.findViewById(R.id.txtDoctor);
            txtLugar = view.findViewById(R.id.txtLugar);
            imgBorrar = view.findViewById(R.id.imgBorrar);
            txtfechaOculta = view.findViewById(R.id.txtfechaOculta);
            txtPosicion = view.findViewById(R.id.txtPosicion);

            mView = view;

            adminBD = new AdminBD(mcontext);

        }
    }
}
