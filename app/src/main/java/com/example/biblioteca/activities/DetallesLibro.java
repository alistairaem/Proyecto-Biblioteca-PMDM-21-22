package com.example.biblioteca.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.biblioteca.R;
import com.example.biblioteca.entities.Libro;
import com.example.biblioteca.sqlite.DatabaseHelper;

public class DetallesLibro extends AppCompatActivity {
    Libro libro;

    TextView tvTitulo, tvAutor, tvGenero, tvSubGenero;

    CheckBox cbLeido;

    Button volver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_libro);
        ColorDrawable colorDrawable = new ColorDrawable(getResources().getColor(R.color.terciario));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);

        cargaVariables();

        volver.setBackgroundColor(getResources().getColor(R.color.terciario));

        cargaLibro(getIntent().getLongExtra("id", 0));

        datosLibro();

        volver.setOnClickListener(v -> {
            finish();
        });

    }

    private void cargaLibro(long id) {
        DatabaseHelper db = new DatabaseHelper(this);
        libro = db.getLibro(id);
    }

    private void datosLibro() {
        tvTitulo.setText(libro.getTitulo());
        tvAutor.setText(libro.getAutor());
        tvGenero.setText(libro.getGenero());
        tvSubGenero.setText(libro.getSubgenero());
        if (libro.getLeido() == 1) {
            cbLeido.setChecked(true);
        } else {
            cbLeido.setChecked(false);
        }
    }

    private void cargaVariables() {
        tvTitulo = findViewById(R.id.titulo);
        tvAutor = findViewById(R.id.autor);
        tvGenero = findViewById(R.id.genero);
        tvSubGenero = findViewById(R.id.subgenero);
        cbLeido = findViewById(R.id.leido);
        volver = findViewById(R.id.btnVolver);
    }
}