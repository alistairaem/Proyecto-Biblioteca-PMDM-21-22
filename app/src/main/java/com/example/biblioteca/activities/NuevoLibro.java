package com.example.biblioteca.activities;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.biblioteca.R;
import com.example.biblioteca.entities.Libro;
import com.example.biblioteca.sqlite.DatabaseHelper;

public class NuevoLibro extends AppCompatActivity {

    private EditText titulo;
    private EditText autor;
    private EditText genero;
    private EditText subgenero;
    private Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_libro);
        ColorDrawable colorDrawable = new ColorDrawable(getResources().getColor(R.color.terciario));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);

        cargaVariables();
        btnGuardar.setBackgroundColor(getResources().getColor(R.color.terciario));

        btnGuardar.setOnClickListener(v -> {
            compruebaCampos();
        });
    }

    private void guardaLibro() {
        Libro libro = new Libro();
        DatabaseHelper db = new DatabaseHelper(this);
        libro.setTitulo(titulo.getText().toString());
        libro.setAutor(autor.getText().toString());
        libro.setGenero(genero.getText().toString());
        libro.setSubgenero(subgenero.getText().toString());
        db.createLibro(libro);

        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();

    }

    private void compruebaCampos() {
        if (titulo.getText().toString().isEmpty() || autor.getText().toString().isEmpty() || genero.getText().toString().isEmpty() || subgenero.getText().toString().isEmpty()) {
            Toast.makeText(this, getResources().getText(R.string.camposObligatorios), Toast.LENGTH_SHORT).show();
        }else{
            guardaLibro();
        }
    }

    private void cargaVariables() {
        titulo = findViewById(R.id.titulo);
        autor = findViewById(R.id.autor);
        genero = findViewById(R.id.genero);
        subgenero = findViewById(R.id.subgenero);
        btnGuardar = findViewById(R.id.btnGuardar);
    }
}