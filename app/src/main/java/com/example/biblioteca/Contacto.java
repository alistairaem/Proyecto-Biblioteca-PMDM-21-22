package com.example.biblioteca;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Contacto extends AppCompatActivity {

    LinearLayout llamar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        cargaVariables();

        llamar.setOnClickListener(v -> {
            if (android.os.Build.VERSION.SDK_INT >= 23) {
                if (checkSelfPermission(android.Manifest.permission.CALL_PHONE)
                        == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:+34 886 12 04 64"));
                    startActivity(intent);
                }else{
                    onRequestPermissionsResult(1, new String[]{android.Manifest.permission.CALL_PHONE},
                            new int[]{PackageManager.PERMISSION_GRANTED});
                }
            }else{
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:+34 886 12 04 64"));
                startActivity(intent);
            }

        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:+34 886 12 04 64"));
                startActivity(intent);
            } else {
                Toast.makeText(this, R.string.permisoDenegado, Toast.LENGTH_SHORT).show();
            }
        }
    }


    private void cargaVariables() {
        llamar = findViewById(R.id.contactoLlamada);
    }
}