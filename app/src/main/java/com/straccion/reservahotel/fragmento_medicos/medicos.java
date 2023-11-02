package com.straccion.reservahotel.fragmento_medicos;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.straccion.reservahotel.Contenedor;
import com.straccion.reservahotel.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link medicos#newInstance} factory method to
 * create an instance of this fragment.
 */
public class medicos extends Fragment {
    CircleImageView imgVolverFiltro;
    View mView;
    int idUser;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public medicos() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment medicos.
     */
    // TODO: Rename and change types and number of parameters
    public static medicos newInstance(String param1, String param2) {
        medicos fragment = new medicos();
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
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_medicos, container, false);
        imgVolverFiltro = mView.findViewById(R.id.imgVolverFiltro);

        imgVolverFiltro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //consular();
            }
        });

        return mView;
    }

    public void consular(int abrirventana){
        Intent intent = new Intent(getContext(), Contenedor.class);
        int ventana = abrirventana;
        intent.putExtra("abrir_Contenedor", ventana);
        intent.putExtra("idUser", idUser);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.slide_up_in, R.anim.slide_up_out);
    }
}