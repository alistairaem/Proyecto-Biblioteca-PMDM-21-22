package com.example.biblioteca.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.biblioteca.R;

public class MainAdmin extends AppCompatActivity {

    LinearLayout contacto;
    LinearLayout listaLibros;
    LinearLayout librosAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);
        ColorDrawable colorDrawable = new ColorDrawable(getResources().getColor(R.color.terciario));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);

        if(savedInstanceState == null){
            bienvenida();
        }

        cargaVariables();

        contacto.setOnClickListener(v -> {
            Intent intent = new Intent(MainAdmin.this, Contacto.class);
            startActivity(intent);
        });

        listaLibros.setOnClickListener(v -> {
            Intent intent = new Intent(MainAdmin.this, ListaLibros.class);
            intent.putExtra("usuario", false);
            startActivity(intent);
        });

        librosAdmin.setOnClickListener(v -> {
            Intent intent = new Intent(MainAdmin.this, ListaLibros.class);
            intent.putExtra("usuario", false);
            startActivity(intent);
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent();
        switch (item.getItemId()) {
            case R.id.finSesion:
                intent.setClass(this, MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.finApp:
                //Finaliza app
                finishAffinity();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private void bienvenida() {
        String mensaje = getResources().getString(R.string.bienvenida) + " Admin";
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    private void cargaVariables() {
        contacto = findViewById(R.id.contactoAdmin);
        listaLibros = findViewById(R.id.coleccionAdmin);
        librosAdmin = findViewById(R.id.librosAdmin);
    }
}