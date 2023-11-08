package com.straccion.reservahotel.perfil;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
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
import com.straccion.reservahotel.MainActivity;
import com.straccion.reservahotel.R;
import com.straccion.reservahotel.comprimirImagenes.CompressorBitmapImage;
import com.straccion.reservahotel.comprimirImagenes.FileUtil;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;

public class crearUsuario extends AppCompatActivity {

    CircleImageView circleImagenProfile, circleVolver;
    //AlertDialog.Builder mBuilderSelector;
    Button btnRegistrarse;
    TextView txtNombreUsuario;
    TextView txtCorreo;
    TextView textTelefono;
    TextView txtContraseña;
    TextView txtConfirmContra;
    File ImageFile;
    byte[] ArrayImagen;


    AdminBD adminBD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_usuario);

        circleImagenProfile = findViewById(R.id.circleImagenProfile);
        circleVolver = findViewById(R.id.circleVolver);
        btnRegistrarse = findViewById(R.id.btnRegistrarse);
        txtNombreUsuario = findViewById(R.id.txtNombreUsuario);
        txtCorreo = findViewById(R.id.txtCorreo);
        textTelefono = findViewById(R.id.textTelefono);
        txtContraseña = findViewById(R.id.txtContraseña);
        txtConfirmContra = findViewById(R.id.txtConfirmContra);

        adminBD = new AdminBD(this);

        btnRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = txtNombreUsuario.getText().toString();
                String correo = txtCorreo.getText().toString();
                String telefono = textTelefono.getText().toString();
                String contraseña = txtContraseña.getText().toString();
                String confirmContra = txtConfirmContra.getText().toString();
                if (!contraseña.equals(confirmContra)){
                    Toast.makeText(crearUsuario.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                }else {
                    ContentValues values = new ContentValues();
                    values.put("nombre", "IMAGEN1");
                    values.put("imagen", ImageFile.toString());
                    adminBD.insertarDatosUsuario(nombre, correo, telefono, contraseña, values);
                    Toast.makeText(crearUsuario.this, "Usuario creado con exito", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(crearUsuario.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
        circleImagenProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });
        circleVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
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
                            ImageFile = FileUtil.from(crearUsuario.this, result.getData().getData());
                            circleImagenProfile.setImageBitmap(BitmapFactory.decodeFile(ImageFile.getAbsolutePath()));
                            ArrayImagen = CompressorBitmapImage.getImage(crearUsuario.this, ImageFile.toString(), 100, 100);
                        }catch (Exception e){
                            Log.d("Error", "Se produjo un error" + e.getMessage());
                            Toast.makeText(crearUsuario.this, "Se produjo un error" + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
    );
}