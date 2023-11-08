package com.straccion.reservahotel.fragmentos_modificar_reservas;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.straccion.reservahotel.Adaptadores.modificarAdapter;
import com.straccion.reservahotel.AdminBD;
import com.straccion.reservahotel.Contenedor;
import com.straccion.reservahotel.R;
import com.straccion.reservahotel.objetos.MostarReservas;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link mostrarReservas#newInstance} factory method to
 * create an instance of this fragment.
 */
public class mostrarReservas extends Fragment {
    modificarAdapter modificarAdapter;
    View mView;
    AdminBD adminBD;
    RecyclerView rclReservas;
    LinearLayout lnlMensaje;
    ImageView imgPaginaPrincipal;
    int idUser;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public mostrarReservas() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment mostrarReservas.
     */
    // TODO: Rename and change types and number of parameters
    public static mostrarReservas newInstance(String param1, String param2) {
        mostrarReservas fragment = new mostrarReservas();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        Bundle args = getArguments();
        if (args != null){
            idUser = args.getInt("idUser",0);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_mostrar_reservas, container, false);



        lnlMensaje = mView.findViewById(R.id.lnlMensaje);
        rclReservas = mView.findViewById(R.id.rclReservas);
        imgPaginaPrincipal = mView.findViewById(R.id.imgPaginaPrincipal);

        imgPaginaPrincipal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                volver(1);
            }
        });
        adminBD = new AdminBD(getContext());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rclReservas.setLayoutManager(linearLayoutManager);

        List<MostarReservas> datos = new ArrayList<>();
        datos = adminBD.validarReservasAMostrar();

        modificarAdapter = new modificarAdapter(datos, idUser, getContext());

        rclReservas.setAdapter(modificarAdapter);
        if (modificarAdapter.getItemCount() > 0){
            lnlMensaje.setVisibility(View.INVISIBLE);
        }



        return  mView;
    }

    public void volver(int abrirventana){
        Intent intent = new Intent(getContext(), Contenedor.class);
        int ventana = abrirventana;
        intent.putExtra("abrir_Contenedor", ventana);
        intent.putExtra("idUser", idUser);
        startActivity(intent);
    }
}