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
import com.straccion.reservahotel.fragmentos_visualizar_reservas.visualizarReservas;

public class Contenedor extends AppCompatActivity {

    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contenedor);

        //Pagina documentacion: https://androidwave.com/bottom-navigation-bar-android-example/
        bottomNavigation = findViewById(R.id.bottomNavigationView);
        bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.itemRegistrarCita) {
                    abrirFragmento(new ingresarReserva());
                } else if (item.getItemId() == R.id.itemReservasRealizadas) {
                    abrirFragmento(new visualizarReservas());
                } else if (item.getItemId() == R.id.itemMedicos) {
                    abrirFragmento(new filtrosMedicos());
                }
                return true;
            }

        });



        Intent intent = getIntent();
        if (intent != null) {
            int valorRecibido = intent.getIntExtra("abrir_Contenedor", 0);
            if (valorRecibido == 1){
                abrirFragmento(new ingresarReserva());
            } else if (valorRecibido == 2) {
                abrirFragmento(new reservaFiltro());
            }else if (valorRecibido == 3) {
                abrirFragmento(new reservarCita());
            }else if (valorRecibido == 4) {
                abrirFragmento(new centrosClinicos());
            }else if (valorRecibido == 5) {
                abrirFragmento(new medicos());
                MenuItem menuItem = bottomNavigation.getMenu().findItem(R.id.itemMedicos);
                menuItem.setChecked(true);// sirve para tener el item de medico seleccioando
            }
        }else {
            //abrirFragmento(new ingresarReserva());
            int numero;
        }



    }
    public void abrirFragmento(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_up_in, R.anim.slide_up_out);
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }



}