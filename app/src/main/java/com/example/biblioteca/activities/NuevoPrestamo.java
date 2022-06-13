package com.example.biblioteca.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.biblioteca.R;
import com.example.biblioteca.entities.Prestamo;
import com.example.biblioteca.sqlite.DatabaseHelper;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;

public class NuevoPrestamo extends AppCompatActivity {
    Spinner spLibros;
    TextView tvLibro;
    String[] titulos;
    Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_prestamo);
        ColorDrawable colorDrawable = new ColorDrawable(getResources().getColor(R.color.terciario));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);

        cargaVariables();
        btnGuardar.setBackgroundColor(getResources().getColor(R.color.terciario));


        ArrayAdapter<String> adaptador = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_dropdown_item, titulos);
        spLibros.setAdapter(adaptador);


        spLibros.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tvLibro.setText(spLibros.getSelectedItem().toString());
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        btnGuardar.setOnClickListener(v -> {
            guardaPrestamo();
        });
    }

    private void guardaPrestamo() {
        if (tvLibro.getText().toString().isEmpty()) {
            Toast.makeText(this, getResources().getText(R.string.camposObligatorios), Toast.LENGTH_SHORT).show();
        } else {
            DatabaseHelper db = new DatabaseHelper(this);
            String hoy = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
            //suma 15 dias a la fecha de hoy
            String fechaDevolucion = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date(new Date().getTime() + (1000 * 60 * 60 * 24 * 15)));
            Prestamo prestamo = new Prestamo();
            prestamo.setLibro(tvLibro.getText().toString());
            prestamo.setFechaInicio(hoy);
            prestamo.setFechaFin(fechaDevolucion);
            db.createPrestamo(prestamo);

            Intent intent = new Intent();
            setResult(RESULT_OK, intent);
            finish();
        }
    }


    private void cargaVariables() {
        DatabaseHelper db = new DatabaseHelper(this);
        spLibros = findViewById(R.id.spinnerLibros);
        tvLibro = findViewById(R.id.nombreLibroPrestamo);
        btnGuardar = findViewById(R.id.btnGuardarPrestamo);
        titulos = db.getTitulos();
    }


}