package com.example.biblioteca;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Contacto extends AppCompatActivity {

    LinearLayout llamar;
    LinearLayout mapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);
        ColorDrawable colorDrawable = new ColorDrawable(getResources().getColor(R.color.terciario));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);

        cargaVariables();

        llamar.setOnClickListener(v -> {
            if (android.os.Build.VERSION.SDK_INT >= 23) {
                if (checkSelfPermission(android.Manifest.permission.CALL_PHONE)
                        == PackageManager.PERMISSION_GRANTED) {
                    realizaLlamada();
                } else {
                    onRequestPermissionsResult(1, new String[]{android.Manifest.permission.CALL_PHONE},
                            new int[]{PackageManager.PERMISSION_GRANTED});
                }
            } else {
                realizaLlamada();
            }
        });

        mapa.setOnClickListener(v -> {
            abreMaps();
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                realizaLlamada();

            } else {
                Toast.makeText(this, R.string.permisoDenegado, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void realizaLlamada() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:+34 886 12 04 64"));
        startActivity(intent);
    }

    private void abreMaps() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("geo:0,0?q=IES+de+Teis,+Vigo"));
        startActivity(intent);
    }


    private void cargaVariables() {
        llamar = findViewById(R.id.contactoLlamada);
        mapa = findViewById(R.id.contactoMapa);
    }
}