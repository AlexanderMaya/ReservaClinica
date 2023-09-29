package com.straccion.reservahotel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txtRegistrar;

    AlertDialog mDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtRegistrar = findViewById(R.id.txtRegistrar);
    }


    public void iniciarSesion(View view){
        Intent intent = new Intent(MainActivity.this, Contenedor.class);
        int ventana = 1;
        intent.putExtra("abrir_Contenedor", ventana);
        startActivity(intent);
    }

    public void Registrase(View view){
        Intent intent = new Intent(MainActivity.this, crearUsuario.class);
        startActivity(intent);
    }


}