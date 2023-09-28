package com.straccion.reservahotel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void iniciarSesion(View view){
        Intent intent = new Intent(MainActivity.this, Contenedor.class);
        int ventana = 1;
        intent.putExtra("abrir_Contenedor", ventana);
        startActivity(intent);
    }


}