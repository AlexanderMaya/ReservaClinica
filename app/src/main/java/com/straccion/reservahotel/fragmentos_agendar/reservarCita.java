package com.straccion.reservahotel.fragmentos_agendar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.DatePicker;
import android.widget.ImageView;

import com.straccion.reservahotel.Adaptadores.Adapter;
import com.straccion.reservahotel.AdminBD;
import com.straccion.reservahotel.Contenedor;
import com.straccion.reservahotel.objetos.HorariosMedicos;
import com.straccion.reservahotel.R;
import com.straccion.reservahotel.objetos.MostarReservas;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link reservarCita#newInstance} factory method to
 * create an instance of this fragment.
 */
public class reservarCita extends Fragment {
    List<Integer> dats = new ArrayList<>();
    List<HorariosMedicos> horariosMedicos;
    RecyclerView rclAgendar;
    View mView;
    ImageView imgAtras;
    AppCompatSpinner spnElegirMedico;
    AdminBD adminBD;
    Adapter adapter;

    String ciudad;
    String nombreMedico;
    String fecha;
    String especialidad;
    int idUser;

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
        Bundle args = getArguments();
        if (args != null){
            especialidad = args.getString("especialidad");
            ciudad = args.getString("ciudad");
            idUser = args.getInt("idUser");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_reservar_cita, container, false);


        imgAtras = mView.findViewById(R.id.imgAtras);
        spnElegirMedico = mView.findViewById(R.id.spnElegirMedico);
        rclAgendar = mView.findViewById(R.id.rclAgendar);
        rclAgendar.setHasFixedSize(true);

        adminBD = new AdminBD(getContext());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rclAgendar.setLayoutManager(linearLayoutManager);

        //saber nombre de medico
        spnElegirMedico.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int posicion, long id) {
                nombreMedico = adapterView.getItemAtPosition(posicion).toString();
                cargarLista();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });





        List<String> nombres  = adminBD.medicosFiltroReserva(ciudad, especialidad);
        ArrayAdapter<String> adap = new ArrayAdapter<>(getContext(), R.layout.spinner_item_custom, nombres);
        spnElegirMedico.setAdapter(adap);
        nombreMedico = spnElegirMedico.getSelectedItem().toString();


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
                horariosMedicos = new ArrayList<>();
                cargarLista();
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

    private void cargarLista() {
        String clinica = adminBD.clinicaTrabajoMedico(nombreMedico);
        fecha = editTextDate.getText().toString();
        //SELECT ID FROM reservaciones WHERE IDMEDICO = 1 AND HORA = '09:30 AM' AND FECHA = '23/10/2023' AND LUGAR = 'Clinica Medellin S.A.S'

        HorariosMedicos horario0 = new HorariosMedicos("08:00 AM", clinica, nombreMedico, fecha );
        HorariosMedicos horario1 = new HorariosMedicos("08:30 AM", clinica, nombreMedico, fecha );
        HorariosMedicos horario2 = new HorariosMedicos("09:00 AM", clinica, nombreMedico, fecha );
        HorariosMedicos horario3 = new HorariosMedicos("09:30 AM", clinica, nombreMedico, fecha);
        HorariosMedicos horario4 = new HorariosMedicos("10:00 AM", clinica, nombreMedico, fecha);
        HorariosMedicos horario5 = new HorariosMedicos("10:30 AM", clinica, nombreMedico, fecha);
        HorariosMedicos horario6 = new HorariosMedicos("11:00 AM", clinica, nombreMedico, fecha);
        HorariosMedicos horario7 = new HorariosMedicos("11:30 AM", clinica, nombreMedico, fecha);
        HorariosMedicos horario8 = new HorariosMedicos("12:00 M", clinica, nombreMedico, fecha);
        HorariosMedicos horario9 = new HorariosMedicos("13:00 PM", clinica, nombreMedico, fecha);
        HorariosMedicos horario10 = new HorariosMedicos("13:30 PM", clinica, nombreMedico, fecha);
        HorariosMedicos horario11 = new HorariosMedicos("14:00 PM", clinica, nombreMedico, fecha);



        List<HorariosMedicos> horariosMedicos = new ArrayList<>();

        horariosMedicos.add(horario0);
        horariosMedicos.add(horario1);
        horariosMedicos.add(horario2);
        horariosMedicos.add(horario3);
        horariosMedicos.add(horario4);
        horariosMedicos.add(horario5);
        horariosMedicos.add(horario6);
        horariosMedicos.add(horario7);
        horariosMedicos.add(horario8);
        horariosMedicos.add(horario9);
        horariosMedicos.add(horario10);
        horariosMedicos.add(horario11);

        List<MostarReservas> datos = new ArrayList<>();
        datos = adminBD.validarReservasAMostrar();
        for (int i = 0; i <=11 ; i++) {
            HorariosMedicos horarios = horariosMedicos.get(i);

            String nombreMedico = horarios.getMedico();
            String horaCita = horarios.getHora();
            String lugarCita = horarios.getClinica();
            String fecha = horarios.getFecha();

            //datos de las reservas

            boolean encontrado = false;
            //***************************************************
            //busqueda 1    HORA
            if (!datos.isEmpty()) {
                int id = adminBD.saberIDMedico(nombreMedico);


                for (MostarReservas horario : datos) {
                    if (horario.getHora().equals(horaCita) &&
                            horario.getFecha().equals(fecha) &&
                            horario.getLugar().equals(lugarCita) &&
                            horario.getIdMedico().equals(id)) {
                        encontrado = true;
                        dats.add(i);
                        break;
                    }
                }
            }
        }
        for (int i = 0; i <dats.size() ; i++) {
            int borrar = dats.get(i);
            horariosMedicos.remove(borrar-i);
        }


        adapter = new Adapter(horariosMedicos, getContext(), idUser);

        rclAgendar.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
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