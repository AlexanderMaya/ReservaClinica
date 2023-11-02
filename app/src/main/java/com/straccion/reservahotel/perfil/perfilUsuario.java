package com.straccion.reservahotel.perfil;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.straccion.reservahotel.AdminBD;
import com.straccion.reservahotel.Contenedor;
import com.straccion.reservahotel.MainActivity;
import com.straccion.reservahotel.R;
import com.straccion.reservahotel.comprimirImagenes.CompressorBitmapImage;
import com.straccion.reservahotel.comprimirImagenes.FileUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class perfilUsuario extends AppCompatActivity {

    CircleImageView circleImagenProfile;
    CircleImageView circleVolver;
    TextView txtNombreUsuario;
    TextView txtCorreo;
    TextView textTelefono;
    TextView txtCerrarCesion;
    Button btnActualizar;



    File ImageFile;
    byte[] ArrayImagen;
    AdminBD adminBD;

    int idUser;
    String imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);

        Intent intent = getIntent();
        idUser = intent.getIntExtra("idUser", 0);

        circleImagenProfile = findViewById(R.id.circleImagenProfile);
        circleVolver = findViewById(R.id.circleVolver);
        btnActualizar = findViewById(R.id.btnActualizar);
        txtNombreUsuario = findViewById(R.id.txtNombreUsuario);
        txtCorreo = findViewById(R.id.txtCorreo);
        textTelefono = findViewById(R.id.textTelefono);
        txtCerrarCesion = findViewById(R.id.txtCerrarCesion);

        adminBD = new AdminBD(this);

        llenarDatosUsuario();

        txtCerrarCesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(perfilUsuario.this, MainActivity.class);
                startActivity(intent2);
            }
        });

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualizarPerfil();
            }
        });
        circleVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        circleImagenProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });
        mostrarImagenPerfil();
    }
    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        GalleryLauncher.launch(galleryIntent);
    }
    ActivityResultLauncher<Intent> GalleryLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK){
                        try {
                            ImageFile = FileUtil.from(perfilUsuario.this, result.getData().getData());
                            circleImagenProfile.setImageBitmap(BitmapFactory.decodeFile(ImageFile.getAbsolutePath()));
                            ArrayImagen = CompressorBitmapImage.getImage(perfilUsuario.this, ImageFile.toString(), 100, 100);
                        }catch (Exception e){
                            Log.d("Error", "Se produjo un error" + e.getMessage());
                            Toast.makeText(perfilUsuario.this, "Se produjo un error" + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
    );

    public void mostrarImagenPerfil(){
        if (idUser != -1){
            imagen = adminBD.mostrarImagen(idUser);
            if (imagen != null){
                ImageFile = new File(imagen);
                circleImagenProfile.setImageBitmap(BitmapFactory.decodeFile(ImageFile.getAbsolutePath()));
            }
        }
    }

    public void llenarDatosUsuario(){
        List<String> datosUsuario = new ArrayList<>();
        datosUsuario = adminBD.datosUsuario(idUser);
        if (!datosUsuario.isEmpty()){
            textTelefono.setText(datosUsuario.get(0));
            txtNombreUsuario.setText(datosUsuario.get(1));
            txtCorreo.setText(datosUsuario.get(2));
        }
    }

    public  void actualizarPerfil(){
        String nombreUsuario = txtNombreUsuario.getText().toString();
        String correoUsuario = txtCorreo.getText().toString();
        String telefonoUsuario = textTelefono.getText().toString();
        ContentValues values = new ContentValues();
        values.put("nombre", "IMAGEN1");
        values.put("imagen", ImageFile.toString());
        int resultado = adminBD.actualizarDatosUsuario(idUser, nombreUsuario, correoUsuario, telefonoUsuario, values);
        if (resultado > 0) {
            Toast.makeText(this, "Dato actualizado con Exito", Toast.LENGTH_LONG).show();
        }
        Intent intent = new Intent(perfilUsuario.this, Contenedor.class);
        int ventana = 1;
        intent.putExtra("abrir_Contenedor", ventana);
        intent.putExtra("idUser", idUser);
        startActivity(intent);
    }

}