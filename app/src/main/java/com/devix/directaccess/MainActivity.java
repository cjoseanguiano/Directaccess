package com.devix.directaccess;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int CAMERA_CODE = 100;
    private static final int BLUETOOTH = 200;
    private TextView txtCamera;
    private TextView txtVideo;
    private Button btnCamera;
    private Button btnVideo;
    private ImageView imgCamera;
    private ImageView imgVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtCamera = (TextView) findViewById(R.id.txtCamera);
        txtVideo = (TextView) findViewById(R.id.txtVideo);
        btnCamera = (Button) findViewById(R.id.btnCameraOk);
        btnVideo = (Button) findViewById(R.id.btnVideoOk);
        imgCamera = (ImageView) findViewById(R.id.imgCamera);
        imgVideo = (ImageView) findViewById(R.id.imgVideo);

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    showSnackBar(btnCamera);
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, CAMERA_CODE);

                } else {
                    showSnackBar(btnCamera);

                }

            }
        });
        btnVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    showSnackBar(btnVideo);
                    requestPermissions(new String[]{Manifest.permission.BLUETOOTH}, BLUETOOTH);
                } else {
                    showSnackBar(btnVideo);
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case CAMERA_CODE:
                String permission = permissions[0];
                int result = grantResults[0];

                if (permission.equals(Manifest.permission.CAMERA)) {

                    if (result == PackageManager.PERMISSION_GRANTED) {
                        Intent intent = new Intent(Intent.ACTION_CALL);
                        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//                            startActivity(intent);crear segunda actividad
                            Toast.makeText(this, "ACEPTO CAMERA", Toast.LENGTH_SHORT).show();

                        }
                    }
                } else {
                    Toast.makeText(this, "NO ACEPTO CAMERA", Toast.LENGTH_SHORT).show();

                }
                break;
            case BLUETOOTH:
                String permissionVideo = permissions[0];
                int resultVideo = grantResults[0];
                if (permissionVideo.equals(Manifest.permission.BLUETOOTH)) {
                    if (resultVideo == PackageManager.PERMISSION_GRANTED){
//                        Intent intent = new Intent(Intent.BLU)
                                if (ActivityCompat.checkSelfPermission(this,Manifest.permission.BLUETOOTH) != PackageManager.PERMISSION_GRANTED){
                                    Toast.makeText(this, "ACEPTO BLUETOOTH", Toast.LENGTH_SHORT).show();

                                }
                    }
                }
                else {
                    Toast.makeText(this, "NO ACEPTO BLUETOOTH", Toast.LENGTH_SHORT).show();

                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }
    }

    private void oldVersion() {
        if (checkPermission(Manifest.permission.CAMERA)) {
//            startActivity(intentCamera);
            Toast.makeText(this, "Activo la camara", Toast.LENGTH_SHORT).show();

        }else if (checkPermission(Manifest.permission.BLUETOOTH)){
            Toast.makeText(this, "Activo la BLUETOOTH", Toast.LENGTH_SHORT).show();

        }
            else {
            Toast.makeText(this, "sdfjkl", Toast.LENGTH_SHORT).show();
        }
    }


    private void showSnackBar(View v) {
        View view1 = btnCamera;

        if (v.getId() == R.id.btnCameraOk) {
            Snackbar.make(view1, "CAMERA", Snackbar.LENGTH_SHORT).setAction("Accion", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("Snackbar-Camera", "Pulsado accion camera");
                    oldVersion();

                }
            }).show();
        }
        if (v.getId() == R.id.btnVideoOk) {
            Snackbar.make(view1, "VIDEO", Snackbar.LENGTH_SHORT).setAction("Accion", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("Snackbar-Video", "Pulsado accion video");
                    oldVersion();

                }
            }).show();
        }
    }

    private boolean checkPermission(String permission) {
        int result = this.checkCallingOrSelfPermission(permission);
        return result == PackageManager.PERMISSION_GRANTED;
    }
}
