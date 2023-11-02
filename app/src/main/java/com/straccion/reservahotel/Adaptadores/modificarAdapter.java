package com.straccion.reservahotel.Adaptadores;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.straccion.reservahotel.AdminBD;
import com.straccion.reservahotel.Contenedor;
import com.straccion.reservahotel.R;
import com.straccion.reservahotel.objetos.MostarReservas;
import com.straccion.reservahotel.fragmentos_modificar_reservas.modificarFiltro;

import java.util.ArrayList;
import java.util.List;

public class modificarAdapter extends RecyclerView.Adapter<modificarAdapter.ViewHolder>{
    private List<com.straccion.reservahotel.objetos.MostarReservas> MostarReservas;
    private Context mcontext;
    AdminBD adminBD;
    modificarFiltro mod;

    int idUser=0;
    int posicion=0;
    public modificarAdapter(List<MostarReservas> mostarReservas, int id, Context mcontext) {
        this.idUser = id;
        this.MostarReservas = mostarReservas;
        this.mcontext = mcontext;
    }

    // Create new views (invoked by the layout manager)
    public modificarAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_modificar_reservas, parent, false);
        return new modificarAdapter.ViewHolder(view);

    }
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull modificarAdapter.ViewHolder holder, int position) {
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
        holder.imgEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hora, fecha, lugar;
                hora = holder.txthora.getText().toString();
                int medico = adminBD.saberIDMedico(holder.txtDoctor.getText().toString());
                lugar = holder.txtLugar.getText().toString();
                fecha = holder.txtfechaOculta.getText().toString();
                int idReserva = adminBD.saberIDReserva(medico, hora, fecha,lugar);


                ContentValues datos = new ContentValues();
                datos.put("hora", holder.txthora.getText().toString());
                datos.put("medico", adminBD.saberIDMedico(holder.txtDoctor.getText().toString()));
                datos.put("lugar", holder.txtLugar.getText().toString());
                datos.put("fecha", holder.txtfechaOculta.getText().toString());
                datos.put("hora", holder.txthora.getText().toString());
                datos.put("idReserva", adminBD.saberIDReserva(medico, hora, fecha,lugar));
                datos.put("idUser", idUser);


                Bundle bundle = new Bundle();
                bundle.putParcelable("datos",datos);

                Intent intent = new Intent(mcontext, Contenedor.class);
                intent.putExtra("idUser", idUser );
                intent.putExtras(bundle);
                intent.putExtra("abrir_Contenedor", 7 );
                mcontext.startActivity(intent);
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
        LinearLayout imgEditar;

        View mView;

        public ViewHolder(View view) {
            super(view);
            txtDia = view.findViewById(R.id.txtDia);
            txtMes = view.findViewById(R.id.txtMes);
            txtEspecialidad = view.findViewById(R.id.txtEspecialidad);
            txthora = view.findViewById(R.id.txthora);
            txtDoctor = view.findViewById(R.id.txtDoctor);
            txtLugar = view.findViewById(R.id.txtLugar);
            imgEditar = view.findViewById(R.id.imgEditar);
            txtfechaOculta = view.findViewById(R.id.txtfechaOculta);
            txtPosicion = view.findViewById(R.id.txtPosicion);

            mView = view;

            adminBD = new AdminBD(mcontext);

        }
    }
}
