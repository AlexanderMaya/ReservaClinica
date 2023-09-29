package com.straccion.reservahotel.fragmentos_agendar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;


import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.DatePicker;
import android.widget.ImageView;

import com.straccion.reservahotel.Contenedor;
import com.straccion.reservahotel.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link reservarCita#newInstance} factory method to
 * create an instance of this fragment.
 */
public class reservarCita extends Fragment {

    View mView;
    ImageView imgAtras;
    AppCompatSpinner spnElegirMedico;

    private SimpleDateFormat dateFormatter;
    private DatePickerDialog datePickerDialog;
    private EditText editTextDate;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public reservarCita() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment reservarCita.
     */
    // TODO: Rename and change types and number of parameters
    public static reservarCita newInstance(String param1, String param2) {
        reservarCita fragment = new reservarCita();
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
        mView = inflater.inflate(R.layout.fragment_reservar_cita, container, false);


        imgAtras = mView.findViewById(R.id.imgAtras);
        spnElegirMedico = mView.findViewById(R.id.spnElegirMedico);

        String[] opcionesEspcialidad = {"Dr. Juan Pérez", "Dr. Manuel Gonzales", "Dr. Carlos Sánchez", "Dr. Jorge Rodríguez", "Dr. Eduardo López", "Dr. Andrés Martínez", "Dr. Luis Ramírez", "Dr. Antonio Torres"};
        ArrayAdapter<String> adap = new ArrayAdapter<>(getContext(), R.layout.spinner_item_custom, opcionesEspcialidad);
        spnElegirMedico.setAdapter(adap);


        //Codigo para mostrar el calendario
        editTextDate = mView.findViewById(R.id.editTextDate);
        dateFormatter = new SimpleDateFormat("dd/MM/yyyy");

        // Obtiene la fecha actual
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();
        String fechahoy = dateFormatter.format(currentDate);
        editTextDate.setText(fechahoy);
        datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                editTextDate.setText(selectedDate);
            }
        },
            Calendar.getInstance().get(Calendar.YEAR),
            Calendar.getInstance().get(Calendar.MONTH),
            Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        // Esto evita que el teclado aparezca cuando se hace clic en el EditText
        editTextDate.setInputType(InputType.TYPE_NULL);

        // Muestra el DatePickerDialog cuando se hace clic en el EditText
        editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });

        imgAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consular(2);
            }
        });
        return  mView;
    }

    public void consular(int abrirventana){
        Intent intent = new Intent(getContext(), Contenedor.class);
        int ventana = abrirventana;
        intent.putExtra("abrir_Contenedor", ventana);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.slide_up_in, R.anim.slide_up_out);
    }



}