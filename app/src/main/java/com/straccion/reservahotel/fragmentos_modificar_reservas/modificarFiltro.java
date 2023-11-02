package com.straccion.reservahotel.fragmentos_modificar_reservas;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.straccion.reservahotel.AdminBD;
import com.straccion.reservahotel.Contenedor;
import com.straccion.reservahotel.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link modificarFiltro#newInstance} factory method to
 * create an instance of this fragment.
 */
public class modificarFiltro extends Fragment {
    Button btnConsultar;
    AdminBD adminBD;
    AppCompatSpinner spnCiudad, spnEspecialidad;
    ImageView imgRegresar;
    View mView;

    String ciudad;
    String especialidad;
    int idUser;
    Bundle args;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public modificarFiltro() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment modificarFiltro.
     */
    // TODO: Rename and change types and number of parameters
    public static modificarFiltro newInstance(String param1, String param2) {
        modificarFiltro fragment = new modificarFiltro();
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
            idUser = args.getInt("idUser",0);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_modificar_filtro, container, false);
        btnConsultar = mView.findViewById(R.id.btnConsultar);
        imgRegresar = mView.findViewById(R.id.imgRegresar);
        spnCiudad = mView.findViewById(R.id.spnCiudad);
        spnEspecialidad = mView.findViewById(R.id.spnEspecialidad);

        adminBD = new AdminBD(getContext());

        String[] opcionesCiudad = {"Medellin", "Sabaneta", "Envigado", "Bello", "Itagui", "Rionegro", "La Estrella"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.spinner_item_custom, opcionesCiudad);
        spnCiudad.setAdapter(adapter);
        llenadoSpinerEspecialidad();

        spnCiudad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int posicion, long id) {
                String ciudad = adapterView.getItemAtPosition(posicion).toString();
                ciudad = spnCiudad.getSelectedItem().toString();
                List<String> opcionesEspecialidad  = adminBD.spinnerFiltroxCiudad(ciudad);
                ArrayAdapter<String> adap = new ArrayAdapter<>(getContext(), R.layout.spinner_item_custom, opcionesEspecialidad);
                spnEspecialidad.setAdapter(adap);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ciudad = spnCiudad.getSelectedItem().toString();
                especialidad = spnEspecialidad.getSelectedItem().toString();
                consultar(8);
            }
        });

        imgRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regresar(1);
            }
        });


        return mView;
    }

    public void consultar(int abrirventana){
        Intent intent = new Intent(getContext(), Contenedor.class);
        int ventana = abrirventana;
        intent.putExtra("abrir_Contenedor", ventana);
        intent.putExtra("ciudad", ciudad);
        intent.putExtra("especialidad", especialidad);
        intent.putExtra("idUser", idUser);
        intent.putExtras(args);
        startActivity(intent);
    }

    public void llenadoSpinerEspecialidad(){
        ciudad = spnCiudad.getSelectedItem().toString();
        adminBD.spinnerFiltroxCiudad(ciudad);
        List<String> opcionesEspecialidad  = adminBD.spinnerFiltroxCiudad(ciudad);
        ArrayAdapter<String> adap = new ArrayAdapter<>(getContext(), R.layout.spinner_item_custom, opcionesEspecialidad);
        spnEspecialidad.setAdapter(adap);
    }
    public void regresar(int abrirventana){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Cancelar modificación")
                .setMessage("¿Esta seguro que desea regresar?")
                .setCancelable(false)
                .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(getContext(), Contenedor.class);
                        int ventana = abrirventana;
                        intent.putExtra("abrir_Contenedor", ventana);
                        intent.putExtra("idUser", idUser);
                        startActivity(intent);
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

}