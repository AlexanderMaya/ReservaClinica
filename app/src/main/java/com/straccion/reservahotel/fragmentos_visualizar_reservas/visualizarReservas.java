package com.straccion.reservahotel.fragmentos_visualizar_reservas;

import android.content.ContentValues;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.straccion.reservahotel.Adaptadores.reservasAdapter;
import com.straccion.reservahotel.Adaptadores.Adapter;
import com.straccion.reservahotel.AdminBD;
import com.straccion.reservahotel.R;
import com.straccion.reservahotel.objetos.HorariosMedicos;
import com.straccion.reservahotel.objetos.MostarReservas;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link visualizarReservas#newInstance} factory method to
 * create an instance of this fragment.
 */
public class visualizarReservas extends Fragment {
    reservasAdapter reservasAdapter;
    View mView;
    AdminBD adminBD;
    Bundle args;
    int idUser;
    LinearLayout lnlMensaje;
    RecyclerView rclReservas;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public visualizarReservas() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment visualizarReservas.
     */
    // TODO: Rename and change types and number of parameters
    public static visualizarReservas newInstance(String param1, String param2) {
        visualizarReservas fragment = new visualizarReservas();
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
        args = getArguments();
        if (args != null){
            idUser = args.getInt("idUser");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_visualizar_reservas, container, false);

        rclReservas = mView.findViewById(R.id.rclReservas);
        lnlMensaje = mView.findViewById(R.id.lnlMensaje);

        adminBD = new AdminBD(getContext());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rclReservas.setLayoutManager(linearLayoutManager);

        List<MostarReservas> datos = new ArrayList<>();
        datos = adminBD.validarReservasAMostrar();

        reservasAdapter = new reservasAdapter(datos, getContext(), idUser);

        rclReservas.setAdapter(reservasAdapter);
        if (reservasAdapter.getItemCount() > 0){
            lnlMensaje.setVisibility(View.INVISIBLE);
        }

        return  mView;
    }
}