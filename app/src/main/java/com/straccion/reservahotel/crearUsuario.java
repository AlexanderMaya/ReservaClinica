package com.straccion.reservahotel;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Bundle;
import android.os.FileUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class crearUsuario extends AppCompatActivity {

    CircleImageView circleImagenProfile;
    AlertDialog.Builder mBuilderSelector;
    CharSequence options[];
    File ImageFile;
    File mPhotoFile1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_usuario);

        circleImagenProfile = findViewById(R.id.circleImagenProfile);

        circleImagenProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectOptionImage(1);
            }
        });

    }

    private void selectOptionImage(int requestCode) {
        mBuilderSelector.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                openGallery();
            }
        });
        mBuilderSelector.show();
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
                            mPhotoFile1 = null;
                            //ImageFile = FileUtil.from(this, result.getData().getData());
                            //circleImagenProfile.setImageBitmap(BitmapFactory.decodeFile(mImageFile.getAbsolutePath()));
                        }catch (Exception e){
                            Log.d("Error", "Se produjo un error" + e.getMessage());
                            Toast.makeText(crearUsuario.this, "Se produjo un error" + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
    );
}