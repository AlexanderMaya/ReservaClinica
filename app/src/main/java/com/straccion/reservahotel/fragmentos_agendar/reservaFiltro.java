package com.straccion.reservahotel.fragmentos_agendar;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;


import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;


import com.straccion.reservahotel.Contenedor;
import com.straccion.reservahotel.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link reservaFiltro#newInstance} factory method to
 * create an instance of this fragment.
 */
public class reservaFiltro extends Fragment {

    Button btnConsultar;
    AppCompatSpinner spnCiudad, spnEspecialidad;
    ImageView imgRegresar;
    View mView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public reservaFiltro() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment reservaFiltro.
     */
    // TODO: Rename and change types and number of parameters
    public static reservaFiltro newInstance(String param1, String param2) {
        reservaFiltro fragment = new reservaFiltro();
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

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_reserva_filtro, container, false);
        btnConsultar = mView.findViewById(R.id.btnConsultar);
        imgRegresar = mView.findViewById(R.id.imgRegresar);
        spnCiudad = mView.findViewById(R.id.spnCiudad);
        spnEspecialidad = mView.findViewById(R.id.spnEspecialidad);



        String[] opcionesCiudad = {"Medellin", "Bogota", "Cali", "Barranquilla"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.spinner_item_custom, opcionesCiudad);
        spnCiudad.setAdapter(adapter);

        String[] opcionesEspcialidad = {"Dermatologia", "Ginecologia", "Medicina Interna", "Oftalmologia", "Ortopedia y Traumatologia", "Pediatria", "Urologia"};
        ArrayAdapter<String> adap = new ArrayAdapter<>(getContext(), R.layout.spinner_item_custom, opcionesEspcialidad);
        spnEspecialidad.setAdapter(adap);



        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consultar(3);
            }
        });

        imgRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consultar(1);
            }
        });


        return mView;



    }

    public void consultar(int abrirventana){
        Intent intent = new Intent(getContext(), Contenedor.class);
        int ventana = abrirventana;
        intent.putExtra("abrir_Contenedor", ventana);
        startActivity(intent);
    }



}