package com.straccion.reservahotel;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.straccion.reservahotel.perfil.crearUsuario;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    TextView txtRegistrar;
    File ImageFile;
    int idUser = -1;
    AlertDialog mDialog;
    TextView txtInputEmail;

    AdminBD adminBD;
    TextView txtInputPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtRegistrar = findViewById(R.id.txtRegistrar);
        txtInputEmail = findViewById(R.id.txtInputEmail);
        txtInputPassword = findViewById(R.id.txtInputPassword);

        adminBD = new AdminBD(this);

        //creacion de BD o verificacion de la misma
        AdminBD Base_datos = AdminBD.getInstance(this);
        SQLiteDatabase db = Base_datos.getWritableDatabase();
    }


    public void iniciarSesion(View view){

        if (!txtInputEmail.getText().toString().equals("")){
            if(!txtInputPassword.getText().toString().equals("")){
                String correo = txtInputEmail.getText().toString();
                String contra = txtInputPassword.getText().toString();

                idUser = adminBD.saberID(correo, contra);
                if (idUser > 0){
                    //lleno la base de datos
                    adminBD.llenarMedicos();

                    Intent intent = new Intent(MainActivity.this, Contenedor.class);
                    int ventana = 1;
                    intent.putExtra("abrir_Contenedor", ventana);
                    intent.putExtra("idUser", idUser);
                    startActivity(intent);
                }else {
                    Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(this, "La contraseña no puede estar vacia", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this, "El correo no puede estar vacio", Toast.LENGTH_SHORT).show();
        }
    }

    public void Registrase(View view){
        Intent intent = new Intent(MainActivity.this, crearUsuario.class);
        startActivity(intent);
    }

}