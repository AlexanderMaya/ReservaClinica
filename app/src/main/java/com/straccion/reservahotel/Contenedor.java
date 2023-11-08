package com.straccion.reservahotel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.straccion.reservahotel.fragmento_medicos.filtrosMedicos;
import com.straccion.reservahotel.fragmento_medicos.medicos;
import com.straccion.reservahotel.fragmentos_agendar.centrosClinicos;
import com.straccion.reservahotel.fragmentos_agendar.ingresarReserva;
import com.straccion.reservahotel.fragmentos_agendar.reservaFiltro;
import com.straccion.reservahotel.fragmentos_agendar.reservarCita;
import com.straccion.reservahotel.fragmentos_modificar_reservas.modificarFiltro;
import com.straccion.reservahotel.fragmentos_modificar_reservas.modificarReserva;
import com.straccion.reservahotel.fragmentos_modificar_reservas.mostrarReservas;
import com.straccion.reservahotel.fragmentos_visualizar_reservas.visualizarReservas;
import com.straccion.reservahotel.objetos.MostarReservas;

import java.io.File;

public class Contenedor extends AppCompatActivity {

    BottomNavigationView bottomNavigation;
    File ImageFile;
    int idUser;

    String especialidad;
    String ciudad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contenedor);
        Intent intent = getIntent();
        idUser = intent.getIntExtra("idUser", 0);
        especialidad = intent.getStringExtra("especialidad");
        ciudad = intent.getStringExtra("ciudad");
        Bundle args = new Bundle();
        args.putInt("idUser", idUser);
        //Pagina documentacion: https://androidwave.com/bottom-navigation-bar-android-example/
        bottomNavigation = findViewById(R.id.bottomNavigationView);

        //recarga nuevamente la pantalla de eliminar una reserva
        int ventana2=0;
        ventana2 = intent.getIntExtra("ventana2", 0);
        if (ventana2 > 0){
            bottomNavigation.setSelectedItemId(R.id.itemReservasRealizadas);
            abrirFragmento(new visualizarReservas(), args);
        }


        bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.itemRegistrarCita) {
                    abrirFragmento(new ingresarReserva(), args);
                } else if (item.getItemId() == R.id.itemReservasRealizadas) {
                    abrirFragmento(new visualizarReservas(), args);
                } else if (item.getItemId() == R.id.itemMedicos) {
                    abrirFragmento(new filtrosMedicos(), args);
                }
                return true;
            }
        });



        if (intent != null) {
            int valorRecibido = intent.getIntExtra("abrir_Contenedor", 0);
            int modificar = intent.getIntExtra("modificar", 0);
            if (valorRecibido == 1){
                abrirFragmento(new ingresarReserva(), args);
            } else if (valorRecibido == 2) {
                abrirFragmento(new reservaFiltro(), args);
            }else if (valorRecibido == 3) {
                args.putString("especialidad", especialidad);
                args.putString("ciudad", ciudad);
                abrirFragmento(new reservarCita(), args);
            }else if (valorRecibido == 4) {
                abrirFragmento(new centrosClinicos(), args);
            }else if (valorRecibido == 5) {
                abrirFragmento(new medicos(), args);
                MenuItem menuItem = bottomNavigation.getMenu().findItem(R.id.itemMedicos);
                menuItem.setChecked(true);// sirve para tener el item de medico seleccioando
            }else if (valorRecibido == 6) {
                abrirFragmento(new mostrarReservas(), args);
            }else if (valorRecibido == 7) {
                Bundle bundle = intent.getExtras();
                if (modificar != 0){
                    abrirFragmento(new modificarReserva(), bundle);
                }else {
                    abrirFragmento(new modificarFiltro(), bundle);
                }
            }
        }else {
            //abrirFragmento(new ingresarReserva());
            int numero;
        }
    }

    public void abrirFragmento(Fragment fragment, Bundle args) {
        if (args != null){
            fragment.setArguments(args); // Configura los argumentos en el Fragment
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_up_in, R.anim.slide_up_out);
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }



}